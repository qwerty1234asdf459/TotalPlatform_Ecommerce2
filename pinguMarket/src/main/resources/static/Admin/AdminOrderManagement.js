//---------------주문목록 개수 세기(전체)----------------
const table = document.getElementById('myTable');
const totalRowCnt = table.rows.length;
totalResult.innerText = (totalRowCnt-1); 

//-----------------체크박스 선택 기능----------------------
const ag01 = document.querySelector("#Select01");
const ag = document.querySelectorAll(".Select");

// console.log(ag01);
// console.log(ag);
ag01.addEventListener('change',function(){ //전체 선택박스 눌렀을 때(동작함)
    if (ag01.checked) {
//        console.log("체크");
        ag.forEach(item => {
            item.checked = true;
            selectResult.innerText = (totalRowCnt-1); //전체개수
        });
    } else {
        ag.forEach(item => {
            item.checked = false;
            selectResult.innerText = 0; //0개
        });
    }
});

//---------------체크박스 개수 세기----------------

function getCheckedCnt()  {

    // 선택된 목록 가져오기
    let query = 'input[name="Select"]:checked';
    let selectedElements = document.querySelectorAll(query);

    // 선택된 목록의 갯수 세기
    const selectedElementsCnt = selectedElements.length;
    // 출력
    selectResult.innerText = selectedElementsCnt;

}

//-----------------모달 > 총 결제금액(구현필요)--------------
// totalPrice.innerText = ;
const plusPrice = document.getElementById('plusPrice');
console.log(plusPrice);

        
//-----------------모달 열기(젤 위 행만 됨..수정필요)----------------------
let modal = document.getElementById("modal"); 
let openmodal = document.querySelector(".drop");
let closeModal = document.querySelector(".closeM");
//let ismodalOpen = false; //open close상태를 나타내는 변수

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













