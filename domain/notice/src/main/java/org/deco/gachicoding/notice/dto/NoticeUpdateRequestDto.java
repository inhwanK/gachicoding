package org.deco.gachicoding.notice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeUpdateRequestDto {

    private String notTitle;
    private String notContent;
    private Boolean notPin;

    public NoticeUpdateRequestDto(String notTitle, String notContent, Boolean notPin) {
        this.notTitle = notTitle;
        this.notContent = notContent;
        this.notPin = notPin;
    }
}
