package novi.nl.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "copies")
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isAvailable = true;

    @ManyToOne
    private Book book;

    @OneToMany(mappedBy = "copy")
    @JsonBackReference
    private List<BorrowedCopy> borrowedBy = new ArrayList<>();

}
