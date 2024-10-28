package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int countapArtments) {
        return apartments.stream()
        .sorted((apartment1, apartment2) -> Double.compare(apartment1.getArea(), apartment2.getArea()))
        .limit(countapArtments)
        .map(Home::toString)  
        .collect(Collectors.toList());
    }
}
// END
