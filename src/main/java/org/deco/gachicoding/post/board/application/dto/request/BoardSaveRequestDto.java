package org.deco.gachicoding.post.board.application.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.deco.gachicoding.post.board.domain.Board;
import org.deco.gachicoding.user.domain.User;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardSaveRequestDto {

    @NotNull
    @ApiModelProperty(value = "작성자 아이디", required = true, example = "Swagger@swagger.com")
    private String userEmail;

    @NotNull
    @ApiModelProperty(value = "게시판 제목", required = true, example = "오늘 가입했습니다. 잘 부탁 드립니다.")
    private String boardTitle;

    @NotNull
    @ApiModelProperty(value = "게시판 내용", required = true, example = "안녕하세요 반갑습니다.")
    private String boardContent;

    @NotNull
    @ApiModelProperty(value = "게시판 카테고리", required = false, example = "개발 일상 토론")
    private String boardCategory;

    @Nullable
    @ApiModelProperty(value = "조회수", required = false, example = "0")
    private Long boardViews;

    @Nullable
    @ApiModelProperty(value = "태그 목록", required = false, example = "Java")
    private List<String> tags;
}