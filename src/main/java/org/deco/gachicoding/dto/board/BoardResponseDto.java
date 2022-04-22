package org.deco.gachicoding.dto.board;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.deco.gachicoding.domain.board.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {

    private Long boardIdx;
    private String boardTitle;
    private String boardContent;
    private Long boardViews;
    private LocalDateTime boardRegdate;
    private boolean boardPin;
    private boolean boardActivated;

    public BoardResponseDto(Board board) {
        this.boardIdx = board.getBoardIdx();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.boardViews = board.getBoardViews();
        this.boardRegdate = board.getBoardRegdate();
        this.boardPin = board.isBoardPin();
        this.boardActivated = board.isBoardActivated();
    }
}
