/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.awt.Graphics;

public interface Collidable {
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    public void draw(Graphics g);
    
    public boolean checkCollisionHitbox(Collidable collidable); //TEST
    public boolean checkCollisionTouch(Collidable collidable); //TEST
}
