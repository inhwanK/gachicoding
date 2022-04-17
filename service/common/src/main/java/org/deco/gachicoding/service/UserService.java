package org.deco.gachicoding.service;

import org.deco.gachicoding.jwt.dto.JwtRequestDto;
import org.deco.gachicoding.jwt.dto.JwtResponseDto;
import org.deco.gachicoding.user.domain.User;
import org.deco.gachicoding.user.dto.UserSaveRequestDto;
import org.deco.gachicoding.user.dto.UserUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    boolean isDuplicateEmail(String email);

    Optional<User> getUserByUserEmail(String email);

    JwtResponseDto login(JwtRequestDto request);

    Long registerUser(UserSaveRequestDto dto);

    void confirmEmail(String token);

    Long updateUser(Long idx, UserUpdateRequestDto dto);

    Long deleteUser(Long idx);

}
