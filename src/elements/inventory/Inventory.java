/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.inventory;

import elements.player.Player;
import elements.weapons.Stick;
import elements.weapons.Weapon;
import exceptions.FullInventoryException;
import interfaces.Collidable;
import java.awt.Graphics;

public class Inventory {
    
    private Player player;
    private Space[] spaces;
    private Space selectedWeapon;
    public static final int SPACES_WEAPONS = 2;
    
    public Inventory(Collidable collidable) {
        this.player = player;
        spaces = new Space[SPACES_WEAPONS];
        
        int px = 800;
        int py = 40;
        for(int s = 0; s < SPACES_WEAPONS; s++){
            spaces[s] = new Space(px, py);
            px += 60;
        }
        
        //!!TEST!!
        addWeapon(new Stick(player));
        selectedWeapon = spaces[0];
    }
    
    public void draw(Graphics g) {
        for(Space space: spaces){
            space.draw(g);
        }
    }
    
    public void addWeapon(Weapon weapon) throws FullInventoryException{
        for(Space space: spaces){
            if(space.getWeapon() == null){
                space.setWeapon(weapon);
                return;
            }
        }
        throw new FullInventoryException();
    }
    
    //GETTERS AND SETTERS
    public Weapon getSelectedWeapon() {
        return selectedWeapon.getWeapon();
    }
    
}
