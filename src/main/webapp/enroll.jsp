<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Enrollment</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container fade-in">
        <nav class="back-nav">
            <a href="index.html" class="btn btn-outline">&larr; Back to Home</a>
        </nav>
        
        <header class="page-header">
            <h1>Course Registration</h1>
        </header>

        <div class="form-card glass-panel">
            <c:if test="${not empty error}">
                <div class="alert error">${error}</div>
            </c:if>
            <c:if test="${not empty successMessage}">
                <div class="alert success">${successMessage}</div>
            </c:if>

            <form action="enroll" method="post">
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" id="fullName" name="fullName" placeholder="e.g. John Doe" required>
                    <small>Enter your full name so we can parse your first and last name.</small>
                </div>

                <div class="form-group">
                    <label>Select Courses</label>
                    <div class="checkbox-group">
                        <c:forEach var="course" items="${courses}">
                            <label class="checkbox-label">
                                <input type="checkbox" name="courseIds" value="${course.id}">
                                <span class="custom-checkbox"></span>
                                <div class="course-info">
                                    <strong>${course.id}</strong> - ${course.name} 
                                    <span class="badge">${course.credits} Credits</span>
                                </div>
                            </label>
                        </c:forEach>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Enroll Now</button>
            </form>
        </div>
    </div>
</body>
</html>
