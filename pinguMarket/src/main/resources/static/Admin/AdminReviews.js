document.addEventListener("DOMContentLoaded", function () {
//DOMContentLoaded란? : 
//브라우저가 HTML을 전부 읽고 DOM 트리를 완성하는 즉시 발생합니다. 이미지 파일이나 스타일시트 등의 기타 자원은 기다리지 않습니다
    
    const SelectAll = document.querySelector("#Select01"); // 전체선택
    const deleteBtn = document.querySelector(".selectDel"); // 삭제버튼

     // *************************************체크박스 선택 기능************************************************************
     // 개별 체크박스의 체크가 하나라도 표시 안되어있으면 선택삭제 disabled
    // function updateDeleteButtonState() { 
    //     const anyChecked = Array.from(document.SelectorAll("input[name='Select']")).some(box => box.checked);
    //     deleteBtn.disabled = !anyChecked;
    // }

    SelectAll.addEventListener("click", function () { // click 이벤트 사용시
        const isChecked = productSelectAll.checked; // isChecked= 체크박스 표시
        document.SelectorAll("input[name='Select']").forEach((box) => {
            box.checked = isChecked;
        });
        updateDeleteButtonState(); // 삭제 실행
    });

    document.SelectorAll("input[name='Select']").forEach((box) => {
        box.addEventListener("click", function () { // click 이벤트 사용시
            const allChecked = Array.from(document.querySelectorAll("input[name='Select']")).every(box => box.checked);
            SelectAll.checked = allChecked; // 전체 제품이 체크가 되면
            updateDeleteButtonState();  // 삭제 실행
        });
    });
// ****************************************리뷰 삭제 비동기처리************************************************************
    
    deleteBtn.addEventListener("click", function () {
        const selectedIds = Array.from(document.querySelectorAll("input[name='Select']:checked"))
            .map((checkbox) => checkbox.closest(".Rows").querySelector(".Rows input").id.replace("Select", ""));

            alert("연결됐나?");
            //선택한 리뷰가 없을 때
            if (selectedIds.length === 0) {
                preventDefault(); // 창 이동 막기
                alert("삭제할 리뷰를 선택해주세요."); // 알림 표시
            }

        if (selectedIds.length > 0) {
            const queryString = selectedIds.map(id => `id=${id}`).join("&"); // 쿼리 스트링으로 변환 / 아이디값 전달
            fetch(`/admin/Reviews/delete?${queryString}`, {
                method: "GET",
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    window.location.reload(); // 삭제 후 페이지 새로고침
                } else {
                    alert("선택된 항목을 삭제하는데 실패했습니다.");
                }
            })
            .catch(error => console.error("Error:", error));
        }
    });

});