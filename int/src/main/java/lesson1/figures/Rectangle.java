package lesson1.figures;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rectangle extends Figure{
    private PointXY pointLeftTop;
    private PointXY pointRightBottom;
    private Color color;

    public Rectangle(PointXY pointLeftTop, PointXY pointRightBottom, Color color) {
        this.pointLeftTop = pointLeftTop;
        this.pointRightBottom = pointRightBottom;
        this.color = color;
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom, Color color){
        this.pointLeftTop = new PointXY(xLeft, yTop);
        this.pointRightBottom = new PointXY(xRight, yBottom);
        this.color = color;
    }

    public int getLength(){
        return pointRightBottom.getX() - pointLeftTop.getX();
    }

    public int getWidth(){
        return pointLeftTop.getY() - pointRightBottom.getY();
    }

    @Override
    public double getArea() {
        return getLength()*getWidth();
    }

    @Override
    public double getPerimeter() {
        return getLength()*2 + getWidth()*2;
    }

    @Override
    public void writePerimeter() {
        System.out.println("Периметр прямоугольника: " + this.getPerimeter());
    }

    @Override
    public void writeArea() {
        System.out.println("Площаль прямоугольника: " + this.getArea());
    }
}
