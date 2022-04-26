package org.deco.gachicoding.dto.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.deco.gachicoding.domain.notice.Notice;
import org.deco.gachicoding.domain.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NoticeResponseDto {

    private Long notIdx;
    private Long userIdx;
    private String userNick;
    private String userPicture;
    private String notTitle;
    private String notContent;
    private int notViews;
    private Boolean notPin;
    private LocalDateTime notRegdate;

    @Builder
    public NoticeResponseDto(Notice notice) {
        setWriterInfo(notice);
        this.notIdx = notice.getNotIdx();
        this.notTitle = notice.getNotTitle();
        this.notContent = notice.getNotContent();
        this.notPin = notice.getNotPin();
        this.notRegdate = notice.getNotRegdate();
    }

    private void setWriterInfo(Notice notice) {
        User user = notice.getUser();
        this.userIdx = user.getUserIdx();
        this.userNick = user.getUserNick();
        this.userPicture = user.getUserPicture();
    }
}
