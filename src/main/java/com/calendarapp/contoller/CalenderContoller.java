package com.calendarapp.contoller;

import com.calendarapp.dto.CalenderResponse;
import com.calendarapp.dto.CreateCalenderRequest;
import com.calendarapp.entity.Calender;
import com.calendarapp.service.CalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalenderContoller {

    private final CalenderService calenderService;

    //post 요청처리
    @PostMapping("/calenders")
    public ResponseEntity<CalenderResponse> createCalender(@RequestBody CreateCalenderRequest request) {
        CalenderResponse saved = calenderService.create(request);
        return ResponseEntity.ok(saved);
    }
}