INSERT INTO COMPANY_TYPE (COMPANY_TYPE_ID, COMPANY_TYPE_NAME) VALUES
(001, '농업'),
(002, '어업'),
(003, '임업'),
(004, '광업'),
(005, '제조'),
(006, '건설'),
(007, '도소매'),
(008, '운수'),
(009, '숙박'),
(010, '요식'),
(011, '정보통신'),
(012, '금융'),
(013, '보험'),
(014, '부동산'),
(015, '교육'),
(016, '공공'),
(017, '보건');

INSERT INTO COMPANY_LOCATION (COMPANY_LOCATION_ID, COMPANY_LOCATION_NAME) VALUES
(01, '서울'),
(02, '경기'),
(03, '인천'),
(04, '강원'),
(05, '충북'),
(06, '충남'),
(07, '대전'),
(08, '경상'),
(09, '전북'),
(10, '전남'),
(11, '광주'),
(12, '경북'),
(13, '경남'),
(14, '대구'),
(15, '울산'),
(16, '부산'),
(17, '제주');

INSERT INTO MEMBER (COMPANY_TYPE_ID, COMPANY_LOCATION_ID, NAME, PASSWORD, SEX, COMPANY_NAME) VALUES
(011, 01, '홍길동0', 'af93bX8Id', 'm', '서울 정보통신'),
(017, 09, '홍길동1', 'ydDxcv48s', 'f', '전북 보건'),
(001, 17, '홍길동2', '9h9d5eeHq', 'f', '제주 농업'),
(015, 03, '홍길동3', 'iqPdk55xr', 'm', '인천 교육'),
(005, 02, '홍길동4', 'Yx2dw6hdD', 'f', '경기 제조'),
(008, 11, '홍길동5', 'u5J6kEvcA', 'm', '광주 운수'),
(009, 16, '홍길동6', 'E42eJdf5e', 'm', '부산 숙박'),
(007, 04, '홍길동7', '6oGde8ceL', 'm', '강원 도소매'),
(014, 07, '홍길동8', '8eX6wSgQw', 'f', '대전 부동산'),
(011, 01, '홍길동9', '6gEsvxGEa', 'f', '서울 정보통신');