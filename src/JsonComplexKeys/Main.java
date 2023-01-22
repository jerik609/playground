package JsonComplexKeys;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static final Gson gson = new GsonBuilder().create();

    private static final String PATH = System.getProperty("user.dir");
    private static final URL RESOURCE_PATH = Main.class.getResource("stuff.txt");



    public static void main(String[] args) throws URISyntaxException, IOException {
        System.out.println(PATH);
        System.out.println(RESOURCE_PATH);

        final var jsonObject = gson.fromJson(Files.readString(Paths.get(RESOURCE_PATH.toURI())), JsonObject.class);

        System.out.println(jsonObject);



    }


}
