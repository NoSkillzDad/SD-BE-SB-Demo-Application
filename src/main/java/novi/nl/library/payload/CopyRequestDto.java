package novi.nl.library.payload;

import novi.nl.library.model.Book;

import javax.validation.constraints.NotBlank;

public class CopyRequestDto {

    @NotBlank
    private Long bookId;

}
