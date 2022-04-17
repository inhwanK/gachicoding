package org.deco.gachicoding.controller;

import lombok.RequiredArgsConstructor;
import org.deco.gachicoding.notice.dto.NoticeDetailResponseDto;
import org.deco.gachicoding.notice.dto.NoticeListResponseDto;
import org.deco.gachicoding.notice.dto.NoticeSaveRequestDto;
import org.deco.gachicoding.notice.dto.NoticeUpdateRequestDto;
import org.deco.gachicoding.notice.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RestNoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice/detail/{idx}")
    public NoticeDetailResponseDto findNoticeDetailById(@PathVariable Long idx) {
        return noticeService.findNoticeDetailById(idx);
    }

    @GetMapping("/notice/list/{page}")
    public Page<NoticeListResponseDto> findNoticeByKeyword(@RequestParam(value = "keyword", defaultValue = "") String keyword, @PathVariable int page) {
        return noticeService.findNoticeByKeyword(keyword, page);
    }

    @PostMapping("/notice")
    public Long registerNotice(@RequestBody NoticeSaveRequestDto dto) {
        return noticeService.registerNotice(dto);
    }

    @PutMapping("/notice/update/{idx}")
    public NoticeDetailResponseDto updateNotice(@PathVariable Long idx, @RequestBody NoticeUpdateRequestDto
            dto){
        return noticeService.updateNoticeById(idx, dto);
    }

    @PutMapping("/notice/delete/{idx}")
    public Long deleteUser(@PathVariable Long idx){
        return noticeService.deleteNoticeById(idx);
    }
}
