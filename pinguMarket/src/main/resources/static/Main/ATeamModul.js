// slide event *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
function slideEvent(con01) {
    const slideContainer = document.querySelector(con01);
    const slides = slideContainer.querySelector(".slides");
    const slide = slides.firstElementChild;
    const nextBtn = slideContainer.querySelector('.nextBtn');
    const preBtn = slideContainer.querySelector('.preBtn');

    let slideWidth = slide.offsetWidth;
    let timer;
    let isTransitioning = false;

    // 슬라이드 초기 세팅
    function slideSetting() {
        slides.style.width = 100*slides.childElementCount +"%";
        slideWidth = slide.offsetWidth;
        slides.style.left = -slideWidth + "px"; 
    }

    // 슬라이드 << 이동 이벤트
    function slideGo() {
        if (isTransitioning) return; // 슬라이드가 실행중이면 함수 종료

        isTransitioning = true;
        slides.style.transition = "left 0.5s ease";
        slides.style.left = `-${slideWidth * 2}px`;

        slides.addEventListener('transitionend', () => {
            slides.style.transition = 'none';
            slides.appendChild(slides.firstElementChild);
            slides.style.left = `-${slideWidth}px`;
            isTransitioning = false;
        }, { once: true });
    }

    // 슬라이드 >> 이동 이벤트
    function slidePrev() {
        if (isTransitioning) return; // 슬라이드가 실행중이면 함수 종료

        isTransitioning = true;
        slides.style.transition = "left 0.5s ease";
        slides.style.left = `0px`;

        slides.addEventListener('transitionend', () => {
            slides.style.transition = 'none';
            slides.prepend(slides.lastElementChild); // 맨 앞에 추가
            slides.style.left = `-${slideWidth}px`;
            isTransitioning = false;
        }, { once: true });
    }

    // 슬라이드 시작 함수
    function startSlide() {
        timer = setInterval(slideGo, 4000);
    }

    // 슬라이드 종료 함수
    function stopSlide() {
        clearInterval(timer);
    }

    // 버튼 클릭 슬라이드 이벤트 (다음)
    nextBtn.addEventListener('click', function () {
        stopSlide();
        slideGo();
        startSlide();
    });

    // 버튼 클릭 슬라이드 이벤트 (이전)
    preBtn.addEventListener('click', function () {
        stopSlide();
        slidePrev();
        startSlide();
    });

    // 화면 사이즈 변화 처리
    window.addEventListener('resize', () => {
        stopSlide();
        slideSetting();
        startSlide();
    });

    //슬라이드 이벤트 시작 
    slideSetting();
    slides.prepend(slides.lastElementChild); // 슬라이드의 마지막 요소를 맨 앞에
    startSlide();
}



