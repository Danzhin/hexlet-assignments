package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {
    public static void save(Path path, Car car) {
        String jsonRepresentation = car.serialize();
        try {
            Files.writeString(path, jsonRepresentation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) {
        try {
            String json = Files.readString(path);
            return Car.deserialize(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
