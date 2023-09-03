package com.ssafy.soljigi.dementiaTest.service;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MemoryService {
	// 임시로 배열에 저장 ==> 추후 DB로 이관 예정
	static final String[] listWho = new String[] {"민수", "영호", "철수", "보람", "영희", "성철", "민지", "영수"
		, "영자", "윤정", "준영", "찬호", "상원", "종우", "준범", "석근", "지민", "효진", "수빈", "수현"}; // size = 20
	static final String[] listHow = new String[] {"자전거를 타고", "천천히 걸어서", "킥보드를 타고", "마을버스를 타고", "택시를 타고",
		"지하철을 타고", "비행기를 타고", "오토바이를 타고", "트럭을 타고", "경찰차를 타고", "소방차를 타고", "구급차를 타고", "KTX를 타고",
		"SRT를 타고", "모범택시를 타고", "고속버스를 타고", "무궁화호를 타고", "보트를 타고", "뛰어서", "유모차를 타고"}; // size = 20
	static final String[] listWhere = new String[] {"공원", "마트", "학교", "병원", "경찰서", "소방서", "운동장", "한강", "축구 경기장",
		"올림픽 공원", "강남역", "여의도", "서울역", "서울숲", "학원", "보건소", "세탁소", "식당", "제주도", "울릉도"}; // size = 20
	static final String[] listWhen = new String[] {"1시", "2시", "3시", "4시", "5시", "6시", "7시", "8시", "9시", "10시", "11시",
		"12시", "13시", "14시", "15시", "16시", "17시", "18시", "19시", "20시", "21시", "22시", "23시", "24시"}; // size = 20
	static final String[] listWhat = new String[] {"야구", "축구", "농구", "탁구", "배구", "산책", "테니스", "족구", "당구", "노래", "구르기",
		"줄넘기", "체조", "사격", "공부", "복싱", "골프", "볼링", "공연", "발표"}; // size = 20

	public static HashMap<String, Object> getQuiz(HashMap<String, Object> map) {

		Random random = new Random();

		// Q0 : 기억력 테스트 항목 생성
		// ex) 민수는 / 자전거를 타고 / 공원에 가서 / 11시부터 / 야구를 했다
		map.put("who", listWho[random.nextInt(20)]);
		map.put("how", listHow[random.nextInt(20)]);
		map.put("where", listWhere[random.nextInt(20)]);
		map.put("when", listWhen[random.nextInt(20)]);
		map.put("what", listWhat[random.nextInt(20)]);

		return map;
	}
}
