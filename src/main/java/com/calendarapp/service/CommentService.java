package com.calendarapp.service;

import com.calendarapp.dto.CommentRequest;
import com.calendarapp.dto.CommentResponse;
import com.calendarapp.entity.Calendar;
import com.calendarapp.entity.Comment;
import com.calendarapp.repository.CalendarRepository;
import com.calendarapp.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CalendarRepository calendarRepository;
    private final CommentRepository commentRepository;


//댓글 생성
    @Transactional
    public CommentResponse createComment(Long calendarId, CommentRequest request) {
        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 일정이 없습니다.")
                );

                List<Comment> comments = commentRepository.findAllByCalendar(calendar);
        if (comments.size() >= 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일정 1개당 댓글은 최대 10개까지만 등록 가능합니다.");
        }

        Comment comment = new Comment(
                request.getComment(),
                request.getAuthorName(),
                request.getPassword(),
                calendar
        );

        Comment saved = commentRepository.save(comment);
        return new CommentResponse(
                saved.getId(),
                saved.getComment(),
                saved.getAuthorName(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }
}
