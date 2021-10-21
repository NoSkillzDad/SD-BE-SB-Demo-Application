package novi.nl.library.repository;

import novi.nl.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthorContainingIgnoreCase(String author);
    List<Book> findAllByTitleContainingIgnoreCase(String title);
    List<Book> findAllByPublisherContainingIgnoreCase(String publisher);
    List<Book> findAllByIsbn(String isbn);

}
