/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;
import interfaces.Drawable;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;
import static psychiatric.Room.HEIGHT;
import static psychiatric.Room.WIDTH;

public class Psychiatric {
    private Level [] levels;
    private Level actualLevel;
    private Drawable drawable;
    private BufferedImage buffer;  

    public static final int LEVELS = 3; //POSSIBLE NORMAL ARRAY
    
    public Psychiatric() {
        Player player = new Player(Room.WIDTH/2, Room.HEIGHT/2);
        levels = new Level[LEVELS];
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);  // Inicializar búfer

        for(int i = 0; i < LEVELS; i++){
            try {
                levels[i] = new Level();
            } catch (IOException ex) { //EXCEPTION FILES
                Logger.getLogger(Psychiatric.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        
        actualLevel = levels[0];
        actualLevel.setPlayer(player);
    }
    
    public int getWidth(){
        return Room.WIDTH;
    }
    
    public int getHeight(){
        return Room.HEIGHT;
    }
    
    public void draw(Graphics g) {
        Graphics bufferGraphics = buffer.getGraphics();

        actualLevel.draw(bufferGraphics);
        g.drawImage(buffer, 0, 0, null);
        //bufferGraphics.dispose();
        if(drawable == null){
            drawable.redraw();
        }else{
            System.out.println("cuca");
        }
    }
    
    public void keyPressed(int code){
        if(code == KeyEvent.VK_UP | code == KeyEvent.VK_DOWN | code == KeyEvent.VK_RIGHT | code == KeyEvent.VK_LEFT){
            actualLevel.keyPressed(code);
        }
    }
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
