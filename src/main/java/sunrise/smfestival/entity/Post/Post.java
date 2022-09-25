package sunrise.smfestival.entity.Post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String pw;

    @Column
    private String description;

    @Column
    private int views;

    @Column
    private int likes;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Builder
    public Post(Long postId,String title, String author, String pw, String description){
        this.title = title;
        this.postId = postId;
        this.author = author;
        this.pw = pw;
        this.description = description;
    }

    public void increaseLikes(){
        this.likes += 1;
    }

    public void decreaseLikes(){

        if(likes < 0)
            return;

        this.likes -= 1;
    }

    public void increaseViews(){
        this.views += 1;
    }

    public void updateContent(String title,String content){
        this.title = title;
        this.description = content;
    }
}