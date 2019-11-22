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
	아이디: <input type="text" name="adminId"><br/>
	비밀번호: <input type="text" name="adminPwd"><br/>
	관리자이름: <input type="text" name="adminName"><br/>
	관리자번호: <input type="text" name="adminTel"><br/>
	이메일: <input type="text" name="adminEmail"><br>
	<input type="submit" name="adminAdd" value="관리자 추가"><br/>
</form>

</body>
</html>