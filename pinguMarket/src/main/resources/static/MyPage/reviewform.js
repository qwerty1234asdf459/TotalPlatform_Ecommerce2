function resize(obj) {
	if(obj.style.height == "0px" || obj.scrollHeight == 0) {
		obj.style.height = "100px";
	} else {
		obj.style.height = 'auto';
		obj.style.height = obj.scrollHeight + 'px';
	}
}