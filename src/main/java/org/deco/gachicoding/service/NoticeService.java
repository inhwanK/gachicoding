package org.deco.gachicoding.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.deco.gachicoding.domain.notice.Notice;
import org.deco.gachicoding.domain.notice.NoticeRepository;
import org.deco.gachicoding.domain.user.User;
import org.deco.gachicoding.domain.user.UserRepository;
import org.deco.gachicoding.dto.RequestDto;
import org.deco.gachicoding.dto.notice.NoticeBasicRequestDto;
import org.deco.gachicoding.dto.notice.NoticeResponseDto;
import org.deco.gachicoding.dto.notice.NoticeSaveRequestDto;
import org.deco.gachicoding.dto.notice.NoticeUpdateRequestDto;
import org.deco.gachicoding.dto.response.CustomException;
import org.deco.gachicoding.dto.response.ResponseState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.deco.gachicoding.dto.response.StatusEnum.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final TagService tagService;

    String NOTICE = "NOTICE";

    @Transactional(rollbackFor = Exception.class)
    public Long registerNotice(NoticeSaveRequestDto dto) throws Exception {
        // findById() -> 실제로 데이터베이스에 도달하고 실제 오브젝트 맵핑을 데이터베이스의 행에 리턴한다. 데이터베이스에 레코드가없는 경우 널을 리턴하는 것은 EAGER로드 한것이다.
        // getOne ()은 내부적으로 EntityManager.getReference () 메소드를 호출한다. 데이터베이스에 충돌하지 않는 Lazy 조작이다. 요청된 엔티티가 db에 없으면 EntityNotFoundException을 발생시킨다.

        // 이부분은 우리가 정의한 키워드를 통해 쿼리를 날린다. 요구사항이 이메일 -> 닉네임 으로 변경될 경우 변경이 필요해진다.
        // findByUserEmail을 통해 유저 정보를 가져오는 코드는 Service내에 여럿 존재한다.(중복된다)
        // 결합도가 높다고 할 수 있다. private한 메소드로 빼버릴까?
//        User writer = userRepository.findByUserEmail(dto.getUserEmail())
//                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        // =>
//        User writer = getWriterInfo(dto.getUserEmail());

        Notice notice = noticeRepository.save(dto.toEntity(getWriterInfo(dto.getUserEmail())));

        Long notIdx = notice.getNotIdx();
        String notContent = notice.getNotContent();

        if (!dto.isNullTags())
            tagService.registerBoardTag(notIdx, dto.getTags(), NOTICE);

        try {
            notice.updateContent(fileService.extractImgSrc(notIdx, notContent, NOTICE));
        } catch (Exception e) {
            log.error("Failed To Extract {} File", "Notice Content");
            e.printStackTrace();
            // throw해줘야 Advice에서 예외를 감지 함
            throw e;
        }

        return notIdx;
    }

    @Transactional
    public Page<NoticeResponseDto> getNoticeList(String keyword, Pageable pageable) {
        Page<NoticeResponseDto> noticeList =
                noticeRepository.findByNotContentContainingIgnoreCaseAndNotActivatedTrueOrNotTitleContainingIgnoreCaseAndNotActivatedTrueOrderByNotIdxDesc(keyword, keyword, pageable).map(entity -> new NoticeResponseDto(entity));

        noticeList.forEach(
                noticeResponseDto ->
                        tagService.getTags(noticeResponseDto.getNotIdx(), NOTICE, noticeResponseDto)
        );

        return noticeList;
    }

    @Transactional
    public NoticeResponseDto getNoticeDetail(Long notIdx) {
        // 이부분도 중복된다 하지만 findById는 Repository에서 기본적으로 제공하는 키워드이기 때문에 변경의 여지가 적다
//        Notice notice = noticeRepository.findById(notIdx)
//                .orElseThrow(() -> new CustomException(DATA_NOT_EXIST));
        Notice notice = getNoticeInfo(notIdx);

        NoticeResponseDto noticeDetail = NoticeResponseDto.builder()
                .notice(notice)
                .build();

        tagService.getTags(notIdx, NOTICE, noticeDetail);

        return noticeDetail;
    }

    @Transactional
    public NoticeResponseDto modifyNotice(NoticeUpdateRequestDto dto) {
        Notice notice = getNoticeInfo(dto.getNotIdx());

        isSameWrite(notice, dto);

        notice.updateTitle(dto.getNotTitle());

        notice.updateContent(dto.getNotContent());

        NoticeResponseDto noticeDetail = NoticeResponseDto.builder()
                .notice(notice)
                .build();

        return noticeDetail;
    }

    // 활성 -> 비활성
    // noticeRepository에서 파인드 할때 activated - false 인 애들만 가져오게 하는게 더 좋을지도..?
    @Transactional
    public ResponseEntity<ResponseState> disableNotice(NoticeBasicRequestDto dto) {
        Notice notice = getNoticeInfo(dto.getNotIdx());

        isSameWrite(notice, dto);

        notice.disableNotice();

        return ResponseState.toResponseEntity(DISABLE_SUCCESS);
    }

    // 비활성 -> 활성
    @Transactional
    public ResponseEntity<ResponseState> enableNotice(NoticeBasicRequestDto dto) {
        Notice notice = getNoticeInfo(dto.getNotIdx());

        isSameWrite(notice, dto);

        notice.enableNotice();

        return ResponseState.toResponseEntity(ENABLE_SUCCESS);
    }

    @Transactional
    public ResponseEntity<ResponseState> removeNotie(NoticeBasicRequestDto dto) {
        Notice notice = getNoticeInfo(dto.getNotIdx());

        isSameWrite(notice, dto);

        noticeRepository.delete(notice);

        return ResponseState.toResponseEntity(REMOVE_SUCCESS);
    }

    private Notice getNoticeInfo(Long notIdx) {
        return noticeRepository.findById(notIdx)
                .orElseThrow(() -> new CustomException(DATA_NOT_EXIST));
    }

    private User getWriterInfo(String userEmail) {
        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }

    // Writer의 정보를 가지고 있는 Notice를 통해 같은 유저인지 아닌지 판별 했다.
    // NoticeService.isSameWrite -> Notice.isWriter -> User.isMe
    // Notice를 거치지 않고 NoticeService에서 바로 User.isMe를 사용해도 된다.
    // 쓸데 없는 결합도를 추가한것은 아닐까?
    // 아니면 Writer의 정보를 가지고 있는 Notice를 통해 User.isMe를 실행하는게 옳은 것일까?
    // 원칙적으로는 Notice를 거치는 것이 옳다고 할 수 있다. 원칙을 지키기 위해 실용적인 부분을 배제하는 것은 좋은 설계가 아니다.
    // 난중에 인환이랑 이야기 해보자
    private void isSameWrite(Notice notice, RequestDto dto) {
        User user = getWriterInfo(dto.getUserEmail());

        if (!notice.isWriter(user)) {
            throw new CustomException(INVALID_AUTH_USER);
        }
    }
}
