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
<h2>TRANSACTION DETAILS</h2>
<table border="2" style="padding: 1px;">

<tr>
<th>AccountNumber</th>
<th>Account Type</th>
<th>Amount</th>
<th>Transaction Date </th>
</tr>

<c:forEach var="s" items="${ls}">
  
      <tr>
      <td>
      <h3><c:out value="${s.getAccountnumber()}"></c:out></h3>
      </td>
      <td>
      <h3><c:out value="${s.getAccount_type()}"></c:out></h3>
      </td>
      <td>
      <h3><c:out value="${s.getAmount()}"></c:out></h3>
      </td>
      <td>
      <h3><c:out value="${s.getTime()}"></c:out></h3>
      </td>
      </tr>
      </c:forEach>
      </table><br><br>
      <input type="button" onclick="window.print();" value="Print"> 
      </center>
      
</body>
</html>