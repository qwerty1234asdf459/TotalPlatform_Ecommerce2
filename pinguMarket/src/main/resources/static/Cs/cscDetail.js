
//--------------------------------삭제 버튼 클릭-----------------------------------------
let del = document.querySelectorAll(".deleteBtn") // 삭제 버튼
for (let el of del) {
    el.addEventListener('click', function () { // 삭제 버튼 클릭 시
        if (confirm("정말로 삭제하시겠습니까?")) {
            alert("정상적으로 삭제되었습니다.")
            location.href = this.dataset.uri; // data-uri 값으로 URL 호출
        } else { // 삭제 취소 시
            alert("삭제가 취소되었습니다.")
        }
    });
};
// -----------------------------수정 삭제 버튼 활성화 기능 ----------------------------

const askContainer = document.querySelector(".askContainer"); // 질문 박스
const retouchBtn = document.querySelector(".retouchBtn"); // 수정 버튼
const deleteBtn = document.querySelector(".deleteBtn"); // 삭제 버튼

// 수정 버튼 활성화 여부
if (askContainer.querySelector(".adminReply")) { // 관리자 답변이 있으면
    retouchBtn.style.display = "none"; // 수정 버튼 숨김

} else {
    retouchBtn.style.display = "block"; //관리자 답변이 없으면 수정 버튼 활성화
}

// 삭제 버튼 활성화 여부
if (askContainer.querySelector(".adminReply")) { // 관리자 답변이 있으면
    deleteBtn.style.display = "none"; // 삭제 버튼 숨김

} else {
    deleteBtn.style.display = "block"; // 관리자 답변이 없으면 삭제 버튼 활성화
}


// -----------------수정버튼 클릭 시 URL 이동-------------------
function questionModify(questionId) {
    location.href = '/csc/modify/' + questionId;
}




