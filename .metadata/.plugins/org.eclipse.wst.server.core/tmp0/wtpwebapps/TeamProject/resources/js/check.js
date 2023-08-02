function writeCheck() {
	var titleBox = document.writeForm.t_title;
	var txtBox = document.writeForm.t_txt;
	if (isEmpty(titleBox) || atLeastLetter(titleBox, 2)) {
		alert("제목을 2글자이상 입력해 주세요");
		titleBox.value = "";
		titleBox.focus();
		return false;
	}else if (isEmpty(txtBox) || atLeastLetter(txtBox, 2)) {
		alert("내용을 2글자이상 입력해 주세요");
		txtBox.value = "";
		txtBox.focus();
		return false;
	}
}






