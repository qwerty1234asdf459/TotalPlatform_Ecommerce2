    const periods = document.querySelectorAll('#period');
    let deliveryOpenBtns = document.querySelectorAll('.deliveryOpenBtn');
    const deliveryModal = document.querySelector('.deliveryModal');
    const modalCloseBtn = document.querySelector('.modalCloseBtn');
    const deliveryBtn = document.querySelector('#deliveryBtn');

    deliveryBtn.addEventListener('click', (e) => {
  e.preventDefault();

  const code = document.getElementById('t_code').value;
  const invoice = document.getElementById('t_invoice').value;
  
  fetch('/api/delivery/status', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: new URLSearchParams({
      carrierCode: code,
      invoiceNumber: invoice
    })
  })
  .then(response => {
    if (response.ok) {
      return response.text(); // 서버가 반환하는 응답 처리
    } else {
      throw new Error('Error during fetching delivery status');
    }
  })
  .then(data => {
    alert("성공: " + data); // 응답 데이터 확인
  })
  .catch(error => {
    console.error('Error:', error);
    alert("오류 발생: " + error.message);
  });
});
    
    function printDate(paymentDate) {
  const year = paymentDate.toLocaleDateString('en-US', {
    year: 'numeric',
  });
  const month = paymentDate.toLocaleDateString('en-US', {
    month: '2-digit',
  });
  const day = paymentDate.toLocaleDateString('en-US', {
    day: '2-digit',
  });
  
  return `${year}-${month}-${day}`;
}

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
        
        const paymentDate = new Date(payment.createDate);
        const formattedDate = printDate(paymentDate);
            
        orderItem.innerHTML = `
        <div class="contentListInfo">
            <div class="productImage"><img src="/img/ordertest.jpg" alt="상품 이미지"></div>
            <div class="paymentDate"><span>${formattedDate}</span></div>
            <div class="paymentPrice"><span>${data.totalPrices[index]}원</span></div>
            <div class="paymentProductName"><span>${data.productNames[index]}</span></div>
            <div class="paymentDeliveryno"><span>${payment.deliveryno}</span></div>
            <div class="paymentDeliveryState"><span>${payment.deliveryState}</span><button class="deliveryOpenBtn">배송 조회하기</button></div>
            </div>
        `;
        orderListContainer.appendChild(orderItem);
    });
    const deliveryOpenBtns = document.querySelectorAll('.deliveryOpenBtn');
    deliveryOpenBtns.forEach(deliveryOpenBtn => {
        deliveryOpenBtn.addEventListener('click', (event) => {
            event.stopPropagation();
            deliveryModal.style.display = "flex";
        });
    });
}
   
periods.forEach((e) => {
  e.addEventListener("click", periodColor);
  e.addEventListener('click', periodParameter);
});


deliveryOpenBtns.forEach(deliveryOpenBtn =>{
	deliveryOpenBtn.addEventListener('click', (event) =>{
        event.stopPropagation();
        deliveryModal.style.display="flex";
    })
})
	
modalCloseBtn.addEventListener("click", ()=>{
     deliveryModal.style.display = "none"
	});