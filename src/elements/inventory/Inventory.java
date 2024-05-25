/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.inventory;

import elements.player.Heart;
import elements.player.Player;
import elements.weapons.Weapon;
import exceptions.FullInventoryException;
import java.awt.Color;
import java.awt.Graphics;
import sprites.Icon;
import sprites.IconHearthPosion;

public class Inventory {
    
    private Player player;
    private Space[] spaces;
    private Space selectedWeapon;
    private Icon[] icons;
    public static final int SPACES_WEAPONS = 2;
    
    public Inventory(Player player) {
        this.player = player;
        spaces = new Space[SPACES_WEAPONS];
        
        int px = 800;
        int py = 820;
        for(int s = 0; s < SPACES_WEAPONS; s++){
            spaces[s] = new Space(px, py);
            px += 80;
        }
        
        //PILLS
        icons = new Icon[3];
        IconHearthPosion iconH = new IconHearthPosion(750, 820);
        icons[0] = iconH;
        selectedWeapon = spaces[0];
    }
    
    public void draw(Graphics g) {
        //WEAPONS
        for(Space space: spaces){
            space.draw(g);
        }
        
        //HEARTS
        for(Heart heart: player.getHearts()){
            heart.draw(g);
        }
        
        //PILL
        for(Icon icon: icons){
            if(icon != null)
                icon.draw(g);
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(player.getHearthPills()), 770, 830); //FONT
        }
    }
    
    public void changeSelectedWeapon(int index){
        selectedWeapon = spaces[index];
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
