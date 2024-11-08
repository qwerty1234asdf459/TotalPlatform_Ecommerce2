/* -----------------------답변등록 > 초기화 버튼---------------------- */

// function clearA(){
//     let clearBtn = document.querySelector("#btn-clear");
//     let answerTitle = document.querySelector("#newTitle");
//     let answerContent = document.querySelector("#newContents");

//     clearBtn.addEventListener('click', function(){
//         answerTitle.value = "";
//         answerContent.value = "";
//     });
// }
function clearA(){
    let answerTitle = document.querySelector("#newTitle");
    let answerContent = document.querySelector("#newContents");

    answerTitle.value = "";
    answerContent.value = "";
    
}

    


/* -----------------------답변 삭제 버튼------------------------- */
function deleteA() {
    if(confirm("정말로 삭제하시겠습니까?")){
        //location.href = this.dataset.uri;
        location.href = "/admin/cs/answerDelete/";
    }
}
//계속 GET 404 403 에러남 개빡침
//function deleteA(){
 
   // if (confirm("정말로 삭제하시겠습니까?")) {
        // fetch('/admin/cs/answerDelete', {
        //     method: "POST",
        //     headers: {
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify({ ids: selectedIds })
        // })
        // .then(response => response.json())
        // .then(data => {
        //     if (data.success) {
        //         alert("답변이 성공적으로 삭제되었습니다.");
        //         window.location.reload();
        //     } else {
        //         alert("답변을을 삭제하는데 실패했습니다.");
        //     }
        // })
        // .catch(error => {
        //     console.error("Error:", error);
        //     alert("삭제 중 오류가 발생했습니다.");
        // });
  //  }
//}

//삭제하시겠습니까?메세지 "확인"을 선택했을때 해당 컴포넌트의 data-uri 값으로 URL 호출
// function deleteA() {
//     const deleteBtn = document.querySelector("#btn-delete");

//     deleteBtn.addEventListener('click', function(){
//         if(confirm("정말로 삭제하시겠습니까?")){
//             location.href = this.dataset.uri;
//         }
//     });
// }