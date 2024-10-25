

// Tap Menu Event *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

const csSideNav = document.querySelectorAll(".csSideNav>ul>li");
const AllcsContent = document.querySelectorAll(".csContainer>ul>li");

console.log(csSideNav);

csSideNav.forEach(item =>{
    item.addEventListener('click',function(){
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



// textarea event-------------------------------------------------------------
function handleInput(textarea) {
    var placeholder = document.getElementById("conPlaceholder");
    if (textarea.value.length > 0) {
        placeholder.style.display = "none";
    } else {
        placeholder.style.display = "block";
    }
}