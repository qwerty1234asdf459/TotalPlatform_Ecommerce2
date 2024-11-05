const changeAdressBtn = document.getElementById("changeAd");
const existingAdBtn = document.getElementById("existingAd");
const userAddress = document.getElementById("userAddress").value;

const usableCopunCount = document.getElementById("usableCoupon").value;

console.log(usableCopunCount);
// *************************기본 주소 불러오기***********************************
document.getElementById("adressTextArea").value = userAddress;
document.getElementById("adressTextArea").disabled = true;

// *************************기본주소 & 주소직접입력***********************************
existingAdBtn.addEventListener("click", function(){
	document.getElementById("adressTextArea").value = userAddress;
	document.getElementById("adressTextArea").disabled = true;
	priceCalculate();
})

changeAdressBtn.addEventListener("click", function(){
	console.log()
	document.getElementById("adressTextArea").value  = "";
	document.getElementById("adressTextArea").disabled = false;
})
// ************************************************************

// ***********************사용가능한 쿠폰 갯수********************************
//document.getElementById("usableCoupon").innerText="사용 가능한 쿠폰 "+usableCopunCount+"개";


const couponSelect = document.querySelector(".couponSelect");

couponSelect.addEventListener("click",priceCalculate);

function priceCalculate(){
	const orderPrice = document.getElementById("orderPrice");
	const couponPrice = document.getElementById("couponPrice");
	const discountPrice = document.getElementById("discountPrice");
	const deliveryPrice = document.getElementById("deliveryPrice");
	const finalPrice = document.getElementById("finalPrice");
	const paymentSubmit = document.getElementById("paymentSubmit");
	
	let sumPrice = 0;
	let deliPrice = 3000;
	let couPrice = parseInt(document.querySelector(".couponSelect").value)*-1;
	let discount = 0;
	let totalPrice = 0;
	
	couponPrice.textContent = couPrice;
	
	
	document.querySelectorAll(".productTitle").forEach((product)=>{
		let price = parseInt(product.querySelector(".price").value);
		let count = parseInt(product.querySelector(".count").value);
		
		sumPrice += price * count;
		
		orderPrice.textContent = sumPrice;
	});
	
	totalPrice = sumPrice+deliPrice+couPrice;
	
	deliveryPrice.textContent = deliPrice;
	finalPrice.textContent = totalPrice;
	paymentSubmit.textContent = totalPrice+"원 결제하기";
}

priceCalculate();
