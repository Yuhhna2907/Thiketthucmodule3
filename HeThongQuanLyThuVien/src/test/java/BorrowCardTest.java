

import com.example.hethongquanlythuvien.DAO.ImplDAO.BorrowCardDAOImpl;
import com.example.hethongquanlythuvien.model.BorrowCard;

import java.time.LocalDate;

public class BorrowCardTest {
    public static void main(String[] args) {
        BorrowCardDAOImpl dao = new BorrowCardDAOImpl();

        // tạo dữ liệu mẫu
        Long bookId = 1L;      // phải tồn tại trong bảng book
        Long studentId = 2L;   // phải tồn tại trong bảng student
        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(7);

        BorrowCard card = new BorrowCard(null, bookId, studentId, false, borrowDate, returnDate);

        boolean ok = dao.insert(card);
        System.out.println("Insert BorrowCard thành công? " + ok);
    }
}


