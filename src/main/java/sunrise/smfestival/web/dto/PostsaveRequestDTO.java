package sunrise.smfestival.web.dto;

import lombok.Getter;
import org.springframework.lang.Nullable;
import sunrise.smfestival.entity.Post.Post;

@Getter
public class PostsaveRequestDTO {

    @Nullable
    private String author;
    private String pw;
    private String description;
    private int currentPage;

    public Post toEntity(){
       return Post.builder()
                .author(author)
                .pw(pw)
                .description(description)
                .build();
    }
}
