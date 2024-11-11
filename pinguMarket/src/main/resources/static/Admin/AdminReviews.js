/***************************************체크박스 선택 기능**************************************** */
const ag01 = document.querySelector("#Select01");
const ag = document.querySelectorAll(".Select");

console.log(ag01);
console.log(ag);
ag01.addEventListener('change',function(){ //전체 선택박스 눌렀을 때(동작함)
    if (ag01.checked) {
        console.log("체크");
        ag.forEach(item => {
            item.checked = true;
        });
    } else {
        ag.forEach(item => {
            item.checked = false;
        });
    }
});

/***************************************삭제 기능************************************************** */
const deleteBtn = document.querySelector(".selectDel");

deleteBtn.addEventListener('click', function(){
    const selectedIds = Array.from(document.querySelectorAll("input[name='Select']:checked"))
        .map(checkbox => checkbox.closest(".Rows").querySelector(".Rows input").id.replace("Select", ""));
    if (selectedIds.length === 0) {
        alert("삭제할 리뷰를 선택해주세요.");
        return;
    }
    if (confirm("정말로 삭제하시겠습니까?")) {
        fetch('/admin/Reviews/delete', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ ids: selectedIds })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("선택된 리뷰가 성공적으로 삭제되었습니다.");
                window.location.reload();
            } else {
                alert("선택된 항목을 삭제하는데 실패했습니다.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("삭제 중 오류가 발생했습니다.");
        });
    }
});

    
const rows = document.querySelectorAll(".Rows");
const searchBtn = document.querySelector("#searchBtn"); //검색 버튼
const page_elements = document.getElementsByClassName("page-link"); //페이징 버튼들

// //검색 버튼 클릭시
searchBtn.addEventListener('click', function() {
	document.getElementById('kw').value = document.getElementById('search_kw').value;
	document.getElementById('kwType').value = document.getElementById('searchSelect').value;
	document.getElementById('searchForm').submit();
});

//검색창에서 엔터 버튼 클릭시
function enterkey() {
	if (window.event.keyCode == 13) { //엔터키가 눌렸을 때 
		document.getElementById('kw').value = document.getElementById('search_kw').value;
		document.getElementById('kwType').value = document.getElementById('searchSelect').value;
		document.getElementById('searchForm').submit();
	}ㄴ
}

//페이징 버튼 클릭시
Array.from(page_elements).forEach(function(element) {
	element.addEventListener('click', function() {
		document.getElementById('kw').value = document.getElementById('search_kw').value;
		document.getElementById('page').value = this.dataset.page;
	    document.getElementById('searchForm').submit();
	});
});
















// document.addEventListener("DOMContentLoaded", function () {
// //DOMContentLoaded란? : 
// //브라우저가 HTML을 전부 읽고 DOM 트리를 완성하는 즉시 발생합니다. 이미지 파일이나 스타일시트 등의 기타 자원은 기다리지 않습니다
    
//     const SelectAll = document.querySelector("#Select01"); // 전체선택
//     const deleteBtn = document.querySelector(".selectDel"); // 삭제버튼

//      // *************************************체크박스 선택 기능************************************************************
//      // 개별 체크박스의 체크가 하나라도 표시 안되어있으면 선택삭제 disabled
//     // function updateDeleteButtonState() { 
//     //     const anyChecked = Array.from(document.SelectorAll("input[name='Select']")).some(box => box.checked);
//     //     deleteBtn.disabled = !anyChecked;
//     // }

//     SelectAll.addEventListener("click", function () { // click 이벤트 사용시
//         const isChecked = productSelectAll.checked; // isChecked= 체크박스 표시
//         document.SelectorAll("input[name='Select']").forEach((box) => {
//             box.checked = isChecked;
//         });
//         updateDeleteButtonState(); // 삭제 실행
//     });

//     document.SelectorAll("input[name='Select']").forEach((box) => {
//         box.addEventListener("click", function () { // click 이벤트 사용시
//             const allChecked = Array.from(document.querySelectorAll("input[name='Select']")).every(box => box.checked);
//             SelectAll.checked = allChecked; // 전체 제품이 체크가 되면
//             updateDeleteButtonState();  // 삭제 실행
//         });
//     });
// // ****************************************리뷰 삭제 비동기처리************************************************************
    
//     deleteBtn.addEventListener("click", function () {
        // const selectedIds = Array.from(document.querySelectorAll("input[name='Select']:checked"))
        //     .map((checkbox) => checkbox.closest(".Rows").querySelector(".Rows input").id.replace("Select", ""));

//             alert("연결됐나?");
//             //선택한 리뷰가 없을 때
//             if (selectedIds.length === 0) {
//                 preventDefault(); // 창 이동 막기
//                 alert("삭제할 리뷰를 선택해주세요."); // 알림 표시
//             }

//         if (selectedIds.length > 0) {
//             const queryString = selectedIds.map(id => `id=${id}`).join("&"); // 쿼리 스트링으로 변환 / 아이디값 전달
//             fetch(`/admin/Reviews/delete?${queryString}`, {
//                 method: "GET",
//                  data : queryString
//             })
//             .then(response => response.json())
//             .then(data => {
//                 if (data.success) {
//                     window.location.reload(); // 삭제 후 페이지 새로고침
//                 } else {
//                     alert("선택된 항목을 삭제하는데 실패했습니다.");
//                 }
//             })
//             .catch(error => console.error("Error:", error));
//         }
//     });

// });