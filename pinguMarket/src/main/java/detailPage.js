// 0906 이순
// var wishbtn = document.querySelector(".wishbtn");
// wishbtn.onclick = function(){
//     alert("위시리스트에 추가했습니다.");
// }



// 위시리스트 토글 기능
function toggleAct (button) {
        button.classList.toggle("act");
  };
  
  
// wishbtn.asddEventListener('click', wishbtn);       





//
// --------- 경빈님 압도적 감사 ---------
//




// 변경해야하는 +, - 버튼, 수량, 총금액 선언
const p_num = document.querySelector(".p_num");
const minusbtn = document.querySelector(".minusbtn");
const plusbtn = document.querySelector(".plusbtn");
const totalPrice = document.querySelector(".totalPrice");

// 기본 수량
let num = 1;
// DB에서 가져온 가격을 선언
let price = document.querySelector(".price").innerText;

// 수량 변동
p_num.value = num;
// 금액 변동
totalPrice.innerText = price;

// 수량 감소시 금액과 개수 증가
minusbtn.addEventListener('click',function(){


    if(num === 1){
        alert("최소 수량은 1개입니다.")
    }
    else if(num > 1){
        num = num - 1;
        p_num.value = num; // num-1을 대입
        console.log(p_num.value);
        totalPrice.innerText = price*num;
    }

});

// 수량 추가시 금액과 개수 감소
plusbtn.addEventListener('click',function(){
    num = num + 1;
    p_num.value = num; // num+1을 대입
    console.log(p_num.value);
    totalPrice.innerText = price*num;

});
    
    
var cartbtn = document.querySelector(".cartbtn");
cartbtn.addEventListener('click', function(e){
	alert("장바구니에 추가했습니다.");
	document.getElementById('count1').value = p_num.value;
    document.getElementById('form1').submit();
    //location.href="/cart/"+ document.getElementById('userid').value;
});
