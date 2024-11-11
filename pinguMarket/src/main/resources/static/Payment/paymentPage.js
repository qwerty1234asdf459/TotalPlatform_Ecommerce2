const changeAdressBtn = document.getElementById("changeAd");
const existingAdBtn = document.getElementById("existingAd");

const shippingselect = document.getElementById("shippingselect");
const usableCopunCount = document.getElementById("usableCoupon").value;
const delRequest = document.getElementById("delRequest");
const addressTextArea = document.getElementById("adressTextArea");
const addressTextArea2 = document.getElementById("adressTextArea2");

let userAddress = document.getElementById("userAddress").value;
let userAddressDetail = document.getElementById("userAddressDetail").value;

// *************************기본 주소 불러오기***********************************
function existingAddress(){
	addressTextArea.value = userAddress;
	addressTextArea2.value = userAddressDetail;
	addressTextArea.disabled = true;
	addressTextArea2.disabled = true;
}	
	existingAddress();
// *************************기본주소 & 주소직접입력***********************************
existingAdBtn.addEventListener("click", existingAddress);

changeAdressBtn.addEventListener("click", function(){
	addressTextArea.value  = "";
	addressTextArea2.value  = "";
	addressTextArea.disabled = false;
})

// *************************주소검색 API***********************************


function findAddr(){
	let addressStr ="";
    new daum.Postcode({
      
      oncomplete: function(data) {
          console.log(data);
        
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
          // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          var roadAddr = data.roadAddress; // 도로명 주소 변수
          var jibunAddr = data.jibunAddress; // 지번 주소 변수
          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          addressStr += data.zonecode;
          if(roadAddr !== ''){
              addressStr +=" "+roadAddr;
          } 
          else if(jibunAddr !== ''){
              addressStr += " "+jibunAddr;
          }
          addressTextArea.value = addressStr;
          addressTextArea2.disabled = false;
      }
  }).open();
}

addressTextArea.addEventListener("click",findAddr);
// **************************배송요청사항 직접입력***************************
shippingselect.addEventListener("change",function(e){
	delRequest.value = "";
	if(e.target.value === "write"){
		delRequest.type = "text";
	}
	else{
		delRequest.type = "hidden";
		delRequest.value = e.target.value;
	}
})

// **********************쿠폰 사용********************************
const couponSelect = document.getElementById("couponSelect");

couponSelect.addEventListener("click",priceCalculate); //쿠폰이 선택될때마다 가격 업데이트

const paymentSubmit = document.getElementById("paymentSubmit");
const couponPrice = document.getElementById("couponPrice");

// **********************결제금액 계산(업데이트)********************************

let totalPrice = 0;

function priceCalculate(){
	const orderPrice = document.getElementById("orderPrice");
	const discountPrice = document.getElementById("discountPrice");
	const deliveryPrice = document.getElementById("deliveryPrice");
	const finalPrice = document.getElementById("finalPrice");
	
	let sumPrice = 0;
	let deliPrice = 3000;
	let discount = 0;
	let couPrice = parseInt(document.querySelector(".couponSelect").value)*-1;
	
	couponPrice.textContent = couPrice+"원";
	
	//각 제품마다 가격+수량 계산
	document.querySelectorAll(".productTitle").forEach((product)=>{ 
		let price = parseInt(product.querySelector(".price").value);
		let count = parseInt(product.querySelector(".count").value);
		
		sumPrice += price * count;
		
		orderPrice.textContent = sumPrice+"원";
	});
	
	totalPrice = sumPrice+deliPrice+couPrice;
	
	deliveryPrice.textContent = deliPrice+"원";
	finalPrice.textContent = totalPrice+"원";
	finalPrice.value = totalPrice;
	paymentSubmit.textContent = totalPrice+"원 결제하기";
}

priceCalculate();

// **********************결제하기 버튼 눌렀을때 이벤트(결제정보를 DB에 저장시키기 위한 함수)********************************/


/////////////////////////랜덤한 4자리 알파벳을 만들기 위한 함수//////////////////////////////
const random = (length = 4) => {
  const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
  let str = '';
  for (let i = 0; i < length; i++) {
    str += chars.charAt(Math.floor(Math.random() * chars.length));
  }
  return str;
};

let orderId = random()+new Date().getTime(); // 랜덤 알파벳 + 오늘 날짜 = 주문번호


///////////////////////////주문한 상품 목록중 맨위에 있는 상품이름///////////////////////////////

const titleList = document.querySelectorAll(".productName");
	const nameArr = new Array;
	titleList.forEach((name)=>{
		nameArr.push(name.textContent);
	})
	
///////////////////////////////////////////////////////////
const psf = document.getElementById("paymentSubmit");
	psf.addEventListener('click',function(e){
		e.preventDefault();
			
		const selectedCartList = document.querySelectorAll(".carts");
	
		const cartArr = new Array;

		selectedCartList.forEach((cart)=>{
			cartArr.push(cart.value);
			console.log(cart.value);
		})
		//document.getElementById("paymentAddress").value = document.getElementById("adressTextArea").value+" "+document.getElementById("adressTextArea2").value;
		////document.getElementById("paymentCoupon").value = couponSelect.options[couponSelect.selectedIndex].id
		//document.getElementById("paymentRequest").value = delRequest.value
		//document.getElementById("cartArr").value = JSON.stringify(cartArr);
		//document.getElementById("orderId").value = orderId;
			
		fetch("http://localhost:8080/payment",{
			method: 'POST',
			headers: {
    			"Content-Type": 'application/x-www-form-urlencoded',
    				},
			body : new URLSearchParams({
				address:document.getElementById("adressTextArea").value+" "+document.getElementById("adressTextArea2").value,
				couponId:couponSelect.options[couponSelect.selectedIndex].id,
				cartData:JSON.stringify(cartArr),
				delRequest:delRequest.value,
				orderId:orderId
				})
		})
		.then(response => {
			if(response.ok){
				 handlePayment();
			}else{
				alert("결제에 실패했습니다.");
				console.error(response);
			}
		})
		.catch(error =>{
			alert("연결에 실패하였습니다");
			console.error('Error: ',error);
		})
	})



function requestTossPayment(orderId, totalPrice) {
    return new Promise((resolve, reject) => {
        const tossPayments = TossPayments('test_ck_26DlbXAaV0MOP52Gyd6KrqY50Q9R');
        tossPayments.requestPayment('카드', {
            amount: totalPrice,
            orderId: orderId,
            orderName: '주문 상품',
            successUrl: window.location.origin + '/success',
            failUrl: window.location.origin + '/fail',
            // 결제창에서 사용자가 취소했을 때 호출되는 콜백 함수
            onCancel() {
                reject(new Error('사용자에 의해 결제가 취소되었습니다.'));
                // 추가적으로 실행 중단 로직이나 UI 업데이트 등을 여기에 작성할 수 있음
                console.log('결제가');
                alert('사용자가 결제를 취소했습니다.');
            }
        });
    });
}
    
///////////////////////////////////////////////////
async function handlePayment() {
    try {
        const response = await requestTossPayment(orderId, totalPrice);
        console.log("결제 성공:", response);
    } catch (error) {
        console.error("결제 실패 또는 취소:", error.message);
        alert("결제가 실패하거나 취소되었습니다.");
    }
}
// 이벤트 핸들러
paymentSubmit.addEventListener('click', async function(e) {
    e.preventDefault();
    try {
        await handlePayment();
    } catch (error) {
        console.error("결제 처리 중 오류 발생:", error.message);
    }
});
	