<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<%%@ taglib prefix="c" uri="jakarta.tags.core" %%>
<html><head><title>Customers</title></head><body>
<h1>Customers</h1>
<p><a href="customers?action=create">Create new customer</a></p>
<table border="1" cellpadding="5">
<tr><th>ID</th><th>Name</th><th>Email</th><th>Address</th><th>Actions</th></tr>
<c:forEach items="${customers}" var="customer">
<tr>
<td>${customer.id}</td>
<td>${customer.name}</td>
<td>${customer.email}</td>
<td>${customer.address}</td>
<td><a href="customers?action=view^&id=${customer.id}">View</a> | <a href="customers?action=edit^&id=${customer.id}">Edit</a> | <a href="customers?action=delete^&id=${customer.id}">Delete</a></td>
</tr>
</c:forEach>
</table></body></html>
