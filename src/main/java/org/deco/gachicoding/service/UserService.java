package org.deco.gachicoding.service;

import org.deco.gachicoding.domain.user.User;
import org.deco.gachicoding.dto.user.LoginRequestDto;
import org.deco.gachicoding.dto.jwt.JwtResponseDto;
import org.deco.gachicoding.dto.user.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    boolean isDuplicateEmail(String email);

    Optional<User> getUserByUserEmail(String email);

    JwtResponseDto login(LoginRequestDto requestDto);

    Long registerUser(UserSaveRequestDto dto);

    void confirmEmail(String token);

    Long updateUser(Long idx, UserUpdateRequestDto dto);

    Long deleteUser(Long idx);

}