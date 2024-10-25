const productRelateNav = document.querySelector(".productRelateNav");
const productRelateCon = productRelateNav.querySelectorAll("li");

const productExplainContainer = document.querySelector(".productExplainContainer");
const productExplainCon = productExplainContainer.querySelectorAll("li");



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