package restassured.data;

import selenide.data.RandomCredsGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;

public class DataForApiTests {
    private String baseURI = "https://petstore.swagger.io/v2";
    private String username = "creepymonster";
    private String password = "24687531Qwert";

    public String getBaseURI() {
        return this.baseURI;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public JSONObject getNewPet() {
        JSONObject categoryObj = new JSONObject();
        categoryObj.put("id", 1);
        categoryObj.put("name", "cats");

        JSONArray tagsArray = new JSONArray();
        JSONObject tagsObj = new JSONObject();
        tagsObj.put("id", 1);
        tagsObj.put("name", "Oriental");
        tagsArray.add(tagsObj);

        JSONArray photosArray = new JSONArray();
        photosArray.add("https://monosnap.com/file/XpHwuMk0bkfPpp08XI2OjF94wMk8OX");
        photosArray.add("https://monosnap.com/file/XVIuPY2Op3lBjIfVAAndeUaYVGfVR1");
        photosArray.add("https://monosnap.com/file/enNxHcqrgvUj8rrpCXK2sfygpxPMhh");

        JSONObject newPet = new JSONObject();
        newPet.put("id", 100);
        newPet.put("category", categoryObj);
        newPet.put("name", "Liya");
        newPet.put("photoUrls", photosArray);
        newPet.put("tags", tagsArray);
        newPet.put("status", "available");

        return newPet;
    }

    public JSONObject getNewUser() {
        JSONObject request = new JSONObject();
        request.put("id", 1);
        request.put("username", "creepymonster");
        request.put("firstname", "Harry");
        request.put("lastname", "Potter");
        request.put("email", "testing@gmail.com");
        request.put("password", "24687531Qwert");
        request.put("phone", RandomCredsGenerator.getRandomTelNumber());
        request.put("userStatus", 123);

        return request;
    }

    public JSONArray getListOfUsers() {

        JSONObject user1 = new JSONObject();
        user1.put("id", 1);
        user1.put("username", "creepymonster");
        user1.put("firstname", "Harry");
        user1.put("lastname", "Potter");
        user1.put("email", "testing@gmail.com");
        user1.put("password", "24687531Qwert");
        user1.put("phone", RandomCredsGenerator.getRandomTelNumber());
        user1.put("userStatus", 123);

        JSONObject user2 = new JSONObject();
        user2.put("id", 2);
        user2.put("username", "creepymonster1");
        user2.put("firstname", "Ron");
        user2.put("lastname", "Weasley");
        user2.put("email", "testing1@gmail.com");
        user2.put("password", "24687531Qwert");
        user2.put("phone", RandomCredsGenerator.getRandomTelNumber());
        user2.put("userStatus", 123);

        JSONArray array = new JSONArray();
        array.add(user1);
        array.add(user2);

        return array;
    }

    public JSONObject getUpdatedUser() {
        JSONObject request = new JSONObject();
        request.put("id", 1);
        request.put("username", "creepymonster");
        request.put("firstname", "Drako");
        request.put("lastname", "Malfoy");
        request.put("email", "testing@gmail.com");
        request.put("password", "24687531Qwert");
        request.put("phone", RandomCredsGenerator.getRandomTelNumber());
        request.put("userStatus", 123);

        return request;
    }

    public JSONObject getNewOrder() {
        JSONObject request = new JSONObject();
        request.put("id", 1);
        request.put("petId", 1);
        request.put("quantity", 2);
        request.put("shipDate", LocalDateTime.now().toString());
        request.put("status", "placed");
        request.put("complete", true);

        return request;
    }

    public JSONObject getAvailablePet() {
        JSONObject status = new JSONObject();
        status.put("status", "available");
        return status;
    }

}
