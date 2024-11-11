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
      code: code,
      invoice: invoice
    })
  })
  .then(response => {
    if (response.ok) {
      return response.json();
    } else {
      throw new Error('에러');
    }
  })
  .then(data => {
	
	let trackingData = JSON.parse(data.trackingData);
    if (trackingData.status === false) {
      alert(trackingData.msg);
    }else if(trackingData.result === 'N'){
	alert("해당 운송장은 배송정보 조회 결과값이 N입니다.");
} else{
	console.log(trackingData);
      const resultDiv = document.getElementById('deliveryStatusResult');
                    resultDiv.innerHTML = `
                        <h2>배송 상태</h2>
                        <p>운송장 번호: ${trackingData.invoiceNo}</p>
                        <p>배송사 : ${code}</p>
                        <p>배송 상태: ${trackingData.level}</p>
                    `;
      const detailsContainer = document.createElement('div');
      detailsContainer.id = 'detailsContainer';
      resultDiv.appendChild(detailsContainer);
      
      trackingData.trackingDetails.forEach((trackingDetail) =>{
	    const detailsItem = document.createElement('div');
        detailsItem.classList.add('detailsItem');
        
        const deliveryDate = new Date(trackingDetail.timeString);
        const formattedDate1 = printDate(deliveryDate);
        
        detailsItem.innerHTML = `
       <div class="deliveryWhere"><p> 위치 : ${trackingDetail.where}</p></div>
       <div class="deliveryKind"><p> 상태 : ${trackingDetail.kind}</p></div>
       <div class="deliveryDate"><span>${formattedDate1}</span></div>
       

        `;
        detailsContainer.appendChild(detailsItem);
});
      
    }
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
			
			
			fetch('http://localhost:8081/periodloading', {
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