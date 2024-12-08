package com.example.firstproject.controller;

import com.example.firstproject.dto.post.PostForm;
import com.example.firstproject.entity.Post;
import com.example.firstproject.repository.PostRepository;
import com.example.firstproject.service.PostService;
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
    private PostService postService;
    @PostMapping("/post")
    public ResponseEntity<Post> create(@RequestBody PostForm dto){

        Post created = postService.create(dto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> GetPost(@PathVariable Long postId){

        Post post = postService.show(postId);
        return (post != null)?
                ResponseEntity.status(HttpStatus.OK).body(post) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/postlist")
    public List<Post> GetPostList(){
        return postService.list();
    }


    @PatchMapping("post/{postId}")
    public ResponseEntity<Post> update(@PathVariable Long postId, @RequestBody PostForm dto){
        Post updated = postService.update(postId, dto);

        return (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Post> delete(@PathVariable long postId)
    {
        Post deleted = postService.delete(postId);

        return (deleted != null)?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PostMapping("/post/transaction-test")
    public ResponseEntity<List<Post>>  transactionTest(@RequestBody List<PostForm> dtos){
        List<Post> createdList = postService.createPosts(dtos);

        return (createdList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
