    const emailBtn = document.querySelector('#emailAuthBtn')
	const pwBtn = document.querySelector('#pwCheckBtn');
	const codeInput = document.querySelector('#codeInput');
	let codeCheckBtn = document.querySelector('#codeCheckBtn');
	let codeCheck = document.querySelector('#codeCheck');
	
		
		// 이메일 인증코드 전송
		emailBtn.addEventListener('click', function(e){
			
			e.preventDefault();
			
			const email = document.getElementById('email').value;
			
			fetch('http://localhost:8081/sendcode', {
				method : 'POST',
				headers : {
					'Content-Type': 'application/x-www-form-urlencoded',
				},
				body: new URLSearchParams({email:email})
			})
			.then(response => response.json())  // 응답을 JSON 객체로 변환하여 받아오기
            .then(data => {
	               // 받아온 json 데이터가 담긴 data
        if(data.code) {
            alert("인증 코드가 이메일에 전송되었습니다.");
            codeInput.value = data.code;
            
        } else {
            alert("인증 코드 전송에 실패했습니다.");
        }
    })
    .catch(error => {
        alert("연결에 실패하였습니다.");
        console.error('Error:', error);
    });
});

    // 인증 요구 및 인증 확인
    codeCheckBtn.addEventListener('click', function(){
	if(codeInput.value === "dkwlr"){
		alert("인증 코드를 먼저 받아주세요")
	}else if(codeInput.value === codeCheck.value){
		alert("인증이 완료되었습니다")
		document.getElementById('modify').style.display = 'block';
		document.querySelector('.emailAuth').style.display = 'none';
	}else{
		alert("코드가 잘못되었습니다.")
	}
	
})
 
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('address1').value = data.zonecode;
                document.getElementById("address2").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addressDetail").focus();
            }
        }).open();
    }
    
    // 도메인 직접 입력 or domain option 선택
	const domainListEl = document.querySelector('#domain-list')
	const domainInputEl = document.querySelector('#email2')
	// select 옵션 변경 시
	domainListEl.addEventListener('change', (event) => {
	  // option에 있는 도메인 선택 시
	  if(event.target.value !== "type") {
	    // 선택한 도메인을 input에 입력하고 disabled
	    domainInputEl.value = event.target.value
	    domainInputEl.readOnly = true
	  } else { // 직접 입력 시
	    // input 내용 초기화 & 입력 가능하도록 변경
	    domainInputEl.value = ""
	    domainInputEl.readOnly = false
	  }
	})
	
	const autoHyphen = (target) => {
	  target.value = target.value
	   .replace(/[^0-9]/g, '')
	   .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
	}