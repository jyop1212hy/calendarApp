package com.calendarapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //댓글 내용
    @Column(length = 300, nullable = false)
    private String comment;

    //작성자명
    @Column(length = 50, nullable = false)
    private String authorName;

    //비밀번호 (응답 시 제외)
    @Column(length = 20, nullable = false)
    private String password;

    //일정과의 관계 (N : 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendarId", nullable = false)
    private Calendar calendar;

    public Comment(String comment, String authorName, String password, Calendar calendar) {
        this.comment = comment;
        this.authorName = authorName;
        this.password = password;
        this.calendar = calendar;
    }
}
