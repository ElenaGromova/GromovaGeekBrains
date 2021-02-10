package lesson1.figures;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Triangle extends Figure{
    private PointXY point1;
    private PointXY point2;
    private PointXY point3;
    private Color color;

    public Triangle(PointXY point1, PointXY point2, PointXY point3, Color color) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.color = color;
    }

    public double getSideLength(PointXY point1, PointXY point2){
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
    }

    @Override
    public double getArea() {
        return Math.sqrt(this.getPerimeter() * (this.getPerimeter() - this.getSideLength(point1, point2))
                * (this.getPerimeter() - this.getSideLength(point1, point3)) * (this.getPerimeter() - this.getSideLength(point2, point3)));
    }

    @Override
    public double getPerimeter() {
        return (this.getSideLength(point1, point2) + this.getSideLength(point1, point3) + this.getSideLength(point2, point3))/2;
    }

    @Override
    public void writePerimeter() {
        System.out.println("Периметр треугольника: " + this.getPerimeter());
    }

    @Override
    public void writeArea() {
        System.out.println("Площаль треугольника: " + this.getArea());
    }
}
