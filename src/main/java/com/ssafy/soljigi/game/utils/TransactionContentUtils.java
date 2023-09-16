package com.ssafy.soljigi.game.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TransactionContentUtils {

	private static final String[] restaurants = {
		"한우마을", "목포해물탕", "옥룡면옥", "평양냉면", "오복수산",
		"신대방돼지국밥","김밥천국", "행복김밥천국","동래손칼국수","통영굴전문",
		"담양죽순밥", "한솥도시락", "제주돈까스", "안동찜닭", "맛있는골목식당",
		"천사백반집", "동네밥집","전통시장떡볶이","오복수산물직판장", "전통시장먹거리",
		"우동천하명가", "노포전통시장","남포동전통시장", "우리동네마트", "탑할인마트",
	};
	private static final String[] hospitals = {
		"청담한의원", "다나아내과", "하나한의원", "자연숲한의원", "백제병원",
		"성심병원", "현대의료센터", "효자의료복지센터", "삼성의료센터", "희망의 병원",
		"고려의료센터", "미소의료원", "힘찬건강병원", "바로건강병원", "생명의 병원",
	};
	private static final String[] pharmacies = {
		"백년한약방", "늘푸른약국", "하나약국", "신한 약국", "청담동약국",
		"편한약국", "믿음약국", "건강약국", "행복한 미래 약국", "지혜로운 약국",
		"미소약국", "부창약국", "생명약국", "환자를 위한 약국", "기쁨 약국",
	};
	private static final String[] cafe = {
		"스타벅스", "커피빈", "던킨도너츠", "카페베네", "테리와페리",
		"카페라떼", "라운드캣", "글로리아진스 커피", "코스트코 커피", "투썸플레이스"
	};

	private static final Set<String> restaurantSet = new HashSet<>(List.of(restaurants));
	private static final String[] pharmacyKeyword = {"약국", "약방"};
	private static final String[] utilityKeyword = {"연금", "전기요금", "휴대폰요금"};
	private static final String[] hospitalKeyword = {
		"안과", "내과", "정형외과", "치과", "성형외과",
		"산부인과", "접골원", "피부과", "외과", "병원",
		"비뇨기과", "소아과", "이비인후과", "한의원", "의료원", "의료센터"};

	public static StoreType findStoreType(String content) {

		content = content.trim().replace(" ", "");
		if (checkKeyword(pharmacyKeyword, content)) {
			return StoreType.PHARMACY;
		} else if (checkKeyword(utilityKeyword, content)) {
			return StoreType.UTILITY;
		} else if (checkKeyword(hospitalKeyword, content)) {
			return StoreType.HOSPITAL;
		} else if (restaurantSet.contains(content)) {
			return StoreType.RESTAURANT;
		}
		return StoreType.ETC;
	}

	public static String getRandomChoice(StoreType type) {
		Random random = new Random();
		if (type == StoreType.RESTAURANT) {
			return restaurants[random.nextInt(restaurants.length)];
		} else if (type == StoreType.CAFE) {
			return cafe[random.nextInt(cafe.length)];
		} else if (type == StoreType.HOSPITAL) {
			return hospitals[random.nextInt(hospitals.length)];
		} else if (type == StoreType.PHARMACY) {
			return pharmacies[random.nextInt(pharmacies.length)];
		}
		return null;
	}

	private static boolean checkKeyword(String[] keywords, String content) {
		for (String keyword: keywords) {
			if (content.contains(keyword))
				return true;
		}
		return false;
	}
}
