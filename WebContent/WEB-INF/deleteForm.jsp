<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String no = request.getParameter("no");
	System.out.println(no);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./gbc" method="post">
		<input type="hidden" name="no" value="<%=no%>">
		비밀번호 <input type="password" name="password">
		
		<input type="hidden" name="action" value="delete">
		<button type="submit">삭제</button>
		<br>
		<br>	
		<a href="./gbc?action=list">메인으로 돌아가기</a></td>
	</form>
</body>
</html>
