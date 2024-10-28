package exercise;

// BEGIN
public class Flat implements Home {

    private double area;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area + balconyArea;
        this.floor = floor;
    }

    public String toString() {
        return "Квартира площадью " + area + " метров на " + floor + " этаже";
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public boolean compareTo(Home another) {
        return area > another.getArea();
    }

}
// END
