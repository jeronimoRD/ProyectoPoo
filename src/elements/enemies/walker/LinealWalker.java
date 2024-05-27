/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies.walker;

import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import threads.MoveLinealThread;

public class LinealWalker extends Walker{
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int LIFE = 50;
    private MoveLinealThread moveLinealThread;
    
    public LinealWalker(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.green, LIFE);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        update();
    }
    
    @Override
    public void update() {
        if(stunned.isRecover()){
            moveLinealThread.setPause(true);
        }else{
            moveLinealThread.setPause(false);
        }
    }

    @Override
    public void touched(Collidable collidable) {
        for(Collidable boundable: boundables){
            if(boundable == collidable){
                if(moveLinealThread.getDirection() == UP){
                    moveLinealThread.setDirection(DOWN);
                }
                else if(moveLinealThread.getDirection() == DOWN){
                    moveLinealThread.setDirection(UP);
                }
            }
        }
    }

    @Override
    public void die() {
        moveLinealThread.stopRun();
    }

    @Override
    public void setPlayer(Damageable player) {
        this.player = player;
        
        moveLinealThread = new MoveLinealThread(this, UP);
        moveLinealThread.start();
    }
}
