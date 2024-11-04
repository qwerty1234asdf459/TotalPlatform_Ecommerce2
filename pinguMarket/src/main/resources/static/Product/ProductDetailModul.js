const productRelateNav = document.querySelector(".productRelateNav");
const productRelateCon = productRelateNav.querySelectorAll("li");

const productExplainContainer = document.querySelector(".productExplainContainer");
const productExplainCon = productExplainContainer.querySelectorAll("li");

const countup = document.querySelector(".countup");
const countdown = document.querySelector(".countdown");

const count = document.getElementById('count');

productRelateCon.forEach((item,index )=>{    
    item.addEventListener('click',function(e){
        e.preventDefault();
        console.log(index);
        productExplainCon.forEach(con =>{
            con.classList.remove("on");
        });
        productExplainCon[index].classList.add("on");
    
    });
});


const addCartBtn = document.querySelector(".cartInBtn");
addCartBtn.addEventListener('click', function() {
    document.getElementById('cart_count').value = count.textContent;
    console.log(document.querySelector('.count').textContent);
   document.getElementById('addCartForm').submit();
});

countup.addEventListener('click', function(){
	if(count.textContent>0){
		count.textContent++;
	}
});

countdown.addEventListener('click', function(){
	if(count.textContent>1){
		count.textContent--;
	}else{
		alert("0 이하로는 선택 할 수 없습니다.");
	}
});