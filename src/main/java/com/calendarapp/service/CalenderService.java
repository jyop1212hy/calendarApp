package com.calendarapp.service;

import com.calendarapp.contoller.CalenderContoller;
import com.calendarapp.dto.CreateCalenderRequest;
import com.calendarapp.dto.CalenderResponse;
import com.calendarapp.entity.Calender;
import com.calendarapp.repository.CalenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;

    //생성
    @Transactional
    // Controller가 넘긴 Calender 엔티티를 DB에 저장
    public CalenderResponse create(CreateCalenderRequest requestDto) {
        Calender calender = new Calender(
                requestDto.getTitle(),
                requestDto.getDetail(),
                requestDto.getAuthorName(),
                requestDto.getPassword()
        );

        //비밀번호 제외 하고 DTO로 전달
        Calender savedCalender = calenderRepository.save(calender);
        return new CalenderResponse(
                savedCalender.getId(),
                savedCalender.getTitle(),
                savedCalender.getDetail(),
                savedCalender.getAuthorName(),
                savedCalender.getCreatedAt(),
                savedCalender.getModifiedAt()
                );
    }
}
