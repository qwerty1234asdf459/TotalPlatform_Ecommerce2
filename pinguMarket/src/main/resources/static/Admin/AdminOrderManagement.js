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



//-------여러개의 요소에서 특정 요소만 선택---------
elements.forEach(element => {
  element.addEventListener('click', function() {
      // 이전 선택된 요소가 있다면 스타일 제거
      if (selectedElement) {
          selectedElement.classList.remove('selected');
      }

      // 현재 클릭한 요소를 선택된 요소로 설정
      selectedElement = this;

      // 선택된 요소에 스타일 추가
      selectedElement.classList.add('selected');

      // 선택된 요소에 대한 추가 동작
      console.log(selectedElement.textContent + '가 선택되었습니다.');
  });
});





///////////////////////다른 방법
//모달 영역 외 클릭 시 모달 닫기
// document.body.addEventListener("click", function(e){
//   if (e.target === modal) {
//            modal.style.display = 'none';
//        }
// });
// function handleClickOutside(event) {
//   if (ismodalOpen && !modal.contains(event.target)) {
//     modal.style.display = 'none';
//   }
// }
// document.addEventListener('click', handleClickOutside);
  
// let subToggle=true; 제이쿼리
// $(".drop").click(()=>{
//   if(subToggle){
//     $(".sub").slideDown(1000);
//   }else{
//     $(".sub").slideUp(1000);
//   }
//   subToggle=!subToggle;
// });






