package com.calendarapp.repository;

import com.calendarapp.entity.Calendar;
import com.calendarapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByCalendar(Calendar calendar);
}
