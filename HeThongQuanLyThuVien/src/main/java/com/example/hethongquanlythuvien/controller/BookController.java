package com.example.hethongquanlythuvien.controller;

import com.example.hethongquanlythuvien.ServiceLayer.ImplService.BookServiceImpl;
import com.example.hethongquanlythuvien.ServiceLayer.ImplService.StudentServiceImpl;
import com.example.hethongquanlythuvien.ServiceLayer.Inteface.BookService;
import com.example.hethongquanlythuvien.ServiceLayer.Inteface.StudentService;
import com.example.hethongquanlythuvien.model.Book;
import com.example.hethongquanlythuvien.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/books")
public class BookController extends HttpServlet {

    private final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                request.getRequestDispatcher("/book-add.jsp").forward(request, response);
                break;
            case "edit":
                Long bookId = Long.parseLong(request.getParameter("id"));
                Book book = bookService.getBookById(bookId);
                request.setAttribute("book", book);
                request.getRequestDispatcher("/book-edit.jsp").forward(request, response);
                break;
            case "delete":
                Long idToDelete = Long.parseLong(request.getParameter("id"));
                bookService.removeBook(idToDelete);
                response.sendRedirect(request.getContextPath() + "/books");
                break;
            case "borrow":
                Long bookIdToBorrow = Long.parseLong(request.getParameter("id"));
                Book selectedBook = bookService.getBookById(bookIdToBorrow);
                request.setAttribute("selectedBook", selectedBook);

                // Lấy ngày hiện tại theo định dạng dd/MM/yyyy
                String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                request.setAttribute("currentDate", currentDate);
                StudentService studentService = new StudentServiceImpl();
                List<Student> students = studentService.getAllStudents();
                request.setAttribute("students", students);

                request.getRequestDispatcher("/borrow.jsp").forward(request, response);
                break;
            default:
                List<Book> books = bookService.getAllBooks();
                request.setAttribute("books", books);
                request.getRequestDispatcher("/book-list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String bookCode = request.getParameter("bookCode");
            String bookName = request.getParameter("bookName");
            String author = request.getParameter("author");
            String description = request.getParameter("description");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Book book = new Book(null, bookCode, bookName, author, description, quantity);
            bookService.addBook(book);
            response.sendRedirect(request.getContextPath() + "/books");
        } else if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            String bookCode = request.getParameter("bookCode");
            String bookName = request.getParameter("bookName");
            String author = request.getParameter("author");
            String description = request.getParameter("description");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Book book = new Book(id, bookCode, bookName, author, description, quantity);
            bookService.updateBook(book);
            response.sendRedirect(request.getContextPath() + "/books");
        }
    }
}