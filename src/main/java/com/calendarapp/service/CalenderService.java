package com.calendarapp.service;

import com.calendarapp.dto.*;
import com.calendarapp.entity.Calender;
import com.calendarapp.repository.CalenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;

    //일정 생성
    @Transactional
    public CalenderResponse createCalendar(CreateCalenderRequest request) {
        Calender calender = new Calender(
                request.getTitle(),
                request.getDetail(),
                request.getAuthorName(),
                request.getPassword()
        );

        Calender saved = calenderRepository.save(calender);
        return new CalenderResponse(
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
    public GetSingleCalenderResponse getCalendarById(Long id) {
        Calender calender = calenderRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("해당 ID의 일정이 존재하지 않습니다.")
        );

        return new GetSingleCalenderResponse(
                calender.getId(),
                calender.getTitle(),
                calender.getDetail(),
                calender.getAuthorName(),
                calender.getCreatedAt(),
                calender.getModifiedAt()
        );
    }

    //전체 조회 (작성자 필터 + 수정일 내림차순)
    @Transactional(readOnly = true)
    public List<GetAllCalenderResponse> getAllCalendars(String authorName) {
        List<Calender> calenders = (authorName == null || authorName.isEmpty())
            ? calenderRepository.findAllByOrderByModifiedAtDesc()
            : calenderRepository.findAllByAuthorNameOrderByModifiedAtDesc(authorName);

        return calenders.stream()
                .map(calenderDate -> new GetAllCalenderResponse(
                    calenderDate.getId(),
                    calenderDate.getTitle(),
                    calenderDate.getDetail(),
                    calenderDate.getAuthorName(),
                    calenderDate.getCreatedAt(),
                    calenderDate.getModifiedAt()
                ))
                .toList();
    }

    //일정 수정(비밀번호 검증 포함)
    @Transactional
    public ModifyCalenderResponse updateCalendar(Long id, ModifyCalenderRequest password) {
        //수정할 일정 찾기
        Calender calender = calenderRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 일정이 없습니다.")
        );

        if (!calender.getPassword().equals(password.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "누구냐 넌!");
        }

        calender.updateCalendar(password.getTitle(), password.getAuthorName());

        return new ModifyCalenderResponse(
                calender.getId(),
                calender.getTitle(),
                calender.getDetail(),
                calender.getAuthorName(),
                calender.getCreatedAt(),
                calender.getModifiedAt()
        );
    }

    //일정 삭제 (비밀번호 검증 후 삭제)
    @Transactional
    public void deleteCalendar(Long id, DeleteCalenderRequest password) {

        Calender calender = calenderRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 ID 일정이 없습니다.")
        );

        if (!calender.getPassword().equals(password.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "누구냐 넌!");
        }

        calenderRepository.delete(calender);
    }

}