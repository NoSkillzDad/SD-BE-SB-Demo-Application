package novi.nl.library.repository;

import novi.nl.library.model.BorrowedCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowedCopyRepository extends JpaRepository<BorrowedCopy, Long> {

}
