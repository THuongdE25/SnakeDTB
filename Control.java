package SnakeGame;

import java.awt.*;

public interface Control {
    public void Draw(Graphics g);
    public void DrawFruit(Graphics g);
    public void DrawSnake(Graphics g);
//    public void DrawWall(Graphics g);

    public void DiChuyen();
    public void DoiHuong();
    public void VuotBien();
    public void AnMoi();
    public void VaCham();

    public void Save();
    public void Load();
}
