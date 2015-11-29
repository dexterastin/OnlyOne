/**
 * 
 */
package org.dimigo.pay;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Connect에서 불러온 JSON스트링을 해당 유저의 코드와 돈으로 분리시키는 파싱을 하는 클래스

public class UserJSONParser {
	public static User parse(String jsonString) {
		User user = new User(); // 새로운 유저 객체를 생성

		try {
			if (jsonString == null || jsonString.length() == 0)
				throw new Exception("No input data");

			JSONParser parser = new JSONParser();

			JSONObject result = (JSONObject) parser.parse(jsonString);
			String code = (String) result.get("code"); // JSON의 해당 코드 유저를 옮김
			int money = Integer.parseInt((String) result.get("money")); // JSON의
																		// 해당
																		// 코드의
																		// 유저의
																		// 돈을
																		// 로컬데이터로
																		// 옮김
			user.setMoney(money); // 유저 객체의 돈으로 입력
			user.setCode(code); // 유저 객체의 코드로 입력

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user; // 코드가 code 변수이고 돈이 money 변수인 유저 객체를 리턴
	}
}
