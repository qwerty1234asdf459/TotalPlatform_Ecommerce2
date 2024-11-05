// 초기화버튼
let btnBox = document.querySelector("#btn-box");
let clearBtn = document.querySelector("#btn-clear");

let answerTitle = document.querySelector("#writing-td-text");
let answerContent = document.querySelector("#writing-td-textarea");

/* 초기화 버튼 클릭시 */
clearBtn.addEventListener('click', function(){
    answerTitle.value = "";
    answerContent.value = "";
});