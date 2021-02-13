package lesson1.figures;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Circle extends Figure{
    private PointXY pointCenter;
    private Color color;
    private int radius;

    public Circle(PointXY pointCenter, Color color, int radius) {
        this.pointCenter = pointCenter;
        this.color = color;
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, Color color, int radius){
        this.pointCenter = new PointXY(xCenter, yCenter);
        this.color = color;
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void writePerimeter() {
        System.out.println("Периметр окружности: " + this.getPerimeter());
    }

    @Override
    public void writeArea() {
        System.out.println("Площаль окружности: " + this.getArea());
    }
}
