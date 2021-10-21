package novi.nl.library.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String author;

    private String publisher;

    @Column(length = 40)
    private String isbn;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Copy> copies;

    // additional methods

    @JsonGetter("number_of_copies")
    public int getNumberOfCopies() {
        return copies.size();
    }

    @JsonGetter("number_of_available_copies")
    public int getNumberOfAvailableCopies() {
        return copies.stream().filter(c -> c.isAvailable()).toArray().length;
    }

}
