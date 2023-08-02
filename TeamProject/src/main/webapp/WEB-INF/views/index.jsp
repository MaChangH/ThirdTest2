<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<script type="text/javascript" src="resources/js/go.js"></script>
</head>
<body>
C:/MaProgramming/Spring/TeamProject
	<h1>Index</h1>
	<a href="home.go">HOME</a>
	<a href="board.go">BOARD</a>
	<table border="1">
		<tr>
			<td>
				<jsp:include page="${cp }"/>
			</td>
		</tr>
	</table>
	<h1>master branch protect check</h1>
	<h1>master branch protect check2 with develop</h1> 
	<h2> 확인용 두번째 </h2>
	<h3> sourcetree 확인용  </h3>
	<h3> 4_test 확인용  </h3>
	<h3> 4_test 확인용 2 git add . 말고 직접 index.jsp 라고 써서 하나만 add 해보기</h3>
</body>
</html>