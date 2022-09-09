package sunrise.smfestival.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunrise.smfestival.service.PostService;
import sunrise.smfestival.web.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public List<PostListResponseDTO> getPostList(@PageableDefault(sort = "postId",direction = Sort.Direction.DESC)
                           Pageable pageable){
        return postService.getPosts(pageable);
    }

    @GetMapping("/{id}")
    public PostsResponseDTO getPost(@PathVariable("id") Long id){
       return postService.getPost(id);
    }

    @PostMapping("/")
    public ResponseEntity<Long> posting(@RequestBody PostsaveRequestDTO requestDTO){

        Long id = postService.posting(requestDTO);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Long> editPost(@PathVariable("id") Long id, @RequestBody PostsaveRequestDTO requestDTO){

        postService.editPost(id,requestDTO);

        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") Long id, @RequestBody PostDeleteRequestDTO requestDTO){
        postService.deletePost(id,requestDTO);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<String> like(@PathVariable("id") Long id,
                                     HttpServletRequest httpServletRequest,
                                     @RequestBody PostLikeRequestDTO requestDTO){

        if(requestDTO.getCheck() == 1) {
            postService.like(id, httpServletRequest.getRemoteAddr());
        }

        else if(requestDTO.getCheck() == 0)
            postService.unlike(id,httpServletRequest.getRemoteAddr());

        return new ResponseEntity<>("정상 처리 되었습니다",HttpStatus.OK);
    }
}
