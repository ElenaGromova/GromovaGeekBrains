package lesson1.figures;

public class FiguresTest {
    public static void main(String[] args) {
        PointXY point1 = new PointXY(1, 10);
        PointXY point2 = new PointXY(10, 1);
        PointXY point3 = new PointXY(10, 10);
        int radius = 5;
        Figure rectangle = new Rectangle(point1, point2, Color.RED);
        Figure circle = new Circle(point3, Color.BLUE, radius);
        Figure triangle = new Triangle(point1, point2, point3, Color.GREEN);

        rectangle.writePerimeter();
        rectangle.writeArea();

        circle.writePerimeter();
        circle.writeArea();

        triangle.writePerimeter();
        triangle.writeArea();
    }
}
