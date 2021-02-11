package mini_project;

import java.util.ArrayList;

public class Data {
	private static ArrayList<Food> mealList = new ArrayList<Food>();
	private static ArrayList<Food> sideList = new ArrayList<Food>();
	private static ArrayList<Food> snackList = new ArrayList<Food>();
	private static ArrayList<Food> beverageList = new ArrayList<Food>();
	private static ArrayList<Food> teaList = new ArrayList<Food>();
	private static ArrayList<Fee> feeList = new ArrayList<Fee>();
	
	{
		// 식사 메뉴
		mealList.add(new Food("식사", "기본라면", 3500));
		mealList.add(new Food("식사", "계란라면", 3800));
		mealList.add(new Food("식사", "치즈라면", 4000));
		mealList.add(new Food("식사", "불닭볶음면", 3500));
		mealList.add(new Food("식사", "진짬뽕", 4000));
		mealList.add(new Food("식사", "짜파게티", 3800));
		mealList.add(new Food("식사", "우동", 4000));
		mealList.add(new Food("식사", "새우튀김우동", 4800));
		mealList.add(new Food("식사", "잔치국수", 3500));
		mealList.add(new Food("식사", "냉모밀", 4000));
		mealList.add(new Food("식사", "치킨마요", 4000));
		mealList.add(new Food("식사", "참치마요", 4000));
		mealList.add(new Food("식사", "고기만두(4개)", 2800));
		mealList.add(new Food("식사", "김치만두(4개)", 2800));
		mealList.add(new Food("식사", "군만두", 3000));
		mealList.add(new Food("식사", "떡볶이", 3500));
		mealList.add(new Food("식사", "라볶이", 4200));
		mealList.add(new Food("식사", "제육덮밥", 5000));
		mealList.add(new Food("식사", "김치볶음밥", 4500));
		mealList.add(new Food("식사", "돈까스", 5500));
		mealList.add(new Food("식사", "치킨버거", 3500));
		mealList.add(new Food("식사", "불고기버거", 3500));
		mealList.add(new Food("식사", "치즈추가", 500));
		mealList.add(new Food("식사", "공기밥", 1000));
		
		// 사이드 메뉴
		sideList.add(new Food("사이드", "감자튀김", 3000));
		sideList.add(new Food("사이드", "새우튀김(3개)", 3500));
		sideList.add(new Food("사이드", "고구마맛탕", 3000));
		sideList.add(new Food("사이드", "핫도그", 2000));
		sideList.add(new Food("사이드", "소떡소떡", 3500));
		sideList.add(new Food("사이드", "피카츄돈까스", 1200));
		sideList.add(new Food("사이드", "콜팝치킨", 3500));
		sideList.add(new Food("사이드", "타코야끼(8개)", 3500));
		sideList.add(new Food("사이드", "치즈스틱(4개)", 3000));
		sideList.add(new Food("사이드", "수제소세지(2개)", 3000));
		sideList.add(new Food("사이드", "호떡(2개)", 2000));
		sideList.add(new Food("사이드", "붕어빵(3개)", 2000));
		
		// 과자 메뉴
		snackList.add(new Food("과자", "포카칩", 1500));
		snackList.add(new Food("과자", "나초", 1500));
		snackList.add(new Food("과자", "홈런볼", 1500));
		snackList.add(new Food("과자", "콘칩", 1500));
		snackList.add(new Food("과자", "자갈치", 1500));
		snackList.add(new Food("과자", "오징어땅콩", 2000));
		snackList.add(new Food("과자", "츄러스", 1500));
		snackList.add(new Food("과자", "양파링", 1500));
		snackList.add(new Food("과자", "숏다리", 1500));
		snackList.add(new Food("과자", "누네띠네", 1200));
		snackList.add(new Food("과자", "칸초", 1200));
		snackList.add(new Food("과자", "빼빼로", 1200));
		snackList.add(new Food("과자", "아몬드빼빼로", 1500));
		snackList.add(new Food("과자", "누드빼빼로", 1500));
		
		// 음료수 메뉴
		beverageList.add(new Food("음료", "마운틴듀", 1500));
		beverageList.add(new Food("음료", "밀키스", 1500));
		beverageList.add(new Food("음료", "사이다", 1500));
		beverageList.add(new Food("음료", "콜라", 1500));
		beverageList.add(new Food("음료", "웰치스", 1500));
		beverageList.add(new Food("음료", "환타", 1500));
		beverageList.add(new Food("음료", "몬스터에너지", 2000));
		beverageList.add(new Food("음료", "깔라만시에이드", 3000));
		beverageList.add(new Food("음료", "레드자몽에이드", 3000));
		beverageList.add(new Food("음료", "청포도에이드", 3000));
		beverageList.add(new Food("음료", "레몬에이드", 2000));
		beverageList.add(new Food("음료", "레몬콕", 2000));
		beverageList.add(new Food("음료", "체리콕", 2000));
		beverageList.add(new Food("음료", "체리에이드", 2000));
		beverageList.add(new Food("음료", "블루베리에이드", 2000));
		beverageList.add(new Food("음료", "블루레몬에이드", 2000));
		beverageList.add(new Food("음료", "망고에이드", 2000));
		
		// 커피/차 메뉴
		teaList.add(new Food("커피/차", "레몬유자차", 2000));
		teaList.add(new Food("커피/차", "자몽차", 2000));
		teaList.add(new Food("커피/차", "캐모마일", 2000));
		teaList.add(new Food("커피/차", "얼그레이", 2000));
		teaList.add(new Food("커피/차", "페퍼민트", 2000));
		teaList.add(new Food("커피/차", "자스민차", 2000));
		teaList.add(new Food("커피/차", "녹차", 2000));
		teaList.add(new Food("커피/차", "히비스커스차", 2000));
		teaList.add(new Food("커피/차", "복숭아 아이스티", 1500));
		teaList.add(new Food("커피/차", "믹스베리 아이스티", 2000));
		teaList.add(new Food("커피/차", "아이스커피", 2000));
		teaList.add(new Food("커피/차", "아메리카노 HOT", 2000));
		teaList.add(new Food("커피/차", "카페라떼 HOT", 2500));
		teaList.add(new Food("커피/차", "카페모카 HOT", 2500));
		teaList.add(new Food("커피/차", "바닐라라떼 HOT", 2500));
		teaList.add(new Food("커피/차", "카라멜마끼아또 HOT", 2500));
		teaList.add(new Food("커피/차", "흑당카페라떼 HOT", 2500));
		teaList.add(new Food("커피/차", "핫초코", 2500));
		teaList.add(new Food("커피/차", "아메리카노 ICE", 2200));
		teaList.add(new Food("커피/차", "카페라떼 ICE", 2700));
		teaList.add(new Food("커피/차", "카페모카 ICE", 2700));
		teaList.add(new Food("커피/차", "바닐라라떼 ICE", 2700));
		teaList.add(new Food("커피/차", "녹차스무디", 3500));
		teaList.add(new Food("커피/차", "딸기스무디", 3500));
		teaList.add(new Food("커피/차", "요거트스무디", 3500));
		teaList.add(new Food("커피/차", "망고스무디", 3500));
		teaList.add(new Food("커피/차", "바나나스무디", 3500));
		teaList.add(new Food("커피/차", "블루베리스무디", 3500));
		teaList.add(new Food("커피/차", "아이스딸기라떼", 3000));
		teaList.add(new Food("커피/차", "아이스녹차라떼", 3000));
	}

	public static ArrayList<Food> getMealList() {
		return mealList;
	}

	public static void setMealList(ArrayList<Food> mealList) {
		Data.mealList = mealList;
	}

	public static ArrayList<Food> getSnackList1() {
		return sideList;
	}

	public static void setSnackList1(ArrayList<Food> sideList) {
		Data.sideList = sideList;
	}

	public static ArrayList<Food> getSnackList2() {
		return snackList;
	}

	public static void setSnackList2(ArrayList<Food> snackList) {
		Data.snackList = snackList;
	}

	public static ArrayList<Food> getBeverageList1() {
		return beverageList;
	}

	public static void setBeverageList1(ArrayList<Food> beverageList) {
		Data.beverageList = beverageList;
	}

	public static ArrayList<Food> getBevarageList2() {
		return teaList;
	}

	public static void setBevarageList2(ArrayList<Food> teaList) {
		Data.teaList = teaList;
	}
	
}
