const productRelateNav = document.querySelector(".productRelateNav");
const productRelateCon = productRelateNav.querySelectorAll("li");

const productExplainContainer = document.querySelector(".productExplainContainer");
const productExplainCon = productExplainContainer.querySelectorAll("li");

const countup = document.querySelector(".countup");
const countdown = document.querySelector(".countdown");


productRelateCon.forEach((item,index )=>{    
    item.addEventListener('click',function(e){
        e.preventDefault();
        console.log(index);
        productExplainCon.forEach(con =>{
            con.classList.remove("on");
        });
        productExplainCon[index].classList.add("on");
    
    });
    
const addCart_btn = document.getElementById("cartInBtn");
		addCart_btn.addEventListener('click', function() {
    		document.getElementById('cart_count').value = document.getElementById('count').value;
    		document.getElementById('addCartForm').submit();
		});
});