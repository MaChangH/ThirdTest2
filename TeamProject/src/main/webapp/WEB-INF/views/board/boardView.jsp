<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/boardView.jsp</title>
<script type="text/javascript" src="resources/js/go.js"></script>
</head>
<body>
	<input name="token" value="${token }" type="hidden">
	<c:forEach var="tki" items="${boards }">
		<table id="talkingTbl" border="1">
			<tr>
				<td id="title" name="tp_b_title">&nbsp;${tki.tp_b_title }</td>
				<td align="right">
					<!-- <c:if test="${sessionScope.loginMember.m_nickname == tki.tp_b_writer }"></c:if> -->
					<button onclick="boardUpdateGo(${tki.tp_b_no })">수정</button>
					<button onclick="boardDeleteGo(${tki.tp_b_no })">삭제</button>
				</td>
			</tr>
			<tr>
				<td>&nbsp;${tki.tp_b_writer }</td>
				<td align="right"><fmt:formatDate value="${tki.tp_b_when }"
						pattern="yyyy-MM-dd E HH:mm:ss" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><c:if test="${tki.tp_b_photo != null }">
				<img src="resources/img/${tki.tp_b_photo }" style="max-width: 50%;"><br></c:if>
					<textarea rows="15" cols="100" style="resize: none; border: none;"
						readonly="readonly">${tki.tp_b_txt }</textarea></td>
			</tr>
		</table>
	</c:forEach>
	<c:forEach var="r" items="${Reply }">
	<table border="1">
		<tr>
	<form action="reply.update.go" method="post">
	<input value="${r.tp_r_no }" type="hidden" name="tp_r_no">
			<td>
				${r.tp_r_writer }
			</td>
			<td>
				<fmt:formatDate value="${r.tp_r_date }" pattern="yyyy-MM-dd E HH:mm:ss"/>
			</td>
			<td>
				<button>수정</button>
	</form>
				<button onclick="replyDeleteGo(${r.tp_r_no });">삭제</button>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				${r.tp_r_text }
			</td>
		</tr>
	</table><p>
	</c:forEach>
	<form action="reply.write" method="post">
	<input name="token2" value="${token }" type="hidden">
	<table border="1">
		<tr>
			<td colspan="2">
				<input placeholder="닉네임" name="tp_r_writer">
			</td>
		</tr>
		<tr>
			<td>
				<textarea name="tp_r_text"></textarea>
			</td>
			<td>
				<button>작성</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>