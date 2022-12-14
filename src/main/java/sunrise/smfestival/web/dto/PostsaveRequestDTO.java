package sunrise.smfestival.web.dto;

import lombok.Getter;
import org.springframework.lang.Nullable;
import sunrise.smfestival.entity.Post.Post;

import javax.validation.constraints.NotEmpty;

@Getter
public class PostsaveRequestDTO {

    private String author;
    @NotEmpty
    private String title;

    @NotEmpty
    private String pw;
    @NotEmpty
    private String description;

    public Post toEntity(){
       return Post.builder()
               .title(title)
                .pw(pw)
                .description(description)
                .build();
    }
}
