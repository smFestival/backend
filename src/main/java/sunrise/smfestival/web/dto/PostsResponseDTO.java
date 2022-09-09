package sunrise.smfestival.web.dto;

import lombok.Builder;
import lombok.Getter;
import sunrise.smfestival.entity.Post.Post;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDTO {

    private Long id;
    private String author;
    private String title;
    private String description;
    private int views;
    private int like;
    private LocalDateTime createdTime;

    @Builder
    public PostsResponseDTO(Post post){
        this.id = post.getPostId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.description = post.getDescription();
        this.views = post.getViews();
        this.like = post.getLikes();
        this.createdTime = post.getCreatedDate();
    }
}
