

// Tap Menu Event *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

const csSideNav = document.querySelectorAll(".csSideNav>ul>li");
const AllcsContent = document.querySelectorAll(".csContainer>ul>li");

console.log(csSideNav);

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


//삭제 버튼 클릭
const del = document.querySelector(".deleteBtn");

del.addEventListener('click', function () {
    if (confirm("정말로 삭제하시겠습니까?")) {
        alert("정상적으로 삭제되었습니다.");
        // location.href = this.dataset.uri;
    }else{
        alert("삭제가 취소되었습니다.");
    }
});

// 수정 삭제 버튼 기능 ------------------------------------------------

const askContainer = document.querySelector(".askContainer");
const retouchBtn = document.querySelector(".retouchBtn");

if (askContainer.querySelector(".adminReply")) {
    retouchBtn.style.display = "none";

} else {
    retouchBtn.style.display = "block";
}