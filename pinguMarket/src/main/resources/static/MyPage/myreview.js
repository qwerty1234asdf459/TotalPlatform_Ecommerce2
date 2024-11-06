let del = document.querySelectorAll(".delete");
	        for(let el of del){
		el.addEventListener('click', function(){
			if(confirm("정말로 삭제?")){
				location.href = this.dataset.uri;
	//                         삭제의 data.uri가 dataset-uri에 저장하는 것이기 때문에 누른 버튼의 uri를 가져옴
			}
		});
	};