package novi.nl.library.service;

import novi.nl.library.exception.RecordNotFoundException;
import novi.nl.library.model.Book;
import novi.nl.library.model.Copy;
import novi.nl.library.payload.CopyRequestDto;
import novi.nl.library.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyService {

    @Autowired
    private BookService bookService;

    @Autowired
    private CopyRepository copyRepository;

    public List<Copy> getAllCopies() {
        return copyRepository.findAll();
    }

    public Copy getCopy(long id) {
        if (copyRepository.existsById(id)) {
            return copyRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException("No copy with id " + id);
        }
    }

    public long save(Copy copy) {
        Copy saved = copyRepository.save(copy);
        return saved.getId();
    }

    public void deleteById(long id) {
        if (copyRepository.existsById(id)) {
            copyRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("No copy with id " + id);
        }
    }

}
