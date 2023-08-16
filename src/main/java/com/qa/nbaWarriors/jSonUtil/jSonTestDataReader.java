package com.qa.nbaWarriors.jSonUtil;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class jSonTestDataReader {
    public String getCommonData(String scenarioName, String key) {
        String value=null;
        try {
            String text = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/testData/global_TestData.json")), StandardCharsets.UTF_8);
            JSONObject obj = new JSONObject(text);
            JSONArray commonCompElements = obj.getJSONArray(scenarioName);
            JSONObject dataset = commonCompElements.getJSONObject(0);
            value = dataset.getString(key);

            return value;

        } catch (Exception ex) {
            // TODO: handle exception
            System.out.println(ex.toString());
            return value;
        }
    }
}
