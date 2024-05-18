/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.inventory;

import java.awt.Graphics;

public class Inventory {
    
    private Space[] spaces;
    public static final int SPACES_WEAPONS = 2;
    
    public Inventory() {
        spaces = new Space[SPACES_WEAPONS];
        
        int px = 800;
        int py = 40;
        for(int s = 0; s < SPACES_WEAPONS; s++){
            spaces[s] = new Space(px, py);
            px += 60;
        }
    }
    
    public void draw(Graphics g) {
        for(Space space: spaces){
            space.draw(g);
        }
    }
}
