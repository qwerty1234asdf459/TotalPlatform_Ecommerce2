
// textarea event-------------------------------------------------------------
function handleInput(textarea) {
    var placeholder = document.getElementById("conPlaceholder");
    if (textarea.value.length > 0) {
        placeholder.style.display = "none"; // 한 글자라도 적으면 placeholder 숨김
    } else {
        placeholder.style.display = "block"; // 그게 아니면 placeholder 보임
    }
}