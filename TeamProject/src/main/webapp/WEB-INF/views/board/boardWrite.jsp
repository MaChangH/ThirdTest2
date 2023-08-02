<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/boardWrite.jsp</title>
</head>
<body>
	<form action="board.write" method="post" enctype="multipart/form-data" name="writeForm" onsubmit="return writeCheck();">
	<input name="token" value="${token }" type="hidden">
		<table id="writeAresTbl" border="1">
			<tr>
				<th colspan="2" align="left">글 작 성</th>
			</tr>
			<tr>
				<td>
					<table id="writeTbl" border="1">
						<tr>
							<td id="writeTitle">&nbsp;제목 :</td>
							<td><input name="tp_b_title" autocomplete="off" size="90"></td>
						</tr>
						<tr>
							<td>이름 : <input name="tp_b_writer"></td>
							<td><input type="file" name="tp_b_photo"></td>
						</tr>
						<tr>
							<td colspan="2"><textarea rows="15" cols="100" name="tp_b_txt"
									style="resize: none;"></textarea></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right" id="writeButton"><button>작성</button></td>
			</tr>
		</table>
	</form>
</body>
</html>