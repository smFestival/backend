package sunrise.smfestival.web.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class PostDeleteRequestDTO {

    @NotEmpty
    private String pw;
}
