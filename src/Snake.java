import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class Snake {
    private List<Point> body;
    private Direction direction;
    private Point ending;

    public Snake(){
        ending = new Point();
        direction = direction.R;
        body=new ArrayList<>();
        body.add(new Point(2,5));//head
        body.add(new Point(2,4));
        body.add(new Point(2,3));
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        for (Point point : getTail()) {
            g.fillRect(point.x*Board.SIZE,point.y*Board.SIZE, Board.SIZE,Board.SIZE);
        }

        g.setColor(new Color(0, 70, 3));
        g.fillRect(getHead().x*Board.SIZE,getHead().y*Board.SIZE, Board.SIZE,Board.SIZE);
    }

    private Point getHead() {
        return body.get(0);
    }

    public List<Point> getTail() {
        return body.subList(1, body.size());
    }

    public void move() {
        ending.setLocation(body.get(body.size()-1));
        for (int i = body.size()-1;i>0; i--) {
            body.get(i).setLocation(body.get(i-1));
        }
        switch (direction) {
            case D -> getHead().y++;
            case U -> getHead().y--;
            case R -> getHead().x++;
            case L -> getHead().x--;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isCollision() {
        Point head = getHead();

        for (Point point : getTail()) {
            if (head.equals(point)) {
                return true;
            }
        }
        return false;
    }

    public void ifWall() {
        Point head = getHead();

        if (head.x<0)
            head.x=Board.FIELD_X-1;
        if(head.x>Board.FIELD_X-1)
            head.x=0;
        if (head.y<0)
            head.y=Board.FIELD_Y-1;
        if(head.y>Board.FIELD_Y-1)
            head.y=0;
    }

    public int getSize() {
        return (body.size()-3);
    }

    public boolean eatApple(Apple apple) {

        if (getHead().equals(apple)) {
            body.add(new Point(ending));
            MainFrame.score.setText("Score: "+(body.size()-3));
            return true;
        }
        return false;
    }
}
