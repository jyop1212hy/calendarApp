package com.calendarapp.repository;

import com.calendarapp.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    // 전체 일정: 수정일 기준 내림차순 정렬
    List<Calendar> findAllByOrderByModifiedAtDesc();

    // 특정 작성자 일정만: 수정일 기준 내림차순 정렬
    List<Calendar> findAllByAuthorNameOrderByModifiedAtDesc(String authorName);

    //댓글 한번에 가져오기
    @Query("SELECT c FROM Calendar c LEFT JOIN FETCH c.comments WHERE c.id = :id")
    Optional<Calendar> findByIdWithComments(@Param("id") Long id);

}