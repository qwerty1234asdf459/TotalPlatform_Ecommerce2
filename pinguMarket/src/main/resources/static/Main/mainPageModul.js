
// 상품컨테이너 높이세팅
function productHeightSetting() {

    const productContainer = document.querySelectorAll(".productContainer");

    productContainer.forEach(container => {
        const productSlides = container.querySelector(".slides");
        container.style.height = productSlides.offsetHeight + "px";
    });

}

window.addEventListener('resize', function () {
    productHeightSetting()
});

//상품컨테이너 슬라이드 설정
function productslide(container) {
    const slideContainer = document.querySelectorAll(container);
    
    slideContainer.forEach(item => {
        const slides = item.querySelector(".slides");
        const slide = slides.querySelector(".slide");
        const nextBtn = item.querySelector('.nextBtn');
        const preBtn = item.querySelector('.preBtn');

        let timer;
        let isTransitioning = false;
        let slideIndex = 0;
        console.log(slide);

        function productSlideSetting() {
            slides.style.width = 100 * slides.childElementCount + "%";
        };

        // 슬라이드 << 이동 이벤트
        function slideGo() {
            if (isTransitioning) return; // 슬라이드가 실행중이면 함수 종료

            isTransitioning = true;
            slides.style.transition = "left 0.3s ease";
            slides.style.left = `-${item.offsetWidth * slideIndex}px`;

            slides.addEventListener('transitionend', () => {
                slides.style.transition = 'none';
                isTransitioning = false;
            }, { once: true });
        }

        //버튼 이벤트
        function productBtnEvent(){
            if(slideIndex>0){
                preBtn.classList.remove("btnOff");
            }else{
                preBtn.classList.add("btnOff");
            }
            if(slideIndex == slides.childElementCount - 1){
                nextBtn.classList.add("btnOff");
            }else{
                nextBtn.classList.remove("btnOff");
            }
        }
        // 버튼 클릭 슬라이드 이벤트 (다음)
        nextBtn.addEventListener('click', function () {
            if (slideIndex < slides.childElementCount - 1) {
                slideIndex += 1;
            }
            slideGo();
            productBtnEvent();
        });

        // 버튼 클릭 슬라이드 이벤트 (이전)
        preBtn.addEventListener('click', function () {
            if (slideIndex > 0) {
                slideIndex -= 1;
            }
            slideGo();
            productBtnEvent();
        });

        // 화면 사이즈 변화 처리
        window.addEventListener('resize', () => {
            productSlideSetting();
        });

        //이벤트 시작
        productSlideSetting();
    });
}




// MAIN SLIDE EVENT 
slideEvent(".mainSlideContainer");

// PRODUCT EVENT
productHeightSetting();
productslide(".productContainer");

