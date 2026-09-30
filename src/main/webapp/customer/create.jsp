<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Create Customer</title></head>
<body>
<h1>Create Customer</h1>
<form method="post" action="customers?action=create">
Name: <input type="text" name="name" required><br><br>
Email: <input type="email" name="email" required><br><br>
Address: <input type="text" name="address" required><br><br>
<input type="submit" value="Create">
</form>
<p><a href="customers">Back to customer list</a></p>
</body>
</html>
