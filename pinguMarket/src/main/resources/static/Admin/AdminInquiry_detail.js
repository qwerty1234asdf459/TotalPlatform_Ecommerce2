
// const updateBtn = document.querySelector("#btn-updateRe");           /*답변 수정 버튼 : 답변 수정하는 창이 뜸*/
// const answerBtn = document.querySelector("#answer-btn"); /*답변 등록 버튼 : 답변 등록 창이 뜸 */




// const submitBtn = document.querySelector("#btn-submit"); /*답변 저장 버튼 : 작성한 답변을 저장*/


// let answerTable = document.querySelector("#answer-table");/*답변 신규 등록 시 뜨는 창 */
// let updateTable = document.querySelector("#updateTable");/*답변 수정 등록 시 뜨는 창 */

// let btnBox = document.querySelector("#btn-box");


// /* -----------------------답변 등록 버튼---------------------------- */
// answerBtn.addEventListener('click', function(){
    //     answerTable.style.display = "table";
    //     btnBox.style.display = "block";  /*저장, 초기화 버튼 보이게 */
    //     answerBtn.style.display = "none"; /*등록 버튼 안보이게 */
    // });
    
/* -----------------------답변등록 > 초기화 버튼---------------------- */
let clearBtn = document.querySelector("#btn-clear");     /*초기화 버튼 : 작성중인 답변을 초기화*/
let answerTitle = document.querySelector("#newTitle");
let answerContent = document.querySelector("#newContents");

clearBtn.addEventListener('click', function(){
    answerTitle.value = "";
    answerContent.value = "";
});
    
// /* -----------------------답변등록 > 저장 버튼------------------------- */
// updateReBtn.addEventListener('click', function(){
    //     form.submit();
    // });

/* -----------------------답변수정 > 저장 버튼------------------------- */
const updateReBtn = document.querySelector("#btn-updateRe"); /*답변 수정저장 버튼 : 수정한 답변을 저장*/
// updateBtn.addEventListener('click', function(){
    // //    answerTable.style.display = "table";
    //     updateReBtn.style.display = "block";  /*저장, 초기화 버튼 보이게 */
    //     updateBtn.style.display = "none"; /*등록 버튼 안보이게 */
// });