<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<%%@ taglib prefix="c" uri="jakarta.tags.core" %%>
<html><head><title>Edit customer</title></head><body>
<h1>Edit customer</h1>
<p><a href="customers">Back to customer list</a></p>
<c:if test="${requestScope['message'] != null}"><p>${requestScope["message"]}</p></c:if>
<form method="post">
<input type="hidden" name="action" value="edit">
<input type="hidden" name="id" value="${customer.id}">
<p>Name: <input type="text" name="name" value="${customer.name}"></p>
<p>Email: <input type="text" name="email" value="${customer.email}"></p>
<p>Address: <input type="text" name="address" value="${customer.address}"></p>
<input type="submit" value="Update customer">
</form></body></html>
