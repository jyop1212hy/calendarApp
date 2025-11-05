package com.calendarapp.repository;

import com.calendarapp.entity.Calender;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CalenderRepository extends JpaRepository<Calender, Long> {

    // 전체 일정: 수정일 기준 내림차순 정렬
    List<Calender> findAllByOrderByModifiedAtDesc();

    // 특정 작성자 일정만: 수정일 기준 내림차순 정렬
    List<Calender> findAllByAuthorNameOrderByModifiedAtDesc(String authorName);
}
