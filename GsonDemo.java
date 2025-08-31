package com.soft.eng.string;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GsonDemo {

    private static final String DATE_WITH_TIME = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        Gson gson = new Gson();
        String jsonStr = "{\n" +
                "    \"name\": \"Madan Reddy\",\n" +
                "    \"email\": \"tutor@eazybytes\",\n" +
                "    \"mobileNumber\": \"4354437687\"\n" +
                "}";

        String jsonAcct = "{\n" +
                "    \"name\": \"Radha Madav Reddy\",\n" +
                "    \"email\": \"tutor@eazybytes\",\n" +
                "    \"mobileNumber\": \"4354437687\",\n" +
                "    \"accountsDto\": {\n" +
                "        \"accountNumber\": 1371083879,\n" +
                "        \"accountType\": \"Super Savings\",\n" +
                "        \"branchAddress\": \"123 Main Street, New York\"\n" +
                "    }\n" +
                "}";

        Customer customer = gson.fromJson(jsonStr, Customer.class);
        System.out.println(customer);
        String json = gson.toJson(customer);
        System.out.println("json: " + json);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode response = mapper.valueToTree(customer);
        System.out.println("resp: " + response);
        //response = mapper.createObjectNode();
        System.out.println("resp2: " + response);
        JSONObject jsonObject = new JSONObject(jsonAcct);
        JSONObject getDataObject = jsonObject.getJSONObject("accountsDto");
        System.out.println(getDataObject);

        JsonNode responseNode = mapper.valueToTree(customer);

        if (responseNode != null && responseNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) responseNode;
            objectNode.remove("sno");
            objectNode.remove("serviceStartTime");
            objectNode.remove("jcStatus");
            objectNode.remove("rampAllocationId");
            LocalDateTime currentTime = LocalDateTime.now();
            String currentDateTime = DateTimeFormatter.ofPattern(DATE_WITH_TIME).format(currentTime);
            objectNode.put("currentDateTime",currentDateTime);
            //objectNode.put("allocatedStartTime",dataListRamp.getStartTime());
            //objectNode.put("allocatedEndTIme",dataListRamp.getEndTime());
            System.out.println(objectNode);
        }


    }
}
