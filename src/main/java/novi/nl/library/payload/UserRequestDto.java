package novi.nl.library.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UserRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String email;

    private List<String> authorities;

}
