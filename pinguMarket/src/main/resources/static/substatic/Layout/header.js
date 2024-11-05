let logobtn = document.querySelector(".logo");
logobtn.addEventListener("click", function(){
    location.href = "/main"
})




// console.log(pagelink);
// console.log(category);
// category.value="채소";
// console.log(category.value);
// for(let i=0; i<pagelink.length; i++){
//     category.value="과일";
//     document.querySelector(".search-bnticon").addEventListener("click", function(e){
//         e.preventDefault();
//         console.log(e.target);
//         //console.log(e.currentTarget);
//         alert("test");
//        // 이벤트 리스너 재설계, 메뉴 쪽에서 메뉴들에 대한 정보 클릭을 했을 때 페이지 링크 클릭했을 때로 가야함
//             console.log(category.value);
//         //document.form1.submit();
//     });
// }



let pagelink = document.querySelectorAll(".page-link");
let category = document.querySelector(".search-bnticon");
let categoryblock = document.querySelector("#category");
let keywordblock = document.querySelector("#keyword");
let submit = document.form1;


        Array.from(pagelink).forEach(function(element){
           
        element.addEventListener("click", function(e){
        categoryblock.value = e.currentTarget.textContent; 
        keywordblock.value = "";
        submit.submit();
        
    });


        });

        