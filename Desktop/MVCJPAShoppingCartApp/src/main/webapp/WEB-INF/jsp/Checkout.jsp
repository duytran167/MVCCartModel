<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Cart Page</title>
        <style>
            h1{
                text-align: center;
            }
            #table {
                border-collapse: collapse;
                width: 100%;
            }
            td,th{
                border: 1px solid black;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8">
                    <table id="table">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Product Description</th>
                                <th>Quantity</th>
                                <th>Unit Price</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="totalCart" value="0"></c:set>
                            <c:forEach var="cart" items="${cart.orderDetailsList}">
                                <c:set var="totalCart" value="${totalCart + cart.totalAmount}"></c:set>
                                    <tr>
                                        <td>${cart.product.productName}</td>
                                    <td>${cart.product.productDescription}</td>
                                    <td>${cart.quantity}</td>
                                    <td>${cart.product.unitPrice}</td>
                                    <td>${cart.totalAmount}</td>
                                </tr>
                            </tbody>
                        </c:forEach>
                        <tr>
                            <td colspan="4" align="right"><strong>Total Cart</strong></td>
                            <td><strong>${totalCart}</strong></td>
                        </tr>
                    </table>
                </div>
                <div class="col-md-4">
                    <mvc:form modelAttribute="orders" action="saveOrder" method="post" style="width: 100%;" >
                        <table>
                            <tr>
                                <td>Customer Name: </td>
                                <td><mvc:input path="customerName" required="true" /></td>
                            </tr>
                            <tr>
                                <td>Customer Address: </td>
                                <td><mvc:input path="customerAddress" required="true" /></td>
                            </tr>                          
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="Order Now">
                                </td>
                            </tr>
                        </table>
                    </mvc:form>
                </div>
            </div>
        </div><br>
        <form action="${pageContext.request.contextPath}" method="post" style="padding-left: 25%;">
            <input type="hidden" name="action" value="shopping">
            <input type="submit" value="Continue Shopping">
        </form>
    </body>
</html>
