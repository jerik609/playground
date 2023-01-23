package JsonComplexKeys;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;

public class Main {
    private static final Gson gson = new GsonBuilder().create();

    private static final String PATH = System.getProperty("user.dir");
    private static final URL RESOURCE_PATH = Main.class.getResource("stuff.txt");



    public static void main(String[] args) throws URISyntaxException, IOException {
        System.out.println(PATH);
        System.out.println(RESOURCE_PATH);

        final var jsonObject = gson.fromJson(Files.readString(Paths.get(RESOURCE_PATH.toURI())), JsonObject.class);

        System.out.println(jsonObject);

        System.out.println("type: " + jsonObject.get("type").getAsString());
        System.out.println("key: " + jsonObject.get("key").getAsString());

        // first get the operation based on "type"
        // then get the value based on key -> we get our JSON object , we can traverse

        // then use the remaining "keys" to traverse the JSON object

        final var valueBehindKey = jsonObject.getAsJsonObject("value"); // this queries the database, where value is stored as string


        final var keys = new String[]{"rocket", "launches"};

        final var keys2 = new String[]{"rocket", "launches", "apples"}; // trying to search past leaf

        final var keys3 = new String[]{"rocket", "itches"};

        final var deleteKeys = new String[]{"rocket"};

        // iterator - searcher

        JsonObject parentElement = valueBehindKey;
        String elementKey = "";
        JsonElement element = valueBehindKey;
        for (var key : deleteKeys) {
            if (element instanceof JsonObject node) {
                element = node.get(key);
                if (element == null) {
                    System.out.println("Key is not one of node's children: Key '" + key + "' not found!");
                }
                parentElement = node;
                elementKey = key;
            } else {
                System.out.println("Trying to search past leaf: Key '" + key + "' not found!");
            }
        }
        System.out.println("parent: " + parentElement);
        System.out.println("element: " + element);


        System.out.println("Before delete: " + valueBehindKey);

        parentElement.remove(elementKey);

        System.out.println("After delete: " + valueBehindKey);

        // and now we can persist hat we have modified
    }


}
