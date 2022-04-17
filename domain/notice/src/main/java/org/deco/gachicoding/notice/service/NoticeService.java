package org.deco.gachicoding.notice.service;

import org.deco.gachicoding.notice.domain.Notice;
import org.deco.gachicoding.notice.dto.NoticeDetailResponseDto;
import org.deco.gachicoding.notice.dto.NoticeListResponseDto;
import org.deco.gachicoding.notice.dto.NoticeSaveRequestDto;
import org.deco.gachicoding.notice.dto.NoticeUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NoticeService {
    Optional<Notice> findById(Long idx);

    NoticeDetailResponseDto findNoticeDetailById(Long idx);

    Page<NoticeListResponseDto> findNoticeByKeyword(String keyword, int page);

    Long registerNotice(NoticeSaveRequestDto dto);

    NoticeDetailResponseDto updateNoticeById(Long idx, NoticeUpdateRequestDto dto);

    Long deleteNoticeById(Long idx);
}
