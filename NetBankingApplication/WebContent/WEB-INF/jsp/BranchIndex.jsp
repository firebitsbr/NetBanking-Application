<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
        <link rel="stylesheet" href="resource/css/bootstrap.css">
        <script src="resource/js/bootstrap.js"></script>
        <script src="resource/js/bootstrap1.js"></script>
        <style type="text/css">
            .well {
                background-color:  #8ddfe1 ;
            }
        </style>
    </head>
    <body">
        <div class = "well">
            <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        </div>
        <h1>BANK MANAGEMENT</h1>
        <br/><br/>
        <center>
            <c:if test="${message != null}">
                <script type="text/javascript">
                    alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>" );
                    windows.location.reload();
                </script>
            </c:if>
            <table>
                <tr> 
                    <td align="center" height="40" width="350">
                      <a href="addBranch"> ADD BRANCH</a>
                    </td>
                </tr>
                <tr>
                    <td align="center" height="40" width="350">
                        <a href="GetBranch">FETCH BRANCH</a>
                    </td>
                </tr> 
                <tr>
                    <td align="center" height="40" width="350">
                         <a href="AddAccount">ADD ACCOUNT</a>
                    </td>
                </tr> 
                <tr>
                    <td align="center" height="40" width="350">
                        <a href="ViewAccountByBranch">View  ACCOUNT By Branch</a>
                    </td>
                </tr> 
                <tr> 
                    <td align="center" height="40" width="350">
                      <a href="addUserRole"> Add User Role</a>
                    </td>
                </tr> 
            </table> 
        </center>
        <b>Go to main page </b><a href="index.jsp" style="font-size:18px"> click here</a></br></br>
    </body>
</html> 