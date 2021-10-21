package novi.nl.library.service;

import novi.nl.library.exception.BadRequestException;
import novi.nl.library.exception.RecordNotFoundException;
import novi.nl.library.model.Book;
import novi.nl.library.model.Copy;
import novi.nl.library.repository.BookRepository;
import novi.nl.library.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CopyRepository copyRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooks(String title, String author, String publisher, String isbn) {
        int numberOfSpecifiedArguments = 0;
        if (!(title == null || title.isBlank())) numberOfSpecifiedArguments++;
        if (!(author == null || author.isBlank())) numberOfSpecifiedArguments++;
        if (!(publisher == null || publisher.isBlank())) numberOfSpecifiedArguments++;
        if (!(isbn == null || isbn.isBlank())) numberOfSpecifiedArguments++;
        if (numberOfSpecifiedArguments > 1) {
            throw new BadRequestException("Too many search fields specified. Only one is allowed.");
        }

        if (!(title == null || title.isBlank())) {
            return bookRepository.findAllByTitleContainingIgnoreCase(title);
        } else if (!(author == null || author.isBlank())) {
            return bookRepository.findAllByAuthorContainingIgnoreCase(author);
        } else if (!(publisher == null || publisher.isBlank())) {
            return bookRepository.findAllByPublisherContainingIgnoreCase(publisher);
        } else if (!(isbn == null || isbn.isBlank())) {
            return bookRepository.findAllByIsbn(isbn);
        } else {
            return bookRepository.findAll();
        }
    }

    public Book getBook(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        }
        else {
            throw new RecordNotFoundException("No book with id " + id);
        }
    }

    public long save(Book book) {
        Book saved = bookRepository.save(book);
        return saved.getId();
    }

    public void deleteById(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("No book with id " + id);
        }
    }

    public void updateBookDescription(long id, String description) {
        Book book = getBook(id);
        book.setDescription(description);
        bookRepository.save(book);
    }

    public long addBookCopy(long bookId) {
        Book book = getBook(bookId);
        Copy copy = new Copy();
        copy.setBook(book);
        copy.setAvailable(true);
        Copy saved = copyRepository.save(copy);
        return saved.getId();
    }

}
