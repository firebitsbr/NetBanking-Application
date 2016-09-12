<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
         <c:if test="${message != null}">
            <script type="text/javascript">
                alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
        <div class = "well">
            <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        </div>
         <center>
             <h2>CUSTOMER INFORMATION</h2>
             <table>
                 <br/><br/>
                 <tr>
                     <td align="center" height="40" width="350">
                         <a href="CustomerRegistration">CUSTOMER REGISTRATION</a>
                     </td>
                 </tr>
                 <tr>
                     <td align="center" height="40" width="350">
                         <a href="GetCustomer">FETCH CUSTOMER</a>
                     </td>
                 </tr>
                 <tr>
                     <td align="center" height="40" width="350">
                         <a href="getMiniStatementByCustomerId">CUTOMER MINI STATEMENT</a>
                     </td>
                 </tr>
             </table>
         </center>
         <b>Go to main page </b><a href="index.jsp" style="font-sise:18px"> click here</a></br></br>
         <br/><a href="logoutController" style="width:300px;"> LOGOUT</a>
    </body>
</html>