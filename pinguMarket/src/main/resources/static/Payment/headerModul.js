//header event 관련 스크립트 *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
const bannerCloseBtn = document.querySelector(".closeBtn");
const headerBanner = document.querySelector(".headerBanner");
const header = document.querySelector("header");

const headerBannerHeight = headerBanner.offsetHeight;

bannerCloseBtn.addEventListener('click', function() {
    headerBanner.classList.add("off");
});


window.addEventListener('scroll',function(){
    let windowScrollY = window.scrollY;
    
    if(windowScrollY>0){
        header.classList.add("scrollHeader");
    }else{
        header.classList.remove("scrollHeader");
    }
    
});

// 마우스 이벤트
const categoryContainer = document.querySelector(".categoryContainer");
const categoryName = categoryContainer.querySelector("p");

categoryContainer.addEventListener('mouseenter', (event) => {
    categoryName.style.color = "#f7418f";
    categoryName.querySelector("i").style.color = "#f7418f";
});

categoryContainer.addEventListener('mouseleave', (event) => {
    categoryName.style.color = "";
    categoryName.querySelector("i").style.color = "";
});
