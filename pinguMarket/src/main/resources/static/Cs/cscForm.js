
// textarea event-------------------------------------------------------------
function handleInput(textarea) {
    var placeholder = document.getElementById("conPlaceholder");
    if (textarea.value.length > 0) {
        placeholder.style.display = "none";
    } else {
        placeholder.style.display = "block";
    }
}