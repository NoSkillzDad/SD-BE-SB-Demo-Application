package novi.nl.library.controller;

import novi.nl.library.model.BorrowedCopy;
import novi.nl.library.service.BorrowedCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class BorrowedCopyController {

    @Autowired
    private BorrowedCopyService borrowedCopyService;

    @GetMapping(value = "/borrowedCopies")
    public ResponseEntity<?> getBorrowedCopies() {
        List<BorrowedCopy> borrowedCopies = borrowedCopyService.getAllBorrowedCopies();
        return ResponseEntity.ok(borrowedCopies);
    }

    @GetMapping(value = "/borrowedCopies/{id}")
    public ResponseEntity<?> getBorrowedCopy(@PathVariable("id") long id) {
        return ResponseEntity.ok(borrowedCopyService.getBorrowedCopy(id));
    }

    @PostMapping(value = "/borrowedCopies")
    public ResponseEntity<?> createBorrowedCopy(@RequestBody BorrowedCopy borrowedCopy) {
        long newId = borrowedCopyService.save(borrowedCopy);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/borrowedCopies/{id}")
    public ResponseEntity<?> deleteBorrowedCopy(@PathVariable("id") long id) {
        borrowedCopyService.deleteById(id);
        return new ResponseEntity<>("BorrowedCopy deleted", HttpStatus.OK);
    }

}
