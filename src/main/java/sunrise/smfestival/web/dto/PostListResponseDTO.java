package sunrise.smfestival.web.dto;

import lombok.Builder;
import lombok.Getter;
import sunrise.smfestival.entity.Post.Post;

import java.time.LocalDateTime;

@Getter
public class PostListResponseDTO {

    private Long id;
    private String title;
    private String author;
    private String description;
    private int views;
    private int like;
    private LocalDateTime createdTime;

    @Builder
    public PostListResponseDTO(Post post){
        this.id = post.getPostId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.description = post.getDescription();
        this.views = post.getViews();
        this.like = post.getLikes();
        this.createdTime = post.getCreatedDate();
    }
}
