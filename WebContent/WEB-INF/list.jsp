<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.GuestbookVo" %>
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import = "java.util.List" %>

<%
	//리스트
	List<GuestbookVo> guestList = (List<GuestbookVo>)request.getAttribute("gList");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guestbook1</title>
</head>
<body>
	<h1>Guestbook1</h1>
	<p>안녕하세요<br> 게스트북 리스트입니다</p>
	
	<form action="./gbc" method="post">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea name="content" rows="10" cols="60"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="hidden" name="action" value="insert">
					<button type="submit">확인</button>
				</td>
			</tr>
		</table>
	</form>
	<br>
	
	
	<% for(int i=0; i<guestList.size(); i++) {%>
		<table border="1">
			<tr>
				<td><%=guestList.get(i).getNo() %></td>
				<td><%=guestList.get(i).getName() %></td>
				<td><%=guestList.get(i).getRegDate() %></td>
				<td><a href="./gbc?action=deleteForm&no=<%=guestList.get(i).getNo() %>">삭제</a></td>
			</tr>
			<tr>
				<td colspan="4">
					<%=guestList.get(i).getContent() %>
				</td>
			</tr>
		</table>
		<br>
	<%} %>
	
</body>
</html>