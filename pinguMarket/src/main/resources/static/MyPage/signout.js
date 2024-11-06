let pwBtn = document.querySelector('#pwCheckBtn');
        pwBtn.addEventListener('click', function(e) {
    e.preventDefault();
    
    const password = document.getElementById('password').value; // 입력한 값을 password로 저장
    const csrfToken = document.getElementById('_csrf').value;
    
    fetch('http://localhost:8080/pwcheck', { // http://localhost:8080/pwcheck에 요청을 보냄
        method: 'POST', // post 형식
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded', // 컨텐츠 타입 정해주기 솔직히 이거 뭔지 잘 모르겠는데 예제에 있던거 그냥 넣음
            'X-CSRF-TOKEN': csrfToken // 서프토큰도 같이
        },
        body: new URLSearchParams({password: password}) // 현 페이지에서 입력한 password값을 password로 전송?
    })
    .then(response => response.json())  // 응답을 JSON 객체로 변환하여 받아오기
    .then(data => {
	// 받아온 json 데이터가 담긴 data
        if(data.pwChecked) {  // data에 있는 pwChecked값 체크
            document.getElementById('pwModal').style.display = 'none'; // pwChecked가 true면 모달창 none
        } else {
            document.getElementById('pwError').style.display = 'block'; // pwChecked가 false면 에러메세지 켜기
        }
    })
    .catch(error => {
        alert("연결에 실패하였습니다.");
        console.error('Error:', error);
    });
});