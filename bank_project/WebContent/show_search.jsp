<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h2> SEARCH CUSTOMER BY ACCOUNTNUMBER</h2>
<c:forEach var="s" items="${list}">
      <table>
      <tr>
      <td>
      <h3><c:out value="${s.getAccountNumber()}"></c:out></h3>
      </td>
      <td>
      <h3><c:out value="${s.getName()}"></c:out></h3>
      </td>
      <td>
      <h3><c:out value="${s.getBalance()}"></c:out></h3>
      </td>
      </tr>
      </table>
      </c:forEach>
      </center>
</body>
</html>