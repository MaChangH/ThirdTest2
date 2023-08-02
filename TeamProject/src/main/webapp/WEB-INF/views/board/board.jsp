<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/board.jsp</title>
</head>
<body>
	<table id="boardTbl" border="1">
		<%-- 글 제목 보이는 부분 --%>
		<tr>
			<td colspan="2" id="noSoild">
				<table id="boardMsgTbl" border="1">
					<tr>
						<td align="center">번호</td>
						<td>&nbsp;제목</td>
						<td align="center">작성자</td>
						<td align="left">&nbsp;작성일</td>
					</tr>
					<c:forEach var="tm" items="${boardMsg }">
						<tr onclick="boardViewGo(${tm.tp_b_no })">
							<td align="center">${tm.tp_b_no }</td>
							<td class="t_title">&nbsp;${tm.tp_b_title }</td>
							<td align="center" class="t_writer">${tm.tp_b_writer }</td>
							<td align="right">
								<fmt:formatDate value="${tm.tp_b_when }" pattern="yyyy-MM-dd HH:mm"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		
		<tr>

			<%-- 검색 기능 부분 --%>

			<td align="center"><form action="board.search" name="searchForm"
					onsubmit="return searchboard();">
					<input name="search" placeholder="제목 검색">
					<button>검색</button>
				</form></td>
			<td align="right" id="writeButton" class="boardSoild"><form
					action="board.write.go">
					<button>글쓰기</button>
				</form></td>
		</tr>

		

		<%-- 페이지 넘기는 부분 --%>

		<tr>
			<td colspan="2" align="center"><c:forEach var="p" begin="1"
					end="${allPageCount }">
					<a href="board.page?p=${p }">[${p }] </a>
				</c:forEach></td>
		</tr>
	</table>
</body>
</html>