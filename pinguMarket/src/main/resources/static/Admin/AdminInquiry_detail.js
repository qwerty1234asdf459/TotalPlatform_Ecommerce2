/* -----------------------답변등록 > 초기화 버튼---------------------- */
function clearA(){
    let answerTitle = document.querySelector("#newTitle");
    let answerContent = document.querySelector("#newContents");

    answerTitle.value = "";
    answerContent.value = "";
    
}


/* -----------------------답변 삭제 버튼------------------------- */

function deleteA(e) {
    let csAnswerId = document.querySelector('input[name="csAnswerId"]').value; 
    // 숨겨진 input 필드에서 csAnswerId 가져오기

    e.preventDefault(); // 기본 동작 방지
    if (confirm("정말로 삭제하시겠습니까?")) {
        // console.log(csAnswerId); 성공

        fetch("/admin/cs/answerDelete", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `csAnswerId=${csAnswerId}`
        })
        .then(response => {
            if (response.ok) {
            location.reload(); //화면 새로고침
            } else {
                alert("삭제 실패");
            }
        })
        .catch(error => console.error("Error:", error));
    }
}

