import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static String readString (String file){
        String resStr = "";
        JSONParser parser = new JSONParser();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            Object obj = parser.parse(br);
            JSONArray  jsonArray = (JSONArray) obj;
            resStr = jsonArray.toString();
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return resStr;
    }

    public static  ArrayList<Employee> jsonToList(String json)  {
        ArrayList<Employee> employeeList = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(json);
            JSONArray  jsonArray = (JSONArray) obj;
            for (Object ob:jsonArray) {
                JSONObject jsonObject = (JSONObject) ob;
                employeeList.add(gson.fromJson(jsonObject.toString(), Employee.class));
            }

        }catch (ParseException e){
            e.printStackTrace();
        }
        return employeeList;
    }

    public static void main(String[] args) {
        String json = readString("data.json");
        ArrayList<Employee> employeeList = jsonToList(json);
        for (Employee e: employeeList) {
            System.out.println(e.toString());
        }
    }
}
