package com.calendarapp.contoller;

import com.calendarapp.dto.*;
import com.calendarapp.service.CalendarService;
import com.calendarapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendars")
@RequiredArgsConstructor
public class CalendarContoller {

    private final CalendarService calendarService;
    private final CommentService commentService;

    //일정 생성
    @PostMapping("/create")
    public CreateCalendarResponse createCalendar(@RequestBody CreateCalendarRequest request) {
        return calendarService.createCalendar(request);
    }

    //단건 조회
    @GetMapping("/{id}")
    public GetSingleCalendarResponse getCalendarById(@PathVariable Long id) {
       return calendarService.getCalendarById(id);
    }

    //전체 조회 (작성자명 필터 조건 추가)
    @GetMapping
    public List<GetAllCalendarResponse> getAllCalendars(
            @RequestParam(required = false) String responses) {
        return calendarService.getAllCalendars(responses);
    }

    //일정 수정 (제목, 작성자명만 변경 가능)
    @PatchMapping("/{id}")
    public ModifyCalendarResponse updateCalendar(
            @PathVariable Long id,
            @RequestBody ModifyCalendarRequest request) {
        return calendarService.updateCalendar(id, request);
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendar(
            @PathVariable Long id,
            @RequestBody DeleteCalendarRequest password) {
        calendarService.deleteCalendar(id,password);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //댓글 생성
    @PostMapping("{id}/comment")
    public CommentResponse commentCalendar(
            @PathVariable Long id,
            @RequestBody CommentRequest request) {
        return commentService.createComment(id, request);
    }

}
