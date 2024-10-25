        const answerBtn = document.querySelector("#answer-btn");

        let answerTable = document.querySelector("#answer-table");
        let nav = document.querySelector("nav");
        let btnBox = document.querySelector("#btn-box");
        let clearBtn = document.querySelector("#btn-clear");

        let answerTitle = document.querySelector("#writing-td-text");
        let answerContent = document.querySelector("#writing-td-textarea");

        /* 답변 등록 버튼 클릭시 */
        answerBtn.addEventListener('click', function(){
            answerTable.style.display = "table";
            nav.style.height = "1600px";
            btnBox.style.display = "block";
        });

        /* 초기화 버튼 클릭시 */
        clearBtn.addEventListener('click', function(){
            answerTitle.value = "";
            answerContent.value = "";
        });