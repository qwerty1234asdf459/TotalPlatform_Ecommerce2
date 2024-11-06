const updateBtn = document.querySelector("#updateBtn"); /* 수정버튼  */
const updateReBtn = document.querySelector("#updateReBtn"); /* 수정 저장버튼 */

const title = document.querySelector("#writing-td-text"); /*제목 */
const content = document.querySelector("#writing-td-textarea"); /*내용 */

const noticebox = document.querySelector("#noticebox"); /*조회 영역*/ 
const form = document.querySelector("#NoticeForm");/*폼 영역*/ 

updateReBtn.style.display = "none"; /*수정 저장버튼 */


/* -----------------------게시글 수정 버튼---------------------------- */
updateBtn.addEventListener('click', function(){
    updateBtn.style.display = "none";
    updateReBtn.style.display = "inline-block";
    noticebox.style.display = "none";
    form.style.display = "block";
    title.select();
});

/* -----------------------게시글 수정 저장버튼-------------------------- */
updateReBtn.addEventListener('click', function(){
    form.submit();
});


/* -------------------------게시글 삭제 버튼---------------------------- */
const deleteBtn = document.querySelector("#deleteBtn");/* 삭제버튼 */
//삭제하시겠습니까?메세지 "확인"을 선택했을때 해당 컴포넌트의 data-uri 값으로 URL 호출을 하라는 의미
    deleteBtn.addEventListener('click', function(){
        if(confirm("정말로 삭제하시겠습니까?")){
            location.href = this.dataset.uri;
        }
    });