<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Mượn Sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; font-family: 'Segoe UI', sans-serif; }
        .container { max-width: 700px; margin-top: 50px; background-color: #fff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .form-label { font-weight: 500; }
    </style>
</head>
<body>

<div class="container">
    <h3 class="text-center mb-4">Mượn Sách</h3>
    <form action="${pageContext.request.contextPath}/borrow" method="post" onsubmit="return validateForm();">
        <div class="mb-3">
            <label for="borrowCode" class="form-label">Mã mượn sách:</label>
            <input type="text" class="form-control" id="borrowCode" name="borrowCode" placeholder="MS-0001" required pattern="MS-\d{4}">
        </div>

        <div class="mb-3">
            <label class="form-label">Tên sách:</label>
            <input type="text" class="form-control" value="${selectedBook.bookName}" readonly>
            <input type="hidden" name="bookId" value="${selectedBook.bookId}">
        </div>

        <div class="mb-3">
            <label for="studentId" class="form-label">Tên học sinh:</label>
            <select class="form-select" id="studentId" name="studentId" required>
                <option value="">-- Chọn học sinh --</option>
                <c:forEach var="student" items="${students}">
                    <option value="${student.studentId}">${student.fullName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Ngày mượn sách:</label>
            <input type="date" class="form-control" name="borrowDate" value="${currentDate}" readonly>
        </div>


        <div class="mb-3">
            <label for="returnDate" class="form-label">Ngày trả sách:</label>
            <input type="date" class="form-control" id="returnDate" name="returnDate" required>
        </div>

        <div class="d-flex justify-content-between mt-4">
            <button type="submit" class="btn btn-primary" name="action" value="borrow">Mượn sách</button>
            <button type="button" class="btn btn-secondary" onclick="confirmBack()">Trở về danh sách</button>
        </div>
    </form>
</div>

<script>
    function validateForm() {
        const borrowDate = new Date(document.querySelector('[name="borrowDate"]').value);
        const returnDate = new Date(document.getElementById("returnDate").value);
        if (returnDate < borrowDate) {
            alert("Ngày trả sách không được trước ngày mượn sách.");
            return false;
        }
        return true;
    }

    function confirmBack() {
        if (confirm("Bạn có chắc chắn muốn quay về danh sách?")) {
            window.location.href = "${pageContext.request.contextPath}/books";
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>