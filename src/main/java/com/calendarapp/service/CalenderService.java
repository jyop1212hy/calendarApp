package com.calendarapp.service;

import com.calendarapp.dto.*;
import com.calendarapp.entity.Calender;
import com.calendarapp.repository.CalenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;

    //생성
    @Transactional
    // Controller가 넘긴 Calender 엔티티를 DB에 저장
    public CalenderResponse save(CreateCalenderRequest requestDto) {
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

    //단건 일정 조회
    @Transactional(readOnly = true)
    public GetSingleCalenderResponse getSingleCalender(Long id) {
        Calender calender = calenderRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("입력하신 ID는 없는 ID 입니다.")
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

    //전체 일정 조회
    @Transactional(readOnly = true)
    public List<GetAllCalenderResponse> getAll(String authorName) {
        List<Calender> calenders;

        // 작성자 이름이 없는경우
        if (authorName == null || authorName.isEmpty()) {
            calenders = calenderRepository.findAllByOrderByModifiedAtDesc();

            //작성자 이름이 있는 경우
        } else {
            calenders = calenderRepository.findAllByAuthorNameOrderByModifiedAtDesc(authorName);
        }

        //DTO 전달
        List<GetAllCalenderResponse> responses =  calenders.stream().map(calenderDate ->new GetAllCalenderResponse(
                calenderDate.getId(),
                calenderDate.getTitle(),
                calenderDate.getDetail(),
                calenderDate.getAuthorName(),
                calenderDate.getCreatedAt(),
                calenderDate.getModifiedAt()
        ))
        .toList();
        return responses;
    }

    //일정 수정
    @Transactional
    public ModifyCalenderResponse modifyCalender(Long id, ModifyCalenderRequest request) {
        //수정할 일정 찾기
        Calender calender = calenderRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("해당 ID의 일정이 없습니다."));

        //비밀번호 인증
        if (!calender.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("누구냐 넌?");
        }

        //제목, 작성자명 수정
        calender.modifyCalender(request.getTitle(), request.getAuthorName());

        //수정 후 데이터베이스 갱신
        return new ModifyCalenderResponse(
                calender.getId(),
                calender.getTitle(),
                calender.getDetail(),
                calender.getAuthorName(),
                calender.getCreatedAt(),
                calender.getModifiedAt());
    }

}