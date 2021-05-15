<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
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
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Product Description</th>
                    <th>Quantity</th>
                    <th>Unit Price</th>
                    <th>Total</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:set var="totalCart" value="0"></c:set>
                <c:forEach var="cart" items="${cart.orderDetailsList}">
                    <c:set var="totalCart" value="${totalCart + cart.totalAmount}"></c:set>
                    
                        <tr>
                            <td>${cart.product.id}</td>
                        <td>${cart.product.productName}</td>
                        <td>${cart.product.productDescription}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/update" method="post">
                                <input type="hidden" name="id" value="${cart.product.id}">
                                <input type="text" name="quantity" value="${cart.quantity}" id="quantity" style="width: 30px;">                               
                               <input type="submit" value="Update">
                            </form>
                        </td>
                        <td>${cart.product.unitPrice}</td>
                        <td>${cart.totalAmount}</td>
                        <td><a href="${pageContext.request.contextPath}/remove/${cart.product.id}">Delete</a></td>
                    </tr>
                </tbody>
            </c:forEach>
            <tr>
                <td colspan="5" align="right"><strong>Total Cart</strong></td>
                <td><strong>${totalCart}</strong></td>
                <td colspan="1"></td>
            </tr>
            <tr>
                <td colspan="3">
                    <form action="${pageContext.request.contextPath}" method="post">
                        <input type="hidden" name="action" value="shopping">
                        <input type="submit" value="Continue Shopping">
                    </form>
                </td>
                <td colspan="4">
                    <form action="${pageContext.request.contextPath}/checkout" method="get">
                        <input type="hidden" name="action" value="checkout">
                        <input type="submit" value="Checkout">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
