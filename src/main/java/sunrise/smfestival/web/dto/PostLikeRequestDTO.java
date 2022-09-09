package sunrise.smfestival.web.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class PostLikeRequestDTO {

    @NotEmpty
    private int check;

}
