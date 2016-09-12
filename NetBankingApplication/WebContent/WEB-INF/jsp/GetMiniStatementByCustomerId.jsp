<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:if test="${sessionScope['id']== null}">
    <c:redirect url="login"/>
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
    <c:redirect url="userHomePage"/>
</c:if>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.css">
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <style type="text/css">
            .well {
                background-color:  #8ddfe1 ;
            }
        </style>
    </head>
    <body>
        <div class = "well">
            <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        </div>
        <h1>VIEW CUSTOMER BY ID</h1>
        <center> 
            <h2>Insert Id</h2>
            <form action="viewMiniStatementByCustomerId" method="get">
            <table>
                <tr>
                    <td>
                        <input type="text" name="customerId" placeholder="CUSTOMER ID" required>
                    </td>
                </tr>
            </table>
            </br></br>
            <input type="submit" name="view" value="VIEW">
            </form>
            <c:if test="${message != null}">
            <script type="text/javascript">
                alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
            <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a>
            <br/><a href="logoutController" style="width:300px;"> LOGOUT</a>
    </center>
</html>