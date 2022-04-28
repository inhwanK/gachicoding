package org.deco.gachicoding.dto.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NoticeUpdateRequestDto {

    private String notTitle;
    private String notContent;
    private Boolean notPin;

    @Builder
    public NoticeUpdateRequestDto(String notTitle, String notContent, Boolean notPin) {
        this.notTitle = notTitle;
        this.notContent = notContent;
        this.notPin = notPin;
    }
}
