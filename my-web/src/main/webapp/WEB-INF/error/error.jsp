<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 13.04.2016
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form name="login-form" class="login-form" action="" method="post">

  <div class="header">
    <h1>Авторизация</h1>
    <span>Введите ваши регистрационные данные для входа в ваш личный кабинет. </span>
  </div>

  <div class="content">
    <input name="username" type="text" class="input username" value="Логин" onfocus="this.value=''" />
    <input name="password" type="password" class="input password" value="Пароль" onfocus="this.value=''" />
  </div>

  <div class="footer">
    <input type="submit" name="submit" value="ВОЙТИ" class="button" />
    <input type="submit" name="submit" value="Регистрация" class="register" />
  </div>

</form>
</body>
</html>
