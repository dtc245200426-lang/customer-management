<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Edit Customer</title></head>
<body>
<h1>Edit Customer</h1>
<form method="post" action="customers?action=edit">
<input type="hidden" name="id" value="${customer.id}">
Name: <input type="text" name="name" value="${customer.name}" required><br><br>
Email: <input type="email" name="email" value="${customer.email}" required><br><br>
Address: <input type="text" name="address" value="${customer.address}" required><br><br>
<input type="submit" value="Update">
</form>
<p><a href="customers">Back to customer list</a></p>
</body>
</html>
