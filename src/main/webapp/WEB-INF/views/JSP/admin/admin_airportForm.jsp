<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Airport 추가폼</h1>
<form method="post" action="adminAddOk">
	공항코드: <input type="text" name="iata"><br/>
	도시: <input type="text" name="city"><br/>
	대륙: <input type="text" name="continent"><br/>
	위도: <input type="text" name="latitude"><br/>
	경도: <input type="text" name="longitude"><br>
	이미지경로: <input type="text" name="cityImg"><br>
	<input type="submit" name="airportAdd" value="공항 추가"><br/>
</form>

</body>
</html>