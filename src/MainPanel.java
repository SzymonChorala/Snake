import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel {

    private Snake snake = new Snake();
    private Apple apple = new Apple();
    private Apple apple1 = new Apple();

    private Obstacle obstacle = new Obstacle(snake.getTail());

    private List<Obstacle> obstacles;
    private boolean gameOver = false;

    public MainPanel(){
        setPreferredSize(new Dimension(Board.MAX_X, Board.MAX_Y));
        MainTimer timer = new MainTimer();
        timer.start();

        MainFrame.score.setText("Score: "+snake.getSize());

        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        obstacles = new ArrayList<Obstacle>();
        obstacles.add(obstacle);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Board.draw(g);
        snake.draw(g);
        apple.draw(g);
        apple1.draw(g);
        obstacle.draw(g);
    }

    private class MainTimer extends Timer {

        public static int DELAY = 100;
        public MainTimer() {
            super(DELAY, e -> {
                if(!gameOver){
                    snake.move();
                    if (snake.eatApple(apple))
                        apple.displace(snake.getTail());
                    if (snake.eatApple(apple1))
                        apple1.displace(snake.getTail());
                    if (snake.isCollision(obstacles)) {
                        gameOver = true;
                        MainFrame.score.setText("GAME OVER - Score: " + snake.getSize());
                    }
                    apple1.move(snake.getTail());
                    obstacle.displace(snake.getTail());
                    snake.ifWall();
                    repaint();
                }
            });
        }
    }


    private class MyKeyAdapter extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if(snake.getDirection() != Direction.R)
                        snake.setDirection(Direction.L);
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(snake.getDirection() != Direction.L)
                        snake.setDirection(Direction.R);
                        break;
                    case KeyEvent.VK_UP:
                        if(snake.getDirection() != Direction.D)
                        snake.setDirection(Direction.U);
                        break;
                    case KeyEvent.VK_DOWN:
                        if(snake.getDirection() != Direction.U)
                        snake.setDirection(Direction.D);
                        break;

                }
            }
        }

}
