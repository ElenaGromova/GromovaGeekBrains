package lesson1.figures;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointXY {
    private int x, y;

    public PointXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
