const csContentBox = document.querySelector(".csContainer>ul");
const csContentHeight = csContentBox.querySelector("li").offsetHeight;
console.log(csContentBox);

csContentBox.style.paddingTop = csContentHeight + "px";

window.addEventListener('resize', function () {
    const csContentHeight = csContentBox.querySelector("li").offsetHeight;
    csContentBox.style.paddingTop = csContentHeight + "px";
});

// Tap Menu Event *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*


const csSideNav = document.querySelectorAll(".csSideNav>ul>li");
const AllcsContent = document.querySelectorAll(".csContainer>ul>li");
const paging = document.querySelector(".pageBtn");
console.log(csSideNav);
console.log(AllcsContent[0]);

csSideNav.forEach(item => {
    item.addEventListener('click', function () {
        csSideNav.forEach(i => i.classList.remove("on"));
        item.classList.add("on");

        // 모든 콘텐츠에서 'on' 클래스를 제거
        AllcsContent.forEach(i => i.classList.remove("on"));

        // 현재 클릭한 아이템에 대응하는 콘텐츠를 찾아 'on' 클래스를 추가
        const index = Array.from(csSideNav).indexOf(item);
        if (index !== -1) {
            AllcsContent[index].classList.add("on");
        }
    });
});


const pageTypeInput = document.getElementById('pageType');
const searchForm = document.getElementById('searchForm');

// 탭 클릭 이벤트 리스너
csSideNav.forEach(item => {
    const link = item.querySelector('.tab-link');
    if (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault(); // 링크의 기본 동작 방지
            const selectedPageType = this.getAttribute('data-page-type');

            // 페이지 타입 변경
            pageTypeInput.value = selectedPageType;
            searchForm.submit(); // 폼 제출
        });
    }
});

// 페이징 버튼 클릭 이벤트
const pageElements = document.getElementsByClassName("page-link");

Array.from(pageElements).forEach(function (element) {
    element.addEventListener('click', function (event) {
        event.preventDefault(); // 링크의 기본 동작 방지
        const pageNo = this.dataset.page;

        if (pageTypeInput.value === 'inquiry') {
            document.getElementById('page1').value = pageNo; // 1:1 문의 페이지 번호 설정
        } else {
            document.getElementById('page2').value = pageNo; // 공지사항 페이지 번호 설정
        }

        searchForm.submit(); // 폼 제출
    });
});
