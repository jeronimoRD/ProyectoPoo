/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;
import elements.weapons.Weapon;
import java.awt.Graphics;

public interface Collectible extends Collidable{
    public boolean getGrabed();
    public void draw(Graphics g);
    
    //GETTERS AND SETTERS
    public Weapon grabWeapon();
}