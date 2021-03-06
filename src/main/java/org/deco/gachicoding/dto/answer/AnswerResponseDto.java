package org.deco.gachicoding.dto.answer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.deco.gachicoding.domain.answer.Answer;
import org.deco.gachicoding.domain.question.Question;
import org.deco.gachicoding.domain.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AnswerResponseDto {
    private Long ansIdx;
    private String userEmail;
    private String userNick;
    private String userPicture;
    private Long queIdx;
    private String ansContent;
    private Boolean ansSelect;
    private Boolean ansActivated;
    private LocalDateTime ansRegdate;

    @Builder
    public AnswerResponseDto(Answer answer) {
//        setWriterInfo(answer);
        this.userEmail = answer.getWriter().getUserEmail();
        this.userNick = answer.getWriter().getUserNick();
        this.userPicture = answer.getWriter().getUserPicture();
        setQuestionInfo(answer);
        this.ansIdx = answer.getAnsIdx();
        this.ansContent = answer.getAnsContent();
        this.ansSelect = answer.getAnsSelect();
        this.ansActivated = answer.getAnsActivated();
        this.ansRegdate = answer.getAnsRegdate();
    }

    private void setQuestionInfo(Answer answer) {
        Question question = answer.getQuestion();
        this.queIdx = question.getQueIdx();
    }

//    private void setWriterInfo(Answer answer) {
//        User user = answer.getWriter();
//        this.writerIdx = user.getUserIdx();
//    }

}
