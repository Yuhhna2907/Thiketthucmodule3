<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        .header, .footer { background-color: #343a40; color: #fff; padding: 15px 0; text-align: center; }
        .table-container { margin: 40px auto; max-width: 1000px; background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
        table th, table td { vertical-align: middle !important; }

    </style>
</head>
<body>

<div class="header">
    <h2>Thư viện trường học</h2>
</div>

<div class="table-container">
    <h4 class="mb-4 text-center">Danh sách sách</h4>

    <a href="${pageContext.request.contextPath}/books?action=add" class="btn btn-success mb-3">Thêm sách mới</a>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>Mã sách</th>
            <th>Tên sách</th>
            <th>Tác giả</th>
            <th>Số lượng</th>
            <th>Mô tả</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.bookCode}</td>
                <td>${book.bookName}</td>
                <td>${book.author}</td>
                <td>${book.quantity}</td>
                <td>${book.description}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/books?action=edit&id=${book.bookId}" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="${pageContext.request.contextPath}/books?action=delete&id=${book.bookId}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</a>
                    <a href="${pageContext.request.contextPath}/borrow?action=borrow&id=${book.bookId}" class="btn btn-primary btn-sm">Mượn</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/borrow?action=list"
       class="btn btn-primary mt-3">
        Danh sách mượn sách
    </a>
</div>

<div class="footer">
    <p>&copy; 2025 Thư viện trường học - All Rights Reserved</p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
