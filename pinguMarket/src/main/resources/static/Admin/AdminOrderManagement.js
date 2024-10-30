// let subToggle=true;
// $(".drop").click(()=>{
//   if(subToggle){
//     $(".sub").slideDown(1000);
//   }else{
//     $(".sub").slideUp(1000);
//   }
//   subToggle=!subToggle;
// });


let modal = document.getElementById("modal"); 
let openmodal = document.querySelector(".drop");
let closeModal = document.querySelector(".closeM");
let ismodalOpen = false; //open close상태를 나타내는 변수

//모달 열기
openmodal.addEventListener("click", function(e){
//  console.log(e.target);
  modal.style.display = 'block';
});
//console.log(openmodal);

//모달 닫기
closeModal.addEventListener("click", function(e){
  //  console.log(e.target);
    modal.style.display = 'none';
});

// window.addEventListener("click", function(e){
//   if (e.target === modal) {
//            modal.style.display = 'none';
//        }
// });

// function handleClickOutside(e) {
//   if (ismodalOpen && !modal.contains(e.target)) {
//     modal.style.display = 'none';
//   }
// }
// document.addEventListener('click', handleClickOutside);
  




// 모달 닫기 참고
// closeModal.onclick = () => {
//   modal.style.display = 'none';
// };

// //모달 밖 누르면 모달 닫기?
// window.onclick = (event) => {
//   if (event.target === modal) {
//       modal.style.display = 'none';
//   }
// };