document.addEventListener("DOMContentLoaded", function () {
    const productSelectAll = document.querySelector("#productSelect01"); // 전체선택
    const deleteBtn = document.querySelector(".selectDel"); // 선택삭제
    const productPricePop = document.getElementById("productPricePop"); // 상품금액
    const saleAmountElement = document.getElementById("saleAmount"); // 상품할인 금액
    const deliveryFee = parseInt(document.getElementById("deliveryFee").innerText.replace(/[^0-9]/g, '')) || 0; // 배송비
    const totalPriceElement = document.getElementById("totalPrice"); // 결제예정금액
    const paymentBtn = document.querySelector("#payment a"); // 결제하기 버튼

     // *****************************************************체크박스 선택 기능************************************************************

    function updateDeleteButtonState() { // 개별 체크박스의 체크가 하나라도 표시 안되어있으면 선택삭제 disabled
        const anyChecked = Array.from(document.querySelectorAll("input[name='productSelect']")).some(box => box.checked);
        deleteBtn.disabled = !anyChecked;
    }

    function updateTotalPrice() {
        // 기본 셋팅값 0원
        let productPriceTotal = 0;
        let saleAmountTotal = 0;

        document.querySelectorAll("input[name='productSelect']:checked").forEach((box) => { // 개별체크박스가 선택되면
            const productContainer = box.closest(".cartProduct"); // closest: 자기자신 포함해 위쪽으로 트리 순회
            const productPrice = parseInt(productContainer.querySelector(".productPrice p").innerText.replace(/[^0-9]/g, '')) || 0; // 숫자 or 0
            const productCount = parseInt(productContainer.querySelector(".productCount p.count").innerText) || 1; // 숫자 or 1

            productPriceTotal += productPrice * productCount; // Total 값
            //saleAmountTotal += (productPrice * productCount) * 0.1; // 할인율 없으므로 추후 필요하면 사용하기
        });

        productPricePop.innerText = `${productPriceTotal.toLocaleString()}원`; // toLocaleString() : 숫자 콤마찍기
        saleAmountElement.innerText = `-${saleAmountTotal.toLocaleString()}원`;
        
        const totalPrice = productPriceTotal - saleAmountTotal + deliveryFee;
        totalPriceElement.innerText = `${totalPrice.toLocaleString()}원`; // 총 Total 금액
    }

    productSelectAll.addEventListener("click", function () { // click 이벤트 사용시
        const isChecked = productSelectAll.checked; // isChecked= 체크박스 표시
        document.querySelectorAll("input[name='productSelect']").forEach((box) => {
            box.checked = isChecked;
        });
        updateDeleteButtonState(); // 실시간 선택삭제 버튼 반영
        updateTotalPrice(); // 실시간 가격 반영
    });

    document.querySelectorAll("input[name='productSelect']").forEach((box) => {
        box.addEventListener("click", function () { // click 이벤트 사용시
            const allChecked = Array.from(document.querySelectorAll("input[name='productSelect']")).every(box => box.checked);
            productSelectAll.checked = allChecked; // 전체 제품이 체크가 되면
            updateDeleteButtonState();  // 실시간 선택삭제 버튼 반영
            updateTotalPrice(); // 실시간 가격 반영
        });
    });
    
    // *****************************************************장바구니 제품 수량 조절 기능************************************************************
    function updateProductPrice(countElement, pricePerUnit) {
        const count = parseInt(countElement.innerText) || 0; // 제품 수량 or 0
        const updatedPrice = pricePerUnit * count; 
        productPricePop.innerText = `${updatedPrice.toLocaleString()}원`; // 수량에 따라 가격 반영
        updateTotalPrice();
    }

    document.querySelectorAll(".plusBtn").forEach(button => { 
        button.addEventListener("click", function () { // + 버튼 클릭 시
            const countElement = this.previousElementSibling; // previousElementSibling : 공백 제외한 요소만 카운트함
            let count = parseInt(countElement.innerText) || 0; // parseInt: 정수값으로 리턴
            const pricePerUnit = parseInt(this.closest(".productCon").querySelector(".productPrice p").innerText.replace(/[^0-9]/g, ''));

            countElement.innerText = ++count; // 값 증가
            updateProductPrice(countElement, pricePerUnit); // 가격 반영
        });
    });

    document.querySelectorAll(".minusBtn").forEach(button => {
        button.addEventListener("click", function () { // - 버튼 클릭 시
            const countElement = this.nextElementSibling;  // nextElementSibling : 공백, 텍스트를 포함하지 않은 Element(요소)만 가져옴
            let count = parseInt(countElement.innerText) || 0; // parseInt: 정수값으로 리턴
            const pricePerUnit = parseInt(this.closest(".productCon").querySelector(".productPrice p").innerText.replace(/[^0-9]/g, ''));

            if (count > 1) { // count>1 이상일때만 - 버튼 클릭 가능(최소수량 1)
                countElement.innerText = --count;
                updateProductPrice(countElement, pricePerUnit);
            }
        });
    });

    updateTotalPrice();
    

    // *****************************************************장바구니 제품 삭제************************************************************
    
    deleteBtn.addEventListener("click", function () {
        const selectedIds = Array.from(document.querySelectorAll("input[name='productSelect']:checked"))
            .map((checkbox) => checkbox.closest(".cartProduct").querySelector(".productCheckBox input").id.replace("productSelect", ""));
        
        if (selectedIds.length > 0) {
            const queryString = selectedIds.map(id => `id=${id}`).join("&"); // 쿼리 스트링으로 변환 / 아이디값 전달
            fetch(`/cart/delete?${queryString}`, {
                method: "GET",
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("선택된 항목을 삭제하였습니다.");
                    window.location.reload(); // 삭제 후 페이지 새로고침
                } else {
                    alert("선택된 항목을 삭제하는데 실패했습니다.");
                }
            })
            .catch(error => console.error("Error:", error));
        }
    });

// ****************************************************선택한 제품들 결제페이지로 데이터 전송************************************************************
    paymentBtn.addEventListener("click", function (event) {
        const selectedProducts = document.querySelectorAll("input[name='productSelect']:checked");
        const keyarr = new Array;
        const countArr = new Array;
        
        if (selectedProducts.length === 0) {
            event.preventDefault(); // 창 이동 막기
            alert("제품을 선택해주세요."); // 알림 표시
        }else{
			selectedProducts.forEach((checkedbox2) => {
           		keyarr.push(checkedbox2.value);
           		const productContainer = checkedbox2.closest(".cartProduct"); // closest: 자기자신 포함해 위쪽으로 트리 순회
           		countArr.push(parseInt(productContainer.querySelector(".productCount p.count").innerText));
           		document.getElementById('cartArr').value = JSON.stringify(keyarr);
				document.getElementById('countArr').value = JSON.stringify(countArr);
				document.getElementById('cartPaymentForm').submit();
            });
		}
    });
});




