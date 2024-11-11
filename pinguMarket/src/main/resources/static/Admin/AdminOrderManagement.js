//-----------------체크박스 선택 기능----------------------
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

let modal = document.getElementById("modal"); 
let openmodal = document.querySelector(".drop");
let closeModal = document.querySelector(".closeM");
//let ismodalOpen = false; //open close상태를 나타내는 변수

//-----------------모달 열기----------------------
openmodal.addEventListener("click", function(e){
//  console.log(e.target);
//ismodalOpen = true;
  modal.style.display = 'block';
});
//console.log(openmodal);

//-----------------모달 닫기----------------------
closeModal.addEventListener("click", function(e){
  //  console.log(e.target);
//  ismodalOpen = false;
    modal.style.display = 'none';
});
//추후 모달 영역 회 클릭시 모달 닫기 기능 추가하기


//-------여러개의 요소에서 특정 요소만 선택---------
// elements.forEach(element => {
//   element.addEventListener('click', function() {
//       // 이전 선택된 요소가 있다면 스타일 제거
//       if (selectedElement) {
//           selectedElement.classList.remove('selected');
//       }

//       // 현재 클릭한 요소를 선택된 요소로 설정
//       selectedElement = this;

//       // 선택된 요소에 스타일 추가
//       selectedElement.classList.add('selected');

//       // 선택된 요소에 대한 추가 동작
//       console.log(selectedElement.textContent + '가 선택되었습니다.');
//   });
// });

//---------------체크박스 개수 세기----------------
const table = document.getElementById('myTable');
const totalRowCnt = table.rows.length;
totalResult.innerText = (totalRowCnt-1);

//---------------체크박스 개수 세기----------------
//전체 선택 박스 눌렀을때 제대로 안먹힘

function getCheckedCnt()  {
    // 선택된 목록 가져오기
    const count = 'input[type="checkbox"]:checked';
    const selectedElements = 
        document.querySelectorAll(count);
    
    // 선택된 목록의 갯수 세기
    const selectedElementsCnt =
          selectedElements.length;
    
    // 출력
    result.innerText = selectedElementsCnt;

    // document.getElementById('result').innerText
    //   = selectedElementsCnt;
  }



const rows = document.querySelectorAll(".Rows");
const searchBtn = document.querySelector("#searchBtn"); //검색 버튼

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
