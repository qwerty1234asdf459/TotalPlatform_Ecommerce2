/*-----------------체크박스 선택 기능-------------------*/
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
/*--------------------회원삭제 기능----------------------*/
const deleteBtn = document.querySelector(".selectDel");

deleteBtn.addEventListener('click', function(){
    const selectedIds = Array.from(document.querySelectorAll("input[name='Select']:checked"))
        .map(checkbox => checkbox.closest(".Rows").querySelector(".Rows input").id.replace("Select", ""));
    if (selectedIds.length === 0) {
        alert("삭제할 회원정보를 선택해주세요.");
        return;
    }
    if (confirm("정말로 삭제하시겠습니까?")) {
        fetch('/admin/User/delete', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ ids: selectedIds })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("선택된 항목이 성공적으로 삭제되었습니다.");
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
var userName; // 강사 등록할 유저 이름

//검색 버튼 클릭시
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
	}
}

//페이징 버튼 클릭시
Array.from(page_elements).forEach(function(element) {
	element.addEventListener('click', function() {
		document.getElementById('kw').value = document.getElementById('search_kw').value;
		document.getElementById('page').value = this.dataset.page;
	    document.getElementById('searchForm').submit();
	});
});