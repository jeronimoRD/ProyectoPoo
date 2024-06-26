/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;
import elements.weapons.Weapon;
import java.awt.Graphics;

public interface Collectible extends Collidable{
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    
    public boolean getGrabed();
    public void draw(Graphics g);
    
    //GETTERS AND SETTERS
    public Weapon grabWeapon();
    public void throwWeapon();
    
    //TYPE
    public int getType();
}