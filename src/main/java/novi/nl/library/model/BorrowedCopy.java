package novi.nl.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "borrowed_copies")
public class BorrowedCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dateBorrowedOn;

    private LocalDateTime dateReturnedBy;

    private LocalDateTime dateReturnedOn;

    @ManyToOne
    @JsonManagedReference
    private Copy copy;

    @ManyToOne
    private Member member;

}