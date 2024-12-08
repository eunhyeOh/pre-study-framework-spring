package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@Getter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId; //fianl로 불변성 유지

    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String editPermission;
    @Column
    private String password;
    @Column
    private String regUserId;
    @Column
    private LocalDateTime regDate;
    @Column
    private String editUserId;
    @Column
    private LocalDateTime editDate;

    @Builder
    public Post(Long postId, String title, String content, String editPermission, String password,
                String regUserId, LocalDateTime regDate, String editUserId, LocalDateTime editDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.editPermission = editPermission;
        this.password = password;
        this.regUserId = regUserId;
        this.regDate = regDate;
        this.editUserId = editUserId;
        this.editDate = editDate;
    }

    public void patch(Post post){
        if(post.title != null)
            this.title = post.title;
        if(post.content != null)
            this.content = post.content;
        if(post.editPermission != null)
            this.editPermission = post.editPermission;
        if(post.password != null)
            this.password = post.password;
        if(post.regUserId != null)
            this.regUserId = post.regUserId;
        if(post.regDate != null)
            this.regDate = post.regDate;
        //this.editUserId = post.editUserId;
        //this.editDate = post.editDate;
    }
}
