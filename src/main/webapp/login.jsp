<%-- 
    Document   : login
    Created on : Jun 1, 2022, 10:35:05 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="index.css" />
    </head>
    <body>
        <header>
            <h1>Hello World!</h1>
        </header>
        <section><marquee direction="left"> Moving... </marquee></section>
        <section>
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="txtusername"></td></tr>
                <tr><td>Password:</td>
                    <td><input type="password" name ="txtpassword"></td></tr>
                <tr><td colspan="2"><input type="checkbox" value="savelogin" name="action"> Stayed Signed In</td></tr>
                <tr><td colspan="2"><input type="submit" value="login" name="action"></td></tr>                  
            </table>
        </section>
        <footer><h6>Copyright, All rights Reserved</h6>

        </footer>
</html>
