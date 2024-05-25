/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

public interface Collidable {
    
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3; 
    
    public boolean checkCollision(Collidable collidable);
    public boolean checkCollision(Collidable collidable, int direction);
    
    //GETTERS AND SETTERS
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    
    //THREAD
    public void touched(Collidable collidable);
}
