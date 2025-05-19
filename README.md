

## ERD 다이어그램

<img src="./image.png" alt="ERD 다이어그램" width="600"/>

## 플래너 API 설계
| 기능           | 메서드  | URL                             | 요청 타입     | 요청 데이터       | 응답 내용         | 응답 코드     |
|----------------|---------|----------------------------------|----------------|--------------------|--------------------|---------------|
| 일정 등록        | POST    | /api/plans                      | 요청 Body      | 등록 정보          | 등록 정보          | 200: 정상등록  |
| 일정 단건 조회   | GET     | /api/plans/{planId}             | 요청 Param     | planId             | 단건 응답 정보     | 200: 정상조회  |
| 일정 목록 조회   | GET     | /api/users/{userId}/plans       | 요청 Param     | userId             | 다건 응답 정보     | 200: 정상조회  |
| 일정 수정        | PUT     | /api/plans/{planId}             | 요청 Body      | 수정 정보          | 수정 정보          | 200: 정상수정  |
| 일정 삭제        | DELETE  | /api/plans/{planId}             | 요청 Param     | planId             | -                | 200: 정상삭제  |

