package task3;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static JSONArray valueArray;

    public static void main(String[] args) {
        String testsJsonUrl = args[0];
        String valueJsonUrl = args[1];
        valueArray = parseJson(valueJsonUrl, "values");
        JSONArray testsArray = parseJson(testsJsonUrl, "tests");
        JSONArray reportArray = insertToValues(testsArray);
        createJson(reportArray);
    }

    private static JSONArray parseJson(String valueJsonUrl, String key) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(valueJsonUrl));
            JSONObject jsonObject = (JSONObject) obj;
            return (JSONArray) jsonObject.get(key);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONArray insertToValues(JSONArray testsArray) {
        for (Object testObj : testsArray) {
            for (Object valueObj : valueArray) {
                JSONObject test = (JSONObject) testObj;
                JSONObject val = (JSONObject) valueObj;
                if (test.get("id").equals(val.get("id"))) {
                    test.put("value", val.get("value"));
                }
                if (test.get("values") != null) {
                    Object o = test.get("values");
                    JSONArray testPart = (JSONArray) o;
                    test.put("values", insertToValues(testPart));
                }
            }
        }
        return testsArray;
    }

    private static void createJson(JSONArray reportArray) {
        try (FileWriter file = new FileWriter("report.json")) {
            JSONObject reportJsonObj = new JSONObject();
            reportJsonObj.put("report", reportArray);
            file.write(reportJsonObj.toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
