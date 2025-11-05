package com.calendarapp.contoller;

import com.calendarapp.dto.*;
import com.calendarapp.service.CalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calenders")
@RequiredArgsConstructor
public class CalenderContoller {

    private final CalenderService calenderService;

    //일정 생성
    @PostMapping("/create")
    public CreateCalenderResponse createCalender(@RequestBody CreateCalenderRequest request) {
        return calenderService.createCalendar(request);
    }

    //단건 조회
    @GetMapping("/{id}")
    public GetSingleCalenderResponse getCalendarById(@PathVariable Long id) {
       return calenderService.getCalendarById(id);
    }

    //전체 조회 (작성자명 필터 조건 추가)
    @GetMapping
    public List<GetAllCalenderResponse> getAllCalendars(
            @RequestParam(required = false) String responses) {
        return calenderService.getAllCalendars(responses);
    }

    //일정 수정 (제목, 작성자명만 변경 가능)
    @PatchMapping("/{id}")
    public ModifyCalenderResponse updateCalendar(
            @PathVariable Long id,
            @RequestBody ModifyCalenderRequest request) {
        return calenderService.updateCalendar(id, request);
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendar(
            @PathVariable Long id,
            @RequestBody DeleteCalenderRequest password) {
        calenderService.deleteCalendar(id,password);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
