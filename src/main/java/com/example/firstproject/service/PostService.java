package com.example.firstproject.service;

import com.example.firstproject.dto.post.PostForm;
import com.example.firstproject.entity.Post;
import com.example.firstproject.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post create(PostForm dto){
        // DTO를 엔티티로 변환
        Post post = dto.toEntity();

        // DB에 저장
        return postRepository.save(post);
    }
    public List<Post> list(){
        //post 테이블 전체 조회
        return postRepository.findAll();
    }

    public Post show(Long postId){
        //postId로 DB조회
        return postRepository.findById(postId).orElse(null);
    }

    public Post update(Long postId, PostForm dto){
        //1. DTO > Entity 변환
        Post post = dto.toEntity();
        log.info("postId : {}, form : {}", postId, dto);

        //2. DB에서 대상 Entity 조회
        Post target = postRepository.findById(postId).orElse(null);

        if (target == null || postId != post.getPostId()){
            //3. 잘못된 요청 처리
            log.info("잘못된 요청! postId : {}, form : {}", postId, dto.toString());
            return null;
        }
        else {
            //4. 업데이트
            target.patch(post); //기존 데이터 추가
            return postRepository.save(target);
        }
    }

    public Post delete(long postId) {

        //1. 대상 찾기
        Post target = postRepository.findById(postId).orElse(null);

        if(target == null){
            //2. 잘못된 요청 처리
            return null;
        }else{
            //3. 게시글 삭제 처리
            postRepository.delete(target);
            return target;
        }
    }
}
