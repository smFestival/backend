package sunrise.smfestival.entity.Like;

import org.springframework.data.jpa.repository.JpaRepository;
import sunrise.smfestival.entity.Post.Post;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes,Long> {

    Optional<Likes> findLikesByPostAndIp(Post post,String ip);
}
