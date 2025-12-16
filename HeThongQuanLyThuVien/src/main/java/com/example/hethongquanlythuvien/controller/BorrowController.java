package com.example.hethongquanlythuvien.controller;

import com.example.hethongquanlythuvien.ServiceLayer.ImplService.BookServiceImpl;
import com.example.hethongquanlythuvien.ServiceLayer.ImplService.BorrowCardServiceImpl;
import com.example.hethongquanlythuvien.ServiceLayer.ImplService.StudentServiceImpl;
import com.example.hethongquanlythuvien.ServiceLayer.Inteface.BookService;
import com.example.hethongquanlythuvien.ServiceLayer.Inteface.BorrowCardService;
import com.example.hethongquanlythuvien.ServiceLayer.Inteface.StudentService;
import com.example.hethongquanlythuvien.model.Book;
import com.example.hethongquanlythuvien.model.BorrowCard;
import com.example.hethongquanlythuvien.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/borrow")
public class BorrowController extends HttpServlet {

    private final BookService bookService = new BookServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final BorrowCardService borrowCardService = new BorrowCardServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "borrow": {
                Long bookId = Long.parseLong(request.getParameter("id"));
                Book selectedBook = bookService.getBookById(bookId);
                request.setAttribute("selectedBook", selectedBook);

                String currentDate = LocalDate.now().toString();
                request.setAttribute("currentDate", currentDate);

                List<Student> students = studentService.getAllStudents();
                request.setAttribute("students", students);

                request.getRequestDispatcher("/borrow.jsp").forward(request, response);
                break;
            }
            case "return": {
                Long borrowId = Long.parseLong(request.getParameter("id"));
                BorrowCard card = borrowCardService.getById(borrowId);
                if (card != null && !card.getStatus()) { // false = đang mượn
                    card.setStatus(true); // chuyển sang đã trả
                    borrowCardService.update(card);

                    Book book = bookService.getBookById(card.getBookId());
                    book.setQuantity(book.getQuantity() + 1);
                    bookService.updateBook(book);
                }
                response.sendRedirect(request.getContextPath() + "/borrow?action=list");
                break;
            }
            case "list":
            default: {
                String bookName = request.getParameter("bookName");
                String studentName = request.getParameter("studentName");

                List<BorrowCard> borrowList = borrowCardService.searchBorrowing(bookName, studentName);
                request.setAttribute("borrowList", borrowList);
                request.getRequestDispatcher("/borrow-list.jsp").forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("borrow".equals(action)) {
            Long bookId = Long.parseLong(request.getParameter("bookId"));
            Long studentId = Long.parseLong(request.getParameter("studentId"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate borrowDate = LocalDate.parse(request.getParameter("borrowDate"), formatter);
            LocalDate returnDate = LocalDate.parse(request.getParameter("returnDate"), formatter);
            Book book = bookService.getBookById(bookId);
            if (book.getQuantity() <= 0) {
                request.setAttribute("selectedBook", book);
                request.setAttribute("currentDate", borrowDate.toString());
                request.setAttribute("error", "Sách đã hết, không thể mượn.");
                List<Student> students = studentService.getAllStudents();
                request.setAttribute("students", students);
                request.getRequestDispatcher("/borrow.jsp").forward(request, response);
                return;
            }

            BorrowCard card = new BorrowCard(null, bookId, studentId, false, borrowDate, returnDate);
            boolean ok = borrowCardService.insert(card);

            if (ok) {
                book.setQuantity(book.getQuantity() - 1);
                bookService.updateBook(book);
                response.sendRedirect(request.getContextPath() + "/books");
            } else {
                request.setAttribute("error", "Không thể ghi thẻ mượn vào hệ thống.");
                request.getRequestDispatcher("/borrow.jsp").forward(request, response);
            }
        }
    }
}