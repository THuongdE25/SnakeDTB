package SnakeGame;

public class GameState {
    private int length,fruitx,fruity;
    private int snakex[],snakey[];
    private char move;

    public GameState(int length, char move, int fruitx, int fruity, int[] snakex, int[] snakey) {
        this.length = length;
        this.move = move;
        this.fruitx = fruitx;
        this.fruity = fruity;
        this.snakex = snakex;
        this.snakey = snakey;
    }

    public char getMove() {
        return move;
    }

    public int getLength() {
        return length;
    }

    public int getFruitx() {
        return fruitx;
    }

    public int getFruity() {
        return fruity;
    }

    public int getSnakex(int i) {
        return snakex[i];
    }

    public int getSnakey(int i) {
        return snakey[i];
    }
}
