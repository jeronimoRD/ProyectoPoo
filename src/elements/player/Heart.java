/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.player;
import elements.Sprite;
import java.awt.Color;
import java.awt.Graphics;

public class Heart extends Sprite {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    
    private boolean live;
    
    public Heart(int x, int y) {
        super(x, y, WIDTH, HEIGHT, null);
    }

    @Override
    public void draw(Graphics g) {
        if(live){
            g.setColor(Color.RED);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }else{
            g.setColor(Color.GRAY);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
