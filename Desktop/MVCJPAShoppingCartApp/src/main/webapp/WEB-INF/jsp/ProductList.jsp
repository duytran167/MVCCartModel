<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List All Product Page</title>
        <style>
            h1{
                text-align: center;
            }
            #table {
                margin-left: 17%;
                border-collapse: collapse;
                width: 60%;
            }
            td,th{
                border: 1px solid black;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <table id="table">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order date</th>
                    <th>Customer Name</th>
                    <th>View detail</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${product}">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.productName}</td>
                        <td>${product.productDescription}</td>
                        
                        
                        
                        <th><a href="${pageContext.request.contextPath}/addToCart/${product.id}"> Add To Cart</a></th>
                    </tr>
                </tbody>
            </c:forEach>
        </table>
    </body>
</html>
