package novi.nl.library.controller;

import novi.nl.library.model.Book;

import novi.nl.library.model.Copy;
import novi.nl.library.service.BookService;

import novi.nl.library.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CopyService copyService;

    @GetMapping(value = "/books")
    public ResponseEntity<?> getBooks(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "publisher", required = false) String publisher,
            @RequestParam(name = "isbn", required = false) String isbn) {
        List<Book> books = bookService.getBooks(title, author, publisher, isbn);
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<?>  getBook(@PathVariable("id") long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping(value = "/books")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        long newId = bookService.save(book);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/books/{id}/description")
    public ResponseEntity<?> updateBookDescription(@PathVariable("id") long id, @RequestBody String description) {
        bookService.updateBookDescription(id, description);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/books/{id}/copies")
    public ResponseEntity<?> getBookCopies(@PathVariable("id") long id) {
        Book book = bookService.getBook(id);
        return ResponseEntity.ok(book.getCopies());
    }

    @PatchMapping(value = "/books/{id}/copies")
    public ResponseEntity<?> addBookCopy(@PathVariable("id") long id) {
        long newCopyId = bookService.addBookCopy(id);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCopyId)
                .toUri();

        return ResponseEntity.created(location).build();

    }




}
