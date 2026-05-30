package SnakeGame;

public class Snake {
    public static int LENGTH=2 ;
    private int[] SNAKEX,SNAKEY;
    public Snake(){
        this.SNAKEX=new int[200];
        this.SNAKEY=new int[200];
        this.SNAKEX[0]=100;
        this.SNAKEY[0]=100;
    }

    public int getSNAKEX(int i) {
        return SNAKEX[i];
    }

    public int getSNAKEY(int i) {
        return SNAKEY[i];
    }

    public void setSNAKEX(int i,int SNAKEX) {
        this.SNAKEX[i] = SNAKEX;
    }

    public void setSNAKEY(int i,int SNAKEY) {
        this.SNAKEY[i] = SNAKEY;
    }
}
