/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.awt.Graphics;

public interface Collidable {
    
    //REPEATS IN LEVEL
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    public void draw(Graphics g);
    
    public boolean checkCollisionHitbox(Collidable collidable); //TEST
    public boolean checkCollisionHitbox(Collidable collidable, int direction); //TEST
}
