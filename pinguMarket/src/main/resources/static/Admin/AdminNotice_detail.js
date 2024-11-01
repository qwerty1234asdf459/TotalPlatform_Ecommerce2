const updateBtn = document.querySelector("#updateBtn"); /* 수정  */
const updateReBtn = document.querySelector("#updateReBtn"); /* 수정 저장 */
const deleteBtn = document.querySelector("#deleteBtn");/* 삭제 */

const title = document.querySelector("#writing-td-text"); /*제목 */
const content = document.querySelector("#writing-td-textarea"); /*내용 */

const noticebox = document.querySelector("#noticebox"); /*조회 영역*/ 
const form = document.querySelector("#NoticeForm");/*폼 영역*/ 

updateReBtn.style.display = "none";


/* 수정 버튼 클릭시 */
updateBtn.addEventListener('click', function(){
    updateBtn.style.display = "none";
    updateReBtn.style.display = "inline-block";
    noticebox.style.display = "none";
    form.style.display = "block";
    // title.readOnly = false;
    // content.readOnly = false;
    title.select();
});

/* 수정 등록 버튼 클릭시 */
updateReBtn.addEventListener('click', function(){
    // alert("dd");
    // form.action = "/admin/Notice/detail/{noticeId}";
    // form.method = "post";
    form.submit();
});

/* 답변 삭제 버튼 클릭시 */
deleteBtn.addEventListener('click', function(){
    if(confirm("정말로 삭제하시겠습니까?")){
        location.href = this.dataset.uri;
        // location.href = "/admin/Notice/detail" + deleteBtn.getAttribute("data-id");
    }
});