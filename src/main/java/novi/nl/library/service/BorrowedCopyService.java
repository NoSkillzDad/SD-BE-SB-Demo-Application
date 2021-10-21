package novi.nl.library.service;

import novi.nl.library.exception.RecordNotFoundException;
import novi.nl.library.model.BorrowedCopy;
import novi.nl.library.repository.BorrowedCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowedCopyService {

    @Autowired
    private BorrowedCopyRepository borrowedCopyRepository;

    public List<BorrowedCopy> getAllBorrowedCopies() {
        return borrowedCopyRepository.findAll();
    }

    public BorrowedCopy getBorrowedCopy(long id) {
        if (borrowedCopyRepository.existsById(id)) {
            return borrowedCopyRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException("No borrowedCopy with id " + id);
        }
    }

    public long save(BorrowedCopy borrowedCopy) {
        BorrowedCopy saved = borrowedCopyRepository.save(borrowedCopy);
        return saved.getId();
    }

    public void deleteById(long id) {
        if (borrowedCopyRepository.existsById(id)) {
            borrowedCopyRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("No borrowedCopy with id " + id);
        }
    }

}
