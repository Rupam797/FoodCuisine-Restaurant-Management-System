<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
    <link rel="stylesheet" href="../Styles/reset.css">
    <link rel="stylesheet" href="../Styles/globalStyles.css">
    <link rel="stylesheet" href="../Styles/components.css">
    <link rel="stylesheet" href="../Styles/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/admin/users" class="back-link">
            <i class="fa-solid fa-arrow-left"></i> Back to User Details
        </a>
        <h1>Edit User</h1>
        <div class="form-container">
            <form action="${pageContext.request.contextPath}/admin/users" method="post">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="email" value="${param.email}">

                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" value="${param.name}" required>
                </div>

                <div class="form-group">
                    <label for="age">Age:</label>
                    <input type="number" id="age" name="age" value="${param.age}" required>
                </div>

                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <select id="gender" name="gender" required>
                        <option value="Male" ${param.gender == 'Male' ? 'selected' : ''}>Male</option>
                        <option value="Female" ${param.gender == 'Female' ? 'selected' : ''}>Female</option>
                        <option value="Other" ${param.gender == 'Other' ? 'selected' : ''}>Other</option>
                    </select>
                </div>

                <button type="submit" class="btn-submit">Update</button>
                <a href="${pageContext.request.contextPath}/admin/users" class="btn-cancel">Cancel</a>
            </form>
        </div>
    </div>
</body>
</html>
