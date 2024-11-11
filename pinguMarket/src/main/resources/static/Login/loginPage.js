let loginfrm = document.querySelector("#LoginForm");
let ApiUrl = "http://192.168.17.254:8080/login"

	loginfrm.addEventListener("submit", function(e){
		
		
	
		let loginId = document.querySelector("#loginId").value;
		let pwd = document.querySelector("#password").value;
		e.preventDefault();

      axios({
      url: ApiUrl,
      method: "post",
      withCredentials: true,
      headers : {		  
	      "Content-Type": "application/json"
	   },
      data: JSON.stringify([{
				
      	'Id' : loginId,
      	'password' : pwd
      
      }])
       })
      .then(response => {
       //성공시 구현 될 코드
       alert("로그인에 성공하였습니다.")
       let jwtToken = response.headers.get("Authorization"); 
	   document.cookie = `jwtToken=${jwtToken};path=/;`
	   		axios({
		      url: "http://localhost:8080/JwtCookie/request",
		      method: "post",
		      withCredentials: true,
		      headers : {		  
			      "Authorization": jwtToken
			   }
		      })	
	   	
	   	
	   	
       location.href="/main";
  		
       console.log(response.data);
      })
      .catch(error => {
       //실패시 구현 될 코드
       console.error(error);
      });

})




