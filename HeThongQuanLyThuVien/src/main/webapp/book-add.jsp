<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm sách mới</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        .container { max-width: 600px; margin-top: 50px; background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
        .btn-submit { background-color: #0d6efd; color: #fff; }
        .btn-submit:hover { background-color: #0b5ed7; color: #fff; }
    </style>
</head>
<body>

<div class="container">
    <h3 class="mb-4 text-center">Thêm sách mới</h3>

    <form action="${pageContext.request.contextPath}/books?action=add" method="post">
        <div class="mb-3">
            <label for="bookCode" class="form-label">Mã sách</label>
            <input type="text" class="form-control" id="bookCode" name="bookCode" required>
        </div>

        <div class="mb-3">
            <label for="bookName" class="form-label">Tên sách</label>
            <input type="text" class="form-control" id="bookName" name="bookName" required>
        </div>

        <div class="mb-3">
            <label for="author" class="form-label">Tác giả</label>
            <input type="text" class="form-control" id="author" name="author" required>
        </div>

        <div class="mb-3">
            <label for="quantity" class="form-label">Số lượng</label>
            <input type="number" class="form-control" id="quantity" name="quantity" min="1" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Mô tả</label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
        </div>

        <button type="submit" class="btn btn-submit">Thêm sách</button>
        <a href="${pageContext.request.contextPath}/books" class="btn btn-secondary">Hủy</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

