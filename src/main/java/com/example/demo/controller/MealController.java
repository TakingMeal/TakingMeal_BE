package com.example.demo.controller;


import com.example.demo.entity.Meal;
import com.example.demo.dto.MealDto;
import com.example.demo.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


@CrossOrigin
@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
@Slf4j
public class MealController {

    //http://localhost:8080/food/search?foodName=피자 foodName에 파라미터로 음식명(한글)을 넘겨주면 피자 리스트 10개 리턴

    @Autowired
    private MealRepository mealRepository;
    @GetMapping("/search")
    public JSONArray foodRequest(@RequestParam("foodName") String foodName, HttpServletRequest request) throws ParseException {

        //http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1?serviceKey=PE40pSyADxUOfX1WI2BhawGnknXrz6LfLp3NzD5qTJrcO1czoJKlgwuaF5pTsGodQ%2BtD81f%2FXXSZgSZ%2B728Brg%3D%3D&
        // desc_kor=%ED%94%BC%EC%9E%90&
        // pageNo=1&numOfRows=100&
        // type=xml

        //https://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1?serviceKey=PE40pSyADxUOfX1WI2BhawGnknXrz6LfLp3NzD5qTJrcO1czoJKlgwuaF5pTsGodQ%2BtD81f%2FXXSZgSZ%2B728Brg%3D%3D&desc_kor=%ED%94%BC%EC%9E%90&pageNo=1&numOfRows=100&type=xml
        StringBuilder sb = new StringBuilder();
        JSONObject result = new JSONObject();
        JSONArray list = new JSONArray();
        Meal meal = null;
        Double nutrCont1 = 0.0;
        Double nutrCont2 = 0.0;
        Double nutrCont3 = 0.0;
        Double nutrCont4 = 0.0;
        Double nutrCont5 = 0.0;
        Double nutrCont6 = 0.0;



        System.out.println(foodName);
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=PE40pSyADxUOfX1WI2BhawGnknXrz6LfLp3NzD5qTJrcO1czoJKlgwuaF5pTsGodQ%2BtD81f%2FXXSZgSZ%2B728Brg%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("desc_kor", "UTF-8") + "=" + URLEncoder.encode(foodName, "UTF-8")); /*페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과수*/
            urlBuilder.append("&type=json"); /*결과 json 포맷*/

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line + "\n");

            }
            rd.close();
            conn.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }

        //return sb.toString() + "";

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            JSONObject body = (JSONObject)jsonObject.get("body");
            JSONArray items = (JSONArray)body.get("items");
            if(body == null){
                return null;
            }
            else {
                for (int i = 0; i < items.size(); i++) {

                    //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                    JSONObject tmp = (JSONObject) items.get(i);

                    Object serving = tmp.get("SERVING_WT");
                    Double servingWt = Double.parseDouble(String.valueOf(serving));



                        Object cont1 = tmp.get("NUTR_CONT1");
                        if(!(cont1.equals("N/A"))) {
                             nutrCont1 = Double.parseDouble(String.valueOf(cont1));
                        }

                        Object cont2 = tmp.get("NUTR_CONT2");
                        if(!(cont2.equals("N/A"))) {
                            nutrCont2 = Double.parseDouble(String.valueOf(cont2));
                        }

                        Object cont3 = tmp.get("NUTR_CONT3");
                        if(!(cont3.equals("N/A"))) {
                            nutrCont3 = Double.parseDouble(String.valueOf(cont3));
                        }

                        Object cont4 = tmp.get("NUTR_CONT4");
                        if(!(cont4.equals("N/A"))) {
                            nutrCont4 = Double.parseDouble(String.valueOf(cont4));
                        }


                        MealDto mealDto = new MealDto((String) tmp.get("DESC_KOR"), servingWt, nutrCont1, nutrCont2, nutrCont3, nutrCont4);

                        list.add(mealDto);
                    }
                }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(list);
       return list;

    }

    @GetMapping("/testing")
    public String testing(){

        System.out.println("test");
        return "hello world";
    }

    @PostMapping("/register")
    public String foodRegister(@RequestBody MealDto  mealDto){

       /*Meal meal = Meal.registerMeal(mealDto.getMealName(), mealDto.getMealAmount(), mealDto.getMealCal(), mealDto.getMealCarbon(),
               mealDto.getMealProtein(), mealDto.getMealFat());

        System.out.println(meal);
        mealRepository.save(meal);
*/
        return "";


    }


}
