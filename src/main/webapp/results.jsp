<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exam Results</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container fade-in">
        <nav class="back-nav">
            <a href="index.html" class="btn btn-outline">&larr; Back to Home</a>
        </nav>
        
        <header class="page-header">
            <h1>Exam Results Portal</h1>
        </header>

        <div class="search-card glass-panel">
            <form action="results" method="post" class="search-form">
                <div class="search-group">
                    <input type="text" name="fullName" placeholder="Enter Full Name..." 
                           value="${not empty searchedName ? searchedName : ''}" required>
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>
            </form>
        </div>

        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>

        <c:if test="${not empty generatedResult}">
            <div class="result-display fade-in">
                <!-- The dynamically generated HTML string is injected here -->
                ${generatedResult}
            </div>
        </c:if>
    </div>
</body>
</html>
