
//--------------------------------삭제 버튼 클릭-----------------------------------------
let del = document.querySelectorAll(".deleteBtn")
for (let el of del) {
    el.addEventListener('click', function () {
        if (confirm("정말로 삭제하시겠습니까?")) {
            alert("정상적으로 삭제되었습니다.")
            location.href = this.dataset.uri;
        } else {
            alert("삭제가 취소되었습니다.")
        }
    });
};
// -----------------------------수정 삭제 버튼 활성화 기능 ----------------------------

const askContainer = document.querySelector(".askContainer");
const retouchBtn = document.querySelector(".retouchBtn");
const deleteBtn = document.querySelector(".deleteBtn");

// 수정 버튼 활성화 여부
if (askContainer.querySelector(".adminReply")) {
    retouchBtn.style.display = "none";

} else {
    retouchBtn.style.display = "block";
}

// 삭제 버튼 활성화 여부
if (askContainer.querySelector(".adminReply")) {
    deleteBtn.style.display = "none";

} else {
    deleteBtn.style.display = "block";
}


// -----------------수정버튼 클릭 시 URL 이동-------------------
function questionModify(questionId) {
    location.href = '/csc/modify/' + questionId;
}




