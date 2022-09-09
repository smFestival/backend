package sunrise.smfestival.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunrise.smfestival.Exception.CustomException;
import sunrise.smfestival.Exception.ErrorCode;
import sunrise.smfestival.entity.Like.Likes;
import sunrise.smfestival.entity.Like.LikeRepository;
import sunrise.smfestival.entity.Post.Post;
import sunrise.smfestival.entity.Post.PostRepository;
import sunrise.smfestival.web.dto.PostListResponseDTO;
import sunrise.smfestival.web.dto.PostsResponseDTO;
import sunrise.smfestival.web.dto.PostDeleteRequestDTO;
import sunrise.smfestival.web.dto.PostsaveRequestDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Transactional(readOnly = true)
    public List<PostListResponseDTO> getPosts(Pageable pageable){
        Page<Post> page = postRepository.findAll(pageable);
        return page.map(PostListResponseDTO::new).getContent();
    }

    @Transactional(readOnly = true)
    public PostsResponseDTO getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.POST_NOT_FOUND));
        post.increaseViews();

        return PostsResponseDTO.builder()
                .post(post)
                .build();
    }

    @Transactional
    public Long posting(PostsaveRequestDTO requestDTO){
        Post post = postRepository.save(requestDTO.toEntity());

        return post.getPostId();
    }

    @Transactional
    public Long editPost(Long id, PostsaveRequestDTO requestDTO){
        Post post = postRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.POST_NOT_FOUND));

        if(post.getPw().equals(requestDTO.getPw())){

            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCHED);
        }

        post.updateContent(requestDTO.getTitle(),requestDTO.getDescription());

        return post.getPostId();
    }

    @Transactional
    public void deletePost(Long id, PostDeleteRequestDTO requestDTO){
        Post post = postRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.POST_NOT_FOUND));

        if(post.getPw().equals(requestDTO.getPw())){
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCHED);
        }

        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public Optional<Likes> isLikeExists(Post post, String ip){

        return likeRepository.findLikesByPostAndIp(post,ip);
    }

    @Transactional
    public void like(Long id,String ip){

        Post post = postRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.POST_NOT_FOUND));

        if(isLikeExists(post,ip).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_LIKED);
        }

        post.increaseLikes();


        Likes likes = Likes.builder()
                .ip(ip)
                .post(post)
                .build();

        likeRepository.save(likes);

    }

    public void unlike(Long id,String ip) {
        Post post = postRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.POST_NOT_FOUND));

        if(isLikeExists(post,ip).isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }
        post.decreaseLikes();

        Likes likes = likeRepository.findLikesByPostAndIp(post,ip).orElseThrow(()->new CustomException(ErrorCode.POST_NOT_FOUND));

        likeRepository.delete(likes);
    }
}
