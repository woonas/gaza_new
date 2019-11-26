<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>가자에어 관리자페이지 로그인</title>
        <style>
            .login-page {
                width: 360px;
                margin: 0 auto;
                position: absolute;
                top: calc(50vh - 185px);
                left: calc(50% - 180px);
            }
            .form {
                position: relative;
                z-index: 1;
                background: #FFFFFF;
                max-width: 360px;
                margin: 0 auto 100px;
                padding: 45px;
                text-align: center;
                box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
            }
            .form input {
                font-family: "Roboto", sans-serif;
                outline: 0;
                background: #f2f2f2;
                width: 100%;
                border: 0;
                margin: 0 0 15px;
                padding: 15px;
                box-sizing: border-box;
                font-size: 14px;
            }
            .form button {
                font-family: "Roboto", sans-serif;
                text-transform: uppercase;
                outline: 0;
                background: #36f;
                width: 100%;
                border: 0;
                padding: 15px;
                color: #FFFFFF;
                font-size: 14px;
                -webkit-transition: all 0.3s ease;
                transition: all 0.3s ease;
                cursor: pointer;
                margin-top: 10px;
            }
            .form button:hover,.form button:active,.form button:focus {
                background: #58f;
            }
            .form .register-form {
                display: none;
            }
            .container {
                position: relative;
                z-index: 1;
                max-width: 300px;
                margin: 0 auto;
            }
            .container:before, .container:after {
                content: "";
                display: block;
                clear: both;
            }
            .container .info {
                margin: 50px auto;
                text-align: center;
            }
            .container .info h1 {
                margin: 0 0 15px;
                padding: 0;
                font-size: 36px;
                font-weight: 300;
                color: #1a1a1a;
            }
            .container .info span {
                color: #4d4d4d;
                font-size: 12px;
            }
            .container .info span a {
                color: #000000;
                text-decoration: none;
            }
            .container .info span .fa {
                color: #EF3B3A;
            }
            body {
                background: cornflowerblue;
                font-family: "Roboto", sans-serif;
            }
        </style>
    </head>
    <body style="height: 100vh;">
        <div class="login-page" >
            <div class="form">
                <img src="<%=img%>/logo/logo_basic.png" alt="" style="width: 100%; margin-bottom: 50px;">
                <form class="login-form" action="/admin_loginOk" method="post">
                    <input type="text" placeholder="아이디" name="adminId"/>
                    <input type="password" placeholder="비밀번호" name="adminPwd"/>
                    <button onclick="this.parentElement.submit();">로그인</button>
                </form>
            </div>
        </div>
    </body>
</html>