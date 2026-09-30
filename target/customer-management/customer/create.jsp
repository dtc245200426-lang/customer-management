<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<%%@ taglib prefix="c" uri="jakarta.tags.core" %%>
<html><head><title>Create customer</title></head><body>
<h1>Create customer</h1>
<p><a href="customers">Back to customer list</a></p>
<c:if test="${requestScope['message'] != null}"><p>${requestScope["message"]}</p></c:if>
<form method="post">
<input type="hidden" name="action" value="create">
<p>Name: <input type="text" name="name"></p>
<p>Email: <input type="text" name="email"></p>
<p>Address: <input type="text" name="address"></p>
<input type="submit" value="Create customer">
</form></body></html>
