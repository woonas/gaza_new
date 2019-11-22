<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Admin추가폼</h1>
<form method="post" action="adminAddOk">
	회원번호: <input type="text" name="adminNum">
	아이디: <input type="text" name="adminId">
	비밀번호: <input type="text" name="adminPwd">
	관리자이름: <input type="text" name="adminName">
	관리자번호: <input type="text" name="adminTel">
	이메일: <input type="text" name="adminEmail">
	등록일: <input type="text" name="adminRegdate">
	추가 : <input type="submit" name="adminAdd">
</form>

</body>
</html>