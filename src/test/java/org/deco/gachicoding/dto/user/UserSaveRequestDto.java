package org.deco.gachicoding.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.deco.gachicoding.domain.user.User;
import org.deco.gachicoding.domain.user.UserRole;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveRequestDto {

    @NotNull
    private String userName;

    @NotNull
    private String userNick;

    @NotNull
    @Email(message = "올바른 형식의 아이디가 아닙니다.")
    private String userEmail;

    @NotNull
    private String userPassword;

    @Nullable
    private String userPicture;

    @Builder
    public UserSaveRequestDto(String userName, String userNick, String userEmail, String userPassword, String userPicture) {
        this.userName = userName;
        this.userNick = userNick;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPicture = userPicture;
    }

    public User toEntity(){
        return User.builder()
                .userName(userName)
                .userNick(userNick)
                .userEmail(userEmail)
                .userPassword(userPassword)
                .userPicture(userPicture)
                .build();
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        userPassword = passwordEncoder.encode(userPassword);
    }
}
