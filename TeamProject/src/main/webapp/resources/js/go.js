
// 게시글 클릭시 전체 페이지 보여주기
function boardViewGo(tp_b_no) {
	location.href = "board.view?tp_b_no=" + tp_b_no;
}

// 게시글 삭제하기
function boardDeleteGo(tp_b_no) {
	if (confirm("진짜 삭제 하시겠습니까?")) {
		location.href = "board.delete?tp_b_no=" + tp_b_no;
	}
}

// 게시글 수정하기
function boardUpdateGo(tp_b_no) {
	location.href = "board.update.go?tp_b_no=" + tp_b_no;
}

// 댓글 삭제하기
function replyDeleteGo(tp_r_no) {
	if (confirm("진짜 삭제 하시겠습니까?")) {
		location.href = "reply.delete?tp_r_no=" + tp_r_no;
	}
}



