// let subToggle=true;
// $(".drop").click(()=>{
//   if(subToggle){
//     $(".sub").slideDown(1000);
//   }else{
//     $(".sub").slideUp(1000);
//   }
//   subToggle=!subToggle;
// });

addFilmBtn.onclick = () => {
  modal.style.display = 'block';
};

// 모달 닫기
closeModal.onclick = () => {
  modal.style.display = 'none';
};

window.onclick = (event) => {
  if (event.target === modal) {
      modal.style.display = 'none';
  }
};