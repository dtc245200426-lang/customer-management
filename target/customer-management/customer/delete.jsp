<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Delete Customer</title></head>
<body>
<h1>Delete Customer</h1>
<p>Are you sure you want to delete ${customer.name}?</p>
<form method="post" action="customers?action=delete">
<input type="hidden" name="id" value="${customer.id}">
<input type="submit" value="Delete">
</form>
<p><a href="customers">Cancel</a></p>
</body>
</html>
