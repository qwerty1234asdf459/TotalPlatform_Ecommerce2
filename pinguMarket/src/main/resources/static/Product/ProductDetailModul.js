
// ---------------------- 상품설명 / 상세정보 / 리뷰 클릭 시------------->
const productRelateNav = document.querySelector(".productRelateNav");
const productRelateCon = productRelateNav.querySelectorAll("li");

const productExplainContainer = document.querySelector(".productExplainContainer");
const productExplainCon = productExplainContainer.querySelectorAll("li");

productRelateCon.forEach((item,index )=>{    
    item.addEventListener('click',function(e){
        e.preventDefault();
        //    console.log(index);
        productExplainCon.forEach(con =>{
            con.classList.remove("on");
        });
        productExplainCon[index].classList.add("on");
        
    });
});

// ---------------------- 장바구니 추가 버튼 기능------------------------>
const addCartBtn = document.querySelector(".cartInBtn");

addCartBtn.addEventListener('click',function(e){
		e.preventDefault();
		fetch("http://localhost:8081/product/addcart",{
			method: 'POST',
			headers: {
    			"Content-Type": 'application/x-www-form-urlencoded',
    				},
			body : new URLSearchParams({
				cart_count:document.querySelector('.count').textContent,
				product:document.getElementById('product').value
				})
		})
		.then(response => {
			if(response.ok){
				if (confirm("장바구니에 상품을 추가했습니다. 바로 확인하시겠습니까?")) {
            		location.href ="http://localhost:8081/cart";
       			 }
			}else{
				alert("이미 장바구니에 등록된 상품 입니다.");
				console.error(response);
			}
		})
		.catch(error =>{
			alert("연결에 실패하였습니다");
			console.error('Error: ',error);
		})
	
	
	
    //document.getElementById('cart_count').value = document.querySelector('.count').textContent;
   //document.getElementById('addCartForm').submit();
});

// ----------------------상품 금액 천단위 콤마찍기------------------------>
// const sell_price = document.getElementById("sell_price"); //1개 가격

// sell_price = sell_price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");


// ---------------------- 상품 수량 선택 버튼 기능(토탈 금액 조정)---------->
const countup = document.querySelector(".countup");
const countdown = document.querySelector(".countdown");

let count = document.getElementById('count').attributes.value.value;
let sell_price = document.getElementById("sell_price").attributes.value.value; //1개 가격
let totalPrice = document.getElementById("totalPrice").attributes.value.value = sell_price; //총 가격

let countText = document.getElementById('count');
let totalPriceText = document.getElementById('totalPrice');
//const 는 값 재할당이 안되므로 let으로 선언해야 함

//수량 기본값 설정
count = 1; //수량  1
totalPriceText.innerHTML = sell_price; //1개가격

//console.log(count, totalPrice, sell_price); //성공
//console.log(countText, totalPriceText); //성공

if(countup){
    countup.addEventListener('click', function(){
        count = count + 1;
        totalPrice = count*sell_price;
//        console.log(count, totalPrice, sell_price); //성공
        countText.textContent = count; //화면에 출력 
        totalPriceText.innerHTML = totalPrice; //화면에 출력
        
    })
}
if(countdown){
    countdown.addEventListener('click', function(){
        if(count>1){
            count--;
            totalPrice = count*sell_price;
//            console.log(count, totalPrice, sell_price); //성공
            countText.textContent = count; //화면에 출력 
            totalPriceText.innerHTML = totalPrice; //화면에 출력
            
        }else{
            count = 1;
        }
    })
}

//-------------------------리뷰 개수 세기-------------------------->
const table = document.getElementsByClassName('rv_container').length;
totalResult1.innerText = (' ('+table+')');
totalResult2.innerText = (table);


