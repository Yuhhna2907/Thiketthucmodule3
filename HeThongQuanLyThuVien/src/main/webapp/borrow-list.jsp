<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thống kê sách đang cho mượn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f2f5;
            font-family: 'Segoe UI', sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        .table th {
            white-space: nowrap;
        }
        .table td {
            vertical-align: middle;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="card p-4">
        <h4 class="text-center mb-4">📚 Thống kê sách đang cho mượn</h4>

        <form method="get" action="${pageContext.request.contextPath}/borrow">
            <div class="row g-3 mb-4">
                <div class="col-md-5">
                    <input type="text" name="bookName" class="form-control" placeholder="🔍 Tìm theo tên sách" value="${param.bookName}">
                </div>
                <div class="col-md-5">
                    <input type="text" name="studentName" class="form-control" placeholder="🔍 Tìm theo tên học sinh" value="${param.studentName}">
                </div>
                <div class="col-md-2 d-grid">
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </div>
            </div>
        </form>

        <div class="table-responsive">
            <table class="table table-bordered table-hover align-middle">
                <thead class="table-dark text-center">
                <tr>
                    <th>Mã mượn sách</th>
                    <th>Tên sách</th>
                    <th>Tác giả</th>
                    <th>Tên học sinh</th>
                    <th>Lớp</th>
                    <th>Ngày mượn</th>
                    <th>Ngày trả</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="borrow" items="${borrowList}">
                    <c:if test="${borrow.status == false}">
                        <tr>
                            <td>${borrow.borrowId}</td>
                            <td>${borrow.bookId}</td>
                            <td>${borrow.studentId}</td>
                            <td>${borrow.status}</td>
                            <td>${borrow.borrowDate}</td>
                            <td>${borrow.returnDate}</td>
                            <td class="text-center">
                                <button class="btn btn-success btn-sm" onclick="confirmReturn('${borrow.borrowId}')">Trả sách</button>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    function confirmReturn(borrowId) {
        if (confirm("Bạn có chắc chắn muốn trả sách này không?")) {
            window.location.href = "${pageContext.request.contextPath}/borrow?action=return&id=" + borrowId;
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>