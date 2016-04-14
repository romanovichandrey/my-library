<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 14.04.2016
  Time: 7:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title></title>
</head>
<body>
<form method="post" action="addBook">
  <table width="30%" border="1" cellpading="3">
    <thead>
    <tr>
      <th colspan="2">Добавление книги в библиотеку</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Укажите название книги</td>
      <td><input type="text" name="name" value="" /></td>
    </tr>
    <tr>
      <td>Укажите описание книги</td>
      <td><input type="text" name="description" value="" /></td>
    </tr>
    <tr>
      <td>Укажите автора книги</td>
      <td><input type="text" name="author" value="" /></td>
    </tr>
    <tr>
      <td>Укажите дату написания книги</td>
      <td><input type="text" name="book_date" value="" /></td>
    </tr>
    <tr>
      <td>Укажите примерную цену книги</td>
      <td><input type="text" name="price" value="" /></td>
    </tr>
    <tr>
      <td>Выберите категорию книги</td>
      <td><select name="id_cat">
        <c:forEach items="${categoryList}" var="category">
          <option value="${category.id_cat}">${category.name_cat}</option>
        </c:forEach>
      </select></td>
    </tr>
    </tbody>
  </table>
  <input type="submit" value="Добавить книгу" />
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>
