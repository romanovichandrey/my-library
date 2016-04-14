<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 14.04.2016
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 13.04.2016
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="user" value="${sessionScope.user}" />
<c:choose>
  <c:when test="${not empty user}">
    <p>Hello, ${user.reader.name}</p>
  </c:when>
  <c:otherwise>
    <center>
      <p>Yet Not Registered!!</p>
      <form method="post" action="/login">
        <table width="30%" border="1" cellpading="3">
          <tr>
            <th colspan="2">Registration</th>
          </tr>
          <tbody>
          <tr>
            <td>Login</td>
            <td><input type="text" name="login" value="" /></td>
          </tr>
          <tr>
            <td>Password</td>
            <td><input type="text" name="password" value=""></td>
          </tr>
          <tr>
            <td>Name</td>
            <td><input type="text" name="r_name" value=""></td>
          </tr>
          <tr>
            <td>Surname</td>
            <td><input type="text" name="surname" value=""></td>
          </tr>
          <tr>
            <td>Telephone</td>
            <td><input type="text" name="userTel" value=""></td>
          </tr>
          <tr>
            <td>City</td>
            <td><input type="text" name="c_name" value=""></td>
          </tr>
          <tr>
            <td>Street</td>
            <td><input type="text" name="s_name" value=""></td>
          </tr>
          <tr>
            <td colspan="2"><input type="submit" value="Check in" /></td>
          </tr>
          </tbody>
        </table>
      </form>
    </center>
  </c:otherwise>
</c:choose>

