package org.deco.gachicoding.social.service;

import org.deco.gachicoding.social.domain.Social;
import org.deco.gachicoding.social.dto.SocialSaveRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SocialService {
    Long registerSocial(SocialSaveRequestDto dto);

    Optional<Social> getSocialTypeAndEmail(SocialSaveRequestDto dto);

    String getKakaoAccessToken(String code);

    SocialSaveRequestDto getKakaoUserInfo(String token) throws Exception;
}
