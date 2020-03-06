package pl.javastart.service;

import org.springframework.stereotype.Component;
import pl.javastart.model.Book;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Component
public class BookRepository implements GenericRepository<String, Book> {

    private List<Book> books;

    public BookRepository() {
        books = new LinkedList<>();
    }

    @Override
    public Book get(String isbn) {
        if (isbn == null || (isbn.length() != 13)) {
            throw new IllegalArgumentException("You have to provide valid ISBN number");
        }
        Book find = null;
        try {
            find = books.stream()
                    .filter(book -> book.getIsbn().equals(isbn))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            System.err.println("There isn't such ISBN in a library.");
        }
        randomPause(300);
        return find;
    }

    @Override
    public void add(Book book) {
        if (book == null || book.getIsbn() == null || book.getAuthor() == null || book.getTitle() == null) {
            throw new IllegalArgumentException("Book cannot have null fields");
        }
        randomPause(1000);
        books.add(book);
    }

    public void randomPause(int maxPause) {
        try {
            Thread.sleep(new Random().nextInt(maxPause));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

