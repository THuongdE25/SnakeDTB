package SnakeGame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManage {
    private final DatabaseConnect db=new DatabaseConnect();
    public void SaveGame(GameState gamestate){
        String sql="INSERT INTO GamesnState(length,move,fruitx,fruity,snakex,snakey)VALUES(?,?,?,?,?,?)";
        StringBuilder sx=new StringBuilder();
        StringBuilder sy=new StringBuilder();
        for(int i=0;i<gamestate.getLength()-1;i++){
            sx.append(gamestate.getSnakex(i)).append(',');
            sy.append(gamestate.getSnakey(i)).append(',');
        }
        sx.append(gamestate.getSnakex(gamestate.getLength()-1));
        sy.append(gamestate.getSnakey(gamestate.getLength()-1));

        try (PreparedStatement ps=db.getConn().prepareStatement(sql)){
            ps.setInt(1,gamestate.getLength());
            ps.setString(2,String.valueOf(gamestate.getMove()));
            ps.setInt(3,gamestate.getFruitx());
            ps.setInt(4,gamestate.getFruity());
            ps.setString(5,sx.toString());
            ps.setString(6,sy.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public GameState LoadGame(){
        String sql="SELECT TOP 1 * FROM GamesnState ORDER BY id DESC";
        try (Statement stm=db.getConn().createStatement();
             ResultSet rss=stm.executeQuery(sql)){
            if (rss.next()){
                int length=rss.getInt("length");
                char move=rss.getString("move").charAt(0);
                int fruitx=rss.getInt("fruitx");
                int fruity=rss.getInt("fruity");

                String[] snakestrx=rss.getString("snakex").split(",");
                String[] snakestry=rss.getString("snakey").split(",");
                int[] snakex=new int[length];
                int[] snakey=new int[length];
                for(int i=0;i<length;i++){
                    snakex[i]=Integer.parseInt(snakestrx[i]);
                    snakey[i]=Integer.parseInt(snakestry[i]);
                }
                return new GameState(length,move,fruitx,fruity,snakex,snakey);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void CloseDTB(){
        try {
            db.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
