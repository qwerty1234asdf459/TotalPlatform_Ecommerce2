checkBox("#productSelect01",".productSelectBox");

const cartLeft = document.querySelector(".cartLeft");
const productSelectBox = document.querySelector("#productSelect01");
const cartContentNone = document.querySelector(".cartContentNone");
const productSeletAll = document.querySelectorAll(".productSelectBox");
const deletBtn = document.querySelector(".selectDel");

if(cartLeft.querySelector('.cartProductContainer') !==null){
    productSelectBox.disabled = false;
    cartContentNone.classList.add("off");
}else{
    productSelectBox.disabled = true;
    cartContentNone.classList.remove("off");
}

productSeletAll.forEach(item => {
    item.addEventListener('click',function (){
        if (item.checked == false){
            deletBtn.disabled = true;
        }else{
            deletBtn.disabled = false;
        }
    });
});


