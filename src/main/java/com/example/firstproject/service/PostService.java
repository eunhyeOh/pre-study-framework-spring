package com.example.firstproject.service;

import com.example.firstproject.dto.post.PostForm;
import com.example.firstproject.entity.Post;
import com.example.firstproject.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<Post> createPosts(List<PostForm> dtos) {
        //1. dto 묶을을 entity 묶음으로 변환
        List<Post> postList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        //2. entity묶을음 DB에 저장
        postList.stream().forEach(post -> postRepository.save(post));

        //3. 강제 예외 발생
        postRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("게시글 등록 실패!!"));

        //4. 결과 값 반환
        return postList;
    }
}
