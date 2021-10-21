package novi.nl.library.controller;

import novi.nl.library.model.Copy;
import novi.nl.library.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class CopyController {

    @Autowired
    private CopyService copyService;

    @GetMapping(value = "/copies")
    public ResponseEntity<?> getCopies() {
        List<Copy> copies = copyService.getAllCopies();
        return ResponseEntity.ok(copies);
    }

    @GetMapping(value = "/copies/{id}")
    public ResponseEntity<?> getCopy(@PathVariable("id") long id) {
        return ResponseEntity.ok(copyService.getCopy(id));
    }

    @PostMapping(value = "/copies")
    public ResponseEntity<?> createCopy(@RequestBody Copy copy) {
        long newId = copyService.save(copy);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/copies/{id}")
    public ResponseEntity<?> deleteCopy(@PathVariable("id") long id) {
        copyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
