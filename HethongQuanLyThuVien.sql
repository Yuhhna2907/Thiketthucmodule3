create database HeThongQuanLyThuVien;
use HeThongQuanLyThuVien;

CREATE TABLE books (
    book_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_code VARCHAR(20) NOT NULL UNIQUE,
    book_name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT NOT NULL,

    CONSTRAINT chk_book_quantity CHECK (quantity >= 0)
) ENGINE=InnoDB;

CREATE TABLE students (
    student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(20) NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    class_name VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE borrow_cards (
    borrow_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    status BOOLEAN NOT NULL,
    borrow_date DATE NOT NULL,
    return_date DATE,

    CONSTRAINT fk_borrow_book
        FOREIGN KEY (book_id)
        REFERENCES books(book_id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_borrow_student
        FOREIGN KEY (student_id)
        REFERENCES students(student_id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT chk_borrow_date
        CHECK (return_date IS NULL OR return_date >= borrow_date)
) ENGINE=InnoDB;

DELIMITER $$

CREATE TRIGGER trg_before_borrow
BEFORE INSERT ON borrow_cards
FOR EACH ROW
BEGIN
    DECLARE current_quantity INT;

    SELECT quantity INTO current_quantity
    FROM books
    WHERE book_id = NEW.book_id;

    IF current_quantity <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Sách đã hết, không thể mượn';
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_after_borrow
AFTER INSERT ON borrow_cards
FOR EACH ROW
BEGIN
    UPDATE books
    SET quantity = quantity - 1
    WHERE book_id = NEW.book_id;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_after_return
AFTER UPDATE ON borrow_cards
FOR EACH ROW
BEGIN
    IF OLD.status = FALSE AND NEW.status = TRUE THEN
        UPDATE books
        SET quantity = quantity + 1
        WHERE book_id = NEW.book_id;
    END IF;
END$$

DELIMITER ;