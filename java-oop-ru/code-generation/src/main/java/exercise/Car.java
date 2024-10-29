package exercise;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Car {
    private final int id;
    private final String brand;
    private final String model;
    private final String color;
    private final User owner;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String serialize() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Car deserialize(String json) throws IOException {
        try {
            return objectMapper.readValue(json, Car.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}