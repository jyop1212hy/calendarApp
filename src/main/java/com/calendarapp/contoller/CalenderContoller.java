package com.calendarapp.contoller;

import com.calendarapp.dto.*;
import com.calendarapp.service.CalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calenders")
@RequiredArgsConstructor
public class CalenderContoller {

    private final CalenderService calenderService;

    //post 요청처리
    @PostMapping("/create")
    public CalenderResponse createCalender(
            @RequestBody CreateCalenderRequest request) {
        return calenderService.save(request);
    }

    //단건 조회
    @GetMapping("/{id}")
    public GetSingleCalenderResponse getSingleCalender(
            @PathVariable Long id) {
       return calenderService.getSingleCalender(id);
    }

    //전체 조회 (작성자 필터조건 추가)
    @GetMapping
    public List<GetAllCalenderResponse> getAllCalender(
            @RequestParam(required = false) String authorName) {
        return calenderService.getAll(authorName);
    }

    //일정 수정
    @PatchMapping("/{id}")
    public ModifyCalenderResponse modifyCalenderRequest(
            @PathVariable Long id,
            @RequestBody ModifyCalenderRequest request) {
        return calenderService.modifyCalender(id, request);
    }
}
