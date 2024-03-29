# 1. 이슈 구조

이슈는 Title, Body 두 영역으로 이루어져 있습니다.

Title에는 이슈 제목을, Body에는 개요, 체크 리스트, 참고 자료를 작성합니다.

> ##Title
> 
> ---
> ##Body
> 
> 개요
> 
> 체크리스트
> 
> 참고자료

# 2. Title

이슈 제목은 `[브랜치이름] 이슈이름` 으로 작성해 주세요. 

ex) `[board-save-unit-test] 게시판 저장 기능을 단위 테스트한다.`

브랜치가 필요없는 이슈의 경우 브랜치 이름을 생략합니다.

ex) `깃 서브모듈을 통해 스프링 프로필을 관리하도록 변경한다.`

# 3. Body

### `체크 리스트`와 `참고 자료`는 필요한 경우에만 작성해 주세요!!

### 개요

해당 이슈를 통해 다루고자 하는 내용의 개요를 작성합니다.
 - ex) 게시판 저장 테스트 구현

### 체크 리스트 (Optional)

해당 이슈에서 개발하고자 하는 목표를 구체적으로 작성합니다.
 - ex) BoardService의 registerBoard 메소드의 요구사항을 점검한다.
 - ex) BoardService의 registerBoard 메소드를 테스트하는 코드를 작성한다.

### 참고 자료 (Optional)

해당 이슈를 해결하는데 도움이 될만한 참고 자료를 `[표시할 내용](링크 주소)` 형태로 링크합니다.
 - ex) [Tecoble 단위 테스트 vs 통합 테스트 vs 인수 테스트 ](https://tecoble.techcourse.co.kr/post/2021-05-25-unit-test-vs-integration-test-vs-acceptance-test/)

# 4. 이슈 예시
> ### [board-save-unit-test] 게시판 저장 기능을 단위 테스트한다.
> 
> ---
> 
> ## 개요
>
> 게시판 저장 기능의 요구사항을 점검하고 테스트 코드를 작성한다.
> 
> ## 체크리스트
>
> - [ ] BoardService의 registerBoard 메소드의 요구사항을 점검한다.
> 
> - [ ] BoardService의 registerBoard 메소드를 테스트하는 코드를 작성한다.
> 
> ## 참고자료
> 
> [Tecoble 단위 테스트 vs 통합 테스트 vs 인수 테스트 ](https://tecoble.techcourse.co.kr/post/2021-05-25-unit-test-vs-integration-test-vs-acceptance-test/)
