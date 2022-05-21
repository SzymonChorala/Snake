import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Apple extends Point {
    private static Random random = new Random();
    private int i = 0;

    public Apple() {
        super(random.nextInt(Board.FIELD_X),random.nextInt(Board.FIELD_Y));
    }
    public Apple(List<Point> exclude) {
        int _x, _y;
        boolean collision;

        do {
            _x = random.nextInt(Board.FIELD_X);
            _y = random.nextInt(Board.FIELD_Y);
            Point p = new Point(_x, _y);
            collision = false;

            for (Point point : exclude) {
                if (p.equals(point)) {
                    collision = true;
                    break;
                }
            }
        } while (collision);
        x=_x;
        y=_y;
    }
    public void displace(List<Point> exclude) {
        int _x, _y;
        boolean collision;

        do {
            _x = random.nextInt(Board.FIELD_X);
            _y = random.nextInt(Board.FIELD_Y);
            Point p = new Point(_x, _y);
            collision = false;

            for (Point point : exclude) {
                if (p.equals(point)) {
                    collision = true;
                    break;
                }
            }
        } while (collision);
        x=_x;
        y=_y;
    }
    public void move(List<Point> exclude)
    {
        int _x, _y;
        boolean collision;
        i++;
        if(i%15==0)
        {
            do {
                _x = random.nextInt(Math.max(x - 1, 0), Math.min(x + 2, Board.FIELD_X));
                _y = random.nextInt(Math.max(y - 1, 0), Math.min(y + 2, Board.FIELD_Y));
                Point p = new Point(_x, _y);
                collision = false;

                for (Point point : exclude) {
                    if (p.equals(point)) {
                        collision = true;
                        break;
                    }
                }
            } while (collision);
            x = _x;
            y = _y;
        }
    }
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x*Board.SIZE,y*Board.SIZE, Board.SIZE,Board.SIZE);
    }

    private void move()
    {

    }
}
