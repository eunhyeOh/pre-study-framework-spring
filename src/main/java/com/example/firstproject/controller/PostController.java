package com.example.firstproject.controller;

import com.example.firstproject.dto.post.PostForm;
import com.example.firstproject.entity.Post;
import com.example.firstproject.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/post")
    public Post create(@RequestBody PostForm form){

        //1.DTO를 엔티티로 변환
        Post post = form.toEntity();
        log.info(post.toString());

        //2.Repository로 Entity를 DB에 저장
        Post saved = postRepository.save(post);
        log.info(saved.toString());
        
        return saved;
    }

    @GetMapping("/post/{postId}")
    public Post GetPost(@PathVariable Long postId){
        //postId로 DB조회
        Post postEntity =  postRepository.findById(postId).orElse(null);

        return postEntity;
    }

    @GetMapping("/postlist")
    public List<Post> GetPostList(){
        //post 테이블 전체 조회
        return postRepository.findAll();
    }


    @PatchMapping("post/{postId}")
    public ResponseEntity<Post> update(@PathVariable Long postId, @RequestBody PostForm form){
        //1. DTO > Entity 변환
        Post post = form.toEntity();
        log.info("postId : {}, form : {}", postId, form);

        //2. DB에서 대상 Entity 조회
        Post target = postRepository.findById(postId).orElse(null);

        if(target == null || postId != post.getPostId()){
            //3.잘못된 요청 처리
            log.info("잘못된 요청! postId : {}, form : {}", postId, form.toString());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }else{
            //4.업데이트 & 정상응답하기
            target.patch(post); //기존 데이터 추가
            Post updated = postRepository.save(target);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updated);
        }
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Post> delete(@PathVariable long postId)
    {

        // 인증된 사용자 정보 가져오기
        //String loggedInUserId = authentication.getName();  // 로그인한 사용자 ID
        String loggedInUserId = "yaong";
        //postId 게시글의 작성자id와 로그인id 같은지 비교

        //1. 대상 찾기
        Post target = postRepository.findById(postId).orElse(null);

        //2. 잘못된 요청 처리
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else{
            //3. 게시글 삭제 처리
            postRepository.deleteById(postId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
