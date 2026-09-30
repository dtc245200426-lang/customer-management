<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<html><head><title>Delete customer</title></head><body>
<h1>Delete customer</h1>
<h3>Are you sure you want to delete this customer?</h3>
<p>Name: ${customer.name}</p>
<p>Email: ${customer.email}</p>
<p>Address: ${customer.address}</p>
<form method="post">
<input type="hidden" name="action" value="delete">
<input type="hidden" name="id" value="${customer.id}">
<input type="submit" value="Delete customer">
</form>
<a href="customers">Back to customer list</a>
</body></html>
