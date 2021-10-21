package novi.nl.library.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.util.List;

@Validated
public class UserRequestDto {

    @NotBlank
    public String userName;

    @NotBlank
    @Size(min=6, max=30)
    public String password;

    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;

    @NotBlank
    @Email
    public String email;

    public List<String> authorities;

}
