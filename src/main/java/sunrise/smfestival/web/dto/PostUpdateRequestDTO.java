package sunrise.smfestival.web.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import sunrise.smfestival.entity.Post.Post;

import javax.validation.constraints.NotEmpty;

@Getter
public class PostUpdateRequestDTO {

    @NotEmpty
    private Long id;
    @NotEmpty
    private String pw;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
