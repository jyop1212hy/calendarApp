package com.calendarapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "calenders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Calender extends BaseEntity {

    //속성
    //고유 식별ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //일정 제목
    @Column(length = 50, nullable = false)
    private String title;

    //일정 내용
    @Column(length = 200, nullable = false)
    private String detail;

    //작성자명
    @Column(length = 50, nullable = false)
    private String authorName;

    //비밀번호 (응답 시 제외 예정)
    @Column(length = 20, nullable = false)
    private String password;

    //생성자
    public Calender(String title, String detail, String authorName, String password) {
        this.title = title;
        this.detail = detail;
        this.authorName = authorName;
        this.password = password;
    }

    //일정 수정 기능(제목, 작성자명만 변경)
    public void updateCalendar(String title, String authorName) {
        this.title = title;
        this.authorName = authorName;
    }
}
