/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

public interface Collidable {
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    
    public boolean checkCollision(Collidable collidable);
}
