package SnakeGame;

import java.util.Random;

import static SnakeGame.WindowPanel.*;

public class Fruit {
    Random rd=new Random();
    private int FRUITX,FRUITY;
    public Fruit(){
        this.FRUITX= rd.nextInt(WIDTH/TILE)*TILE;
        this.FRUITY= rd.nextInt(HEIGHT/TILE)*TILE;
    } public Fruit(int fruitx,int fruity){
        this.FRUITX= fruitx;
        this.FRUITY= fruity;
    }
    public int getFRUITX() {
        return FRUITX;
    }

    public int getFRUITY() {
        return FRUITY;
    }

    public void setFRUITX(int FRUITX) {
        this.FRUITX = FRUITX;
    }

    public void setFRUITY(int FRUITY) {
        this.FRUITY = FRUITY;
    }
}
