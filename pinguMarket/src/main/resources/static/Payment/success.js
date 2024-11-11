window.onload = function(){
	fetch("http://localhost:8081/payment",{
			method: 'POST',
			headers: {
    			"Content-Type": 'application/x-www-form-urlencoded',
    				},
			body : new URLSearchParams({
				address:window.localStorage.getItem('adress'),
				couponId:window.localStorage.getItem('couponId'),
				cartData:window.localStorage.getItem('cartData'),
				delRequest:window.localStorage.getItem('delRequest'),
				orderId:window.localStorage.getItem('orderId')
				})
		})
		.then(response => {
			if(response.ok){
				window.localStorage.clear();
				if (confirm("구매내역 화면으로 이동하시겠습니까?")) {
            		location.href ="http://localhost:8081/cart";
       			 }
			}else{
				window.localStorage.clear();
				alert("상품수량이 부족합니다.");
			}
		})
		.catch(error =>{
			alert("연결에 실패하였습니다");
			console.error('Error: ',error);
		})
}