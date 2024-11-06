    const periods = document.querySelectorAll('#period');

    function periodColor(event) {
  // 모든 "on" 클래스 제거
  periods.forEach((e) => {
    e.classList.remove("on");
  });
  // 요소만 "click"클래스 추가
  event.target.classList.add("on");
}

   function periodParameter(e){
	     e.preventDefault();

         const period = e.target.getAttribute("value");
			
			
			fetch('http://localhost:8080/periodloading', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: new URLSearchParams({period: period})
})
			.then(response => {
    if (!response.ok) {
        throw new Error('서버 응답이 실패했습니다.');
    }
    return response.json();
})
.then(data => {
    console.log(data); // 받은 데이터 구조 확인
    updateOrderList(data);
})
			
			.catch(error =>{
				alert("오류가 발생하였습니다 " + error.message);
				console.error('Error :', error);
			})
			
		};
		
		function updateOrderList(data) {
    const orderListContainer = document.querySelector('.contentListContainerContainer');
    orderListContainer.innerHTML = '';
    data.payments.forEach((payment, index) => {
        const orderItem = document.createElement('div');
        orderItem.classList.add('contentListContainer');
        orderItem.onclick = function() {
            location.href = `/myorder/detail/${payment.paymentId}`;
        };
        orderItem.innerHTML = `
        <div class="contentListInfo">
            <div class="productImage"><img src="/img/ordertest.jpg" alt="상품 이미지"></div>
            <div class="paymentDate"><span>${payment.createDate}</span></div>
            <div class="paymentPrice"><span>${data.totalPrices[index]}원</span></div>
            <div class="paymentProductName"><span>${data.productNames[index]}</span></div>
            <div class="paymentDeliveryno">${payment.deliveryno}</span></div>
            <div class="paymentDeliveryState">${payment.deliveryState}</span></div>
            </div>
        `;
        orderListContainer.appendChild(orderItem);
    });
}
   
   

periods.forEach((e) => {
  e.addEventListener("click", periodColor);
  e.addEventListener('click', periodParameter);
});

