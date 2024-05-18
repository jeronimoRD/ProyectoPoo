/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;

import Inventory.Inventory;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Logger;

public class Psychiatric {

    private Player player;
    private Inventory inventory;
    private Room room;
    private Level[] levels;
    private Level actualLevel;
    public static final int LEVELS = 3; //POSSIBLE NORMAL ARRAY

    public Psychiatric() {
        Player player = new Player(Room.WIDTH / 2, Room.HEIGHT / 2);
        levels = new Level[LEVELS];

        for (int i = 0; i < LEVELS; i++) {
            try {
                levels[i] = new Level();
            } catch (IOException ex) { //EXCEPTION FILES
                Logger.getLogger(Psychiatric.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

        actualLevel = levels[0];
        actualLevel.setPlayer(player);
    }

    public void pickUpObject(int x, int y) {
        System.out.println("Recibiendo solicitud para recoger objeto en coordenadas: (" + x + ", " + y + ")");
        if (actualLevel != null) {
            Player player = actualLevel.getPlayer();
            if (player != null) {
                player.pickUpObject(actualLevel, x, y);
        }
    }
    }

    public int getWidth() {
        return Room.WIDTH;
    }

    public int getHeight() {
        return Room.HEIGHT;
    }

    public void draw(Graphics g) {
        actualLevel.draw(g);
    }

    public void keyPressed(int code) {
        if (code == KeyEvent.VK_UP | code == KeyEvent.VK_DOWN | code == KeyEvent.VK_RIGHT | code == KeyEvent.VK_LEFT) {
            actualLevel.keyPressed(code);
        }
    }
    
        public void setPlayer(Player player) {
        this.player = player;
    }

}
