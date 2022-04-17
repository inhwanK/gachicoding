package org.deco.gachicoding.notice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.deco.gachicoding.notice.domain.Notice;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class NoticeSaveRequestDto {
    @NotNull
    private Long userIdx;

    @NotNull
    private String notTitle;

    @NotNull
    private String notContent;

    @Nullable
    private Boolean notPin;

    @Builder
    public NoticeSaveRequestDto(Long userIdx, String notTitle, String notContent, Boolean notPin) {
        this.userIdx = userIdx;
        this.notTitle = notTitle;
        this.notContent = notContent;
        this.notPin = notPin;
    }

    public Notice toEntity(){
        return Notice.builder()
                .notTitle(notTitle)
                .notContent(notContent)
                .notPin(notPin)
                .build();
    }
}
