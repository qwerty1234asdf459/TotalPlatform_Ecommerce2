const couponModal = document.querySelector('.couponModal');
const openBtn = document.querySelector('.openBtn');
const modalCloseBtn = document.querySelector('.modalCloseBtn');
openBtn.addEventListener("click", ()=>{
    couponModal.style.display="flex";
	});
	
modalCloseBtn.addEventListener("click", ()=>{
     couponModal.style.display = "none"
	});
	
const cif = document.getElementById("couponInputForm");
	cif.addEventListener('submit',function(e){
		e.preventDefault();
			
		const code = document.getElementById('codeInput').value;
			
		fetch("http://localhost:8080/mycoupon/inputcoupon",{
			method: 'POST',
			headers: {
    			"Content-Type": 'application/x-www-form-urlencoded',
    				},
			body : new URLSearchParams({code:code})
		})
		.then(response => {
			if(response.ok){
				alert("쿠폰이 입력되었습니다.");
				couponModal.style.display = "none";
				location.href ="http://localhost:8081/mycoupon";
			}else{
				alert("유효하지 않은 쿠폰코드 입니다.");
				console.error(response);
			}
		})
		.catch(error =>{
			alert("연결에 실패하였습니다");
			console.error('Error: ',error);
		})
	})