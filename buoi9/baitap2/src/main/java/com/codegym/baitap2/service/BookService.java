package com.codegym.baitap2.service;

import com.codegym.baitap2.exception.BookOutOfStockException;
import com.codegym.baitap2.exception.InvalidCodeException;
import com.codegym.baitap2.model.Book;
import com.codegym.baitap2.model.Borrowing;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService implements IBookService {
    private static final Map<Long, Book> books;
    private static final Map<String, Borrowing> borrowings = new HashMap<>();
    static {
        books = new HashMap<>();
        books.put(1L, new Book(1L, "Lập trình C++", "Nguyễn Văn A", 5));
        books.put(2L, new Book(2L, "Lập trình Java", "Nguyễn Văn B", 10));
        books.put(3L, new Book(3L, "Lập trình Python", "Nguyễn Văn C", 3));
        books.put(4L, new Book(4L, "Lập trình PHP", "Nguyễn Văn D", 0));
    }
    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Book findById(Long id) {
        return books.get(id);
    }

    @Override
    public String borrowBook(Long id) throws BookOutOfStockException {
        Book book = findById(id);
        if (book.getQuantity() == 0) {
            throw new BookOutOfStockException("Sách đã hết, không thể mượn!");
        }
        book.borrow();

        // Tạo mã mượn ngẫu nhiên 5 chữ số
        String code = String.valueOf(new Random().nextInt(90000) + 10000);

        // Lưu lại thông tin mượn
        Long borrowingId = (long) (borrowings.size() + 1);
        borrowings.put(code, new Borrowing(borrowingId, code, book.getId()));

        return code;
    }

    @Override
    public void returnBook(String code) throws InvalidCodeException {
        Borrowing borrowing = borrowings.get(code);
        if (borrowing == null) {
            throw new InvalidCodeException("Mã mượn sách không hợp lệ!");
        }
        Book book = findById(borrowing.getBookId());
        book.giveBack();

        // Xóa thông tin mượn
        borrowings.remove(code);
    }
}
