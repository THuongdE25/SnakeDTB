package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class WindowPanel extends JPanel implements ActionListener, KeyListener,  Control {
    public final static int TILE = 20, WIDTH = TILE * 25, HEIGHT = TILE * 25;
    private Timer timer = new Timer(100, this);
    private char Move = 'r';
    private boolean Over = false, Pause = false;
    private Fruit fruit = new Fruit();
    private Snake snake = new Snake();
    private Font font = new Font("", Font.PLAIN, TILE * 2);
    private Font score = new Font("", Font.PLAIN, TILE);
    private Random rd = new Random();
    private DatabaseManage databasemanage=new DatabaseManage();
//    private boolean GameMode;

    public WindowPanel(){
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT+TILE));
        this.addKeyListener(this);
        timer.start();
//        Choice();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
//        if(GameMode) DrawWall(g);
        DrawFruit(g);
        DrawSnake(g);

        g.setColor(Color.black);
        g.setFont(score);
        g.drawString("Score: " + (Snake.LENGTH - 2), 0, HEIGHT + TILE);

        if (Pause) {
            g.setColor(Color.blue);
            g.setFont(font);
            g.drawString("GAME PAUSE", WIDTH / 2 - TILE * 6, HEIGHT / 2);
        }

        if (Over) {
            g.setColor(Color.blue);
            g.setFont(font);
            g.drawString("GAME OVER", WIDTH / 2 - TILE * 6, HEIGHT / 2);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Over && !Pause) {
            DiChuyen();
            DoiHuong();
            VuotBien();
            AnMoi();
            VaCham();

            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!Over) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN && Move != 'u') Move = 'd';
            if (e.getKeyCode() == KeyEvent.VK_UP && Move != 'd') Move = 'u';
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && Move != 'l') Move = 'r';
            if (e.getKeyCode() == KeyEvent.VK_LEFT && Move != 'r') Move = 'l';
            if(e.getKeyCode()==KeyEvent.VK_SPACE) Pause=!Pause;
            if(e.getKeyCode()==KeyEvent.VK_S) {
                System.out.print("save");
                Save();
            }
            if(e.getKeyCode()==KeyEvent.VK_L) {
                System.out.print("load");
                Load();
            }
            repaint();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e){}
    public void Draw(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < WIDTH; i += TILE) {
            for (int j = 0; j < HEIGHT; j += TILE) {
                g.drawRect(i, j, TILE, TILE);
            }
        }
    }
    public void DrawFruit(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(fruit.getFRUITX(), fruit.getFRUITY(), TILE, TILE);
    }
    public void DrawSnake(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < Snake.LENGTH; i++) {
            g.fillRect(snake.getSNAKEX(i), snake.getSNAKEY(i), TILE, TILE);
        }
    }
//    public void DrawWall(Graphics g) {
//        g.setColor(Color.black);
//        for (int i=0;i<WIDTH;i+=TILE){
//            g.fillRect(0,i,TILE,TILE);
//            g.fillRect(HEIGHT-TILE,i,TILE,TILE);
//        }
//        for (int i=0;i<HEIGHT;i+=TILE){
//            g.fillRect(i,0,TILE,TILE);
//            g.fillRect(i,WIDTH-TILE,TILE,TILE);
//        }
//    }
    public void DiChuyen() {
        for (int i = Snake.LENGTH; i > 0; i--) {
            snake.setSNAKEX(i, snake.getSNAKEX(i - 1));
            snake.setSNAKEY(i, snake.getSNAKEY(i - 1));
        }
    }
    public void DoiHuong() {
        if (Move == 'r') snake.setSNAKEX(0, snake.getSNAKEX(0) + TILE);
        if (Move == 'l') snake.setSNAKEX(0, snake.getSNAKEX(0) - TILE);
        if (Move == 'd') snake.setSNAKEY(0, snake.getSNAKEY(0) + TILE);
        if (Move == 'u') snake.setSNAKEY(0, snake.getSNAKEY(0) - TILE);
    }
    public void VuotBien() {
        if (snake.getSNAKEX(0) < 0) snake.setSNAKEX(0, WIDTH - TILE);
        if (snake.getSNAKEX(0) >= WIDTH) snake.setSNAKEX(0, 0);
        if (snake.getSNAKEY(0) < 0) snake.setSNAKEY(0, HEIGHT - TILE);
        if (snake.getSNAKEY(0) >= HEIGHT) snake.setSNAKEY(0, 0);
    }
    public void AnMoi() {
        if (snake.getSNAKEX(0) == fruit.getFRUITX() && snake.getSNAKEY(0) == fruit.getFRUITY()) {
            if (Snake.LENGTH < 200) Snake.LENGTH++;
            int nextfruitX, nextfruitY;
            do {
                nextfruitX = rd.nextInt(WIDTH / TILE) * TILE;
                nextfruitY = rd.nextInt(HEIGHT / TILE) * TILE;
            } while (FruitTrung(nextfruitX, nextfruitY));
            fruit.setFRUITX(nextfruitX);
            fruit.setFRUITY(nextfruitY);
        }
    }
    public boolean FruitTrung(int fruitx,int fruity){
        for (int i=0;i<Snake.LENGTH;i++){
            if(snake.getSNAKEX(i)==fruitx&&snake.getSNAKEY(i)==fruity) return true;
//            if(GameMode){
//              for (int j = 0; j <WIDTH; j +=TILE){
//                  if(fruitx==0&&fruity== j) return true;
//                  if(fruitx==HEIGHT-TILE&&fruity== j) return true;
//              }
//              for (int j = 0; j <HEIGHT; j +=TILE){
//                  if(fruitx== j &&fruity==0) return true;
//                  if(fruitx== j &&fruity==WIDTH-TILE) return true;
//              }
//            }
        }
        return false;
    }
    public void VaCham() {
        for (int i = 1; i < Snake.LENGTH; i++) {
            if (snake.getSNAKEX(0) == snake.getSNAKEX(i) && snake.getSNAKEY(0) == snake.getSNAKEY(i)) Over = true;
//            if (GameMode) {
//                if (snake.getSNAKEX(0) == 0 || snake.getSNAKEX(0) >= WIDTH || snake.getSNAKEY(0) == 0 || snake.getSNAKEY(0) >= HEIGHT)
//                    Over = true;
//            }
        }
    }
    public void Save(){
        int[]snakex=new int[Snake.LENGTH];
        int[]snakey=new int[Snake.LENGTH];
        for(int i=0;i<Snake.LENGTH;i++){
            snakex[i]=snake.getSNAKEX(i);
            snakey[i]=snake.getSNAKEY(i);
        }
        GameState gamestate=new GameState(Snake.LENGTH,Move,fruit.getFRUITX(),fruit.getFRUITY(),snakex,snakey);
        databasemanage.SaveGame(gamestate);
    }
    public void Load(){
        GameState gamestate=databasemanage.LoadGame();
        if(gamestate!=null){
            Snake.LENGTH=gamestate.getLength();
            Move= gamestate.getMove();
            fruit.setFRUITX(gamestate.getFruitx());
            fruit.setFRUITY(gamestate.getFruity());

            for(int i=0;i<gamestate.getLength();i++){
                snake.setSNAKEX(i,gamestate.getSnakex(i));
                snake.setSNAKEY(i,gamestate.getSnakey(i));
            }
            repaint();
        }else
            System.out.println("No saved game state to load.");
    }
//    public void Choice(){
//         Object[] options = {"mac dinh", "hop"};
//         Object[] objects=new Object[]{"choi moi","choi tiep"};
//         int choice1 = JOptionPane.showOptionDialog(this,"Chon che do choi","Snake Game",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null,options,options[0]);
//         if(choice1==0) GameMode=false;
//         else GameMode=true;
//         int choice2=JOptionPane.showOptionDialog(this,"Options","Snake Game",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,objects,objects[0]);
//         if(choice2==0) Resset();
//         else Load();
//         timer.start();
//    }
//    public void Resset(){
//        Snake.LENGTH=2;
//        Move='r';
//        Snake snake=new Snake();
//        Fruit fruit=new Fruit();
//        repaint();
//    }
    public void CloseDatabase() {
        try {
            databasemanage.CloseDTB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
