<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardUpdate.jsp</title>
</head>
<body>
	<table border="1">
		<c:forEach var="tki" items="${boards }">
			<tr>
				<th colspan="2" align="left">글 수 정</th>
			</tr>
			<tr>
				<td>
					<form action="board.update" method="post" name="writeForm" enctype="multipart/form-data"
						onsubmit="return writeCheck();">
						<input name="token" value="${token }" type="hidden">
						<table id="writeTbl" border="1">
							<tr>
								<td id="writeTitle">&nbsp;제목 :<input name="tp_b_no"
									value="${tki.tp_b_no }" type="hidden" readonly="readonly"></td>
								<td><input name="tp_b_title" autocomplete="off" size="90"
									value="${tki.tp_b_title }"></td>
							</tr>
							<tr>
								<td colspan="2">
									<c:if test="${tki.tp_b_photo != null }">
										<img src="resources/img/${tki.tp_b_photo }" style="max-width: 50%;">
										<input type="file" name="tp_b_photo"><br>
									</c:if> 
									<textarea rows="15" cols="100" name="tp_b_txt" style="resize: none;">${tki.tp_b_txt }</textarea>
								</td>
							</tr>
						</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><button>작성</button></td>
				</form>
			</tr>
		</c:forEach>
	</table>
</body>
</html>