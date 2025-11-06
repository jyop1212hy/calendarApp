package com.calendarapp.service;

import com.calendarapp.dto.*;
import com.calendarapp.entity.Calendar;
import com.calendarapp.entity.Comment;
import com.calendarapp.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    //일정 생성
    @Transactional
    public CreateCalendarResponse createCalendar(CreateCalendarRequest request) {
        Calendar calendar = new Calendar(
                request.getTitle(),
                request.getDetail(),
                request.getAuthorName(),
                request.getPassword()
        );

        Calendar saved = calendarRepository.save(calendar);
        return new CreateCalendarResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getDetail(),
                saved.getAuthorName(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }

    //단건 조회
    @Transactional(readOnly = true)
    public GetSingleCalendarResponse getCalendarById(Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("해당 ID의 일정이 존재하지 않습니다.")
        );
        List<CommentResponse> commentResponses = calendar.getComments().stream()
                .map(commentList ->new CommentResponse(
                commentList.getId(),
                commentList.getComment(),
                commentList.getAuthorName(),
                commentList.getCreatedAt(),
                commentList.getModifiedAt()
                ))
                .toList();

        return new GetSingleCalendarResponse(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getDetail(),
                calendar.getAuthorName(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt(),
                commentResponses
        );
    }

    //전체 조회 (작성자 필터 + 수정일 내림차순)
    @Transactional(readOnly = true)
    public List<GetAllCalendarResponse> getAllCalendars(String authorName) {
        List<Calendar> calendars = (authorName == null || authorName.isEmpty())
            ? calendarRepository.findAllByOrderByModifiedAtDesc()
            : calendarRepository.findAllByAuthorNameOrderByModifiedAtDesc(authorName);

        return calendars.stream()
                .map(calendarDate -> new GetAllCalendarResponse(
                    calendarDate.getId(),
                    calendarDate.getTitle(),
                    calendarDate.getDetail(),
                    calendarDate.getAuthorName(),
                    calendarDate.getCreatedAt(),
                    calendarDate.getModifiedAt()
                ))
                .toList();
    }

    //일정 수정(비밀번호 검증 포함)
    @Transactional
    public ModifyCalendarResponse updateCalendar(Long id, ModifyCalendarRequest password) {
        //수정할 일정 찾기
        Calendar calendar = calendarRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 일정이 없습니다.")
        );

        if (!calendar.getPassword().equals(password.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "누구냐 넌!");
        }

        calendar.updateCalendar(password.getTitle(), password.getAuthorName());

        return new ModifyCalendarResponse(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getDetail(),
                calendar.getAuthorName(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }

    //일정 삭제 (비밀번호 검증 후 삭제)
    @Transactional
    public void deleteCalendar(Long id, DeleteCalendarRequest password) {

        Calendar calendar = calendarRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 ID 일정이 없습니다.")
        );

        if (!calendar.getPassword().equals(password.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "누구냐 넌!");
        }

        calendarRepository.delete(calendar);
    }



}