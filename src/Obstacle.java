import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacle implements  Runnable
{
    private List<Point> body;
    private static Random random = new Random();
    private int i = 0;

    public void run()
    {


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    Obstacle(List<Point> exclude)
    {
        body = new ArrayList<>();
        int length = random.nextInt(7);
        int direction = random.nextInt(0,1);
        Point p;


        boolean collision = false;
        do {
            if(!body.isEmpty())
                body.clear();
            Point first = findEmpty(exclude);
            body.add(first);
            for (int i = 0; i < length; i++) {
                if (direction == 0) {
                    p = new Point(first.x + i, first.y);
                    for (Point point : exclude) {
                        if (p.equals(point)) {
                            collision = true;
                            break;
                        }
                    }
                    body.add(p);
                } else {
                    p = new Point(first.x, first.y + 1);
                    for (Point point : exclude) {
                        if (p.equals(point)) {
                            collision = true;
                            break;
                        }
                    }
                    body.add(p);
                }
            }
        } while(collision);
    }

    public List<Point> getBody()
    {
        return body;
    }
    public void displace(List<Point> exclude) {
        int length = random.nextInt(7);
        int direction = random.nextInt(0,1);
        Point p;
        int collisionCounter = 0;

        i++;
        if(i%35==0) {
            boolean collision = false;
            do {
                body.clear();
                Point first = findEmpty(exclude);
                body.add(first);
                for (int i = 0; i < length; i++) {
                    if (direction == 0) {
                        p = new Point(first.x + i, first.y);
                        for (Point point : exclude) {
                            if (p.equals(point)) {
                                collision = true;
                                collisionCounter++;
                                break;
                            }
                        }
                        body.add(p);
                    } else {
                        p = new Point(first.x, first.y + 1);
                        for (Point point : exclude) {
                            if (p.equals(point)) {
                                collision = true;
                                collisionCounter++;
                                break;
                            }
                        }
                        body.add(p);
                    }
                }
            } while (collision & collisionCounter < 40);
        }
    }

    private Point findEmpty(List<Point> exclude)
    {
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
        return new Point(_x, _y);
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        for (Point point : body) {
            g.fillRect(point.x*Board.SIZE,point.y*Board.SIZE, Board.SIZE,Board.SIZE);
        }
    }




}
