package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final int age;
}