/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

public interface Movable extends Collidable{
    public void setX(int x);
    public void setY(int y);
    
    public int getStep();
    public int getCoolDownMove();
    
    //THREAD
    public void touched(Collidable collidable);
}
