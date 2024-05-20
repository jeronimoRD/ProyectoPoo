/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies;

import interfaces.Boundable;
import interfaces.Collidable;
import static interfaces.Collidable.DOWN;
import static interfaces.Collidable.LEFT;
import static interfaces.Collidable.RIGHT;
import static interfaces.Collidable.UP;
import interfaces.Movable;
import threads.WalkerThread;
import java.awt.Color;

public class Walker extends Creature implements Movable{
    
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    //MOVE
    public static final int STEP = 5;
    private WalkerThread walkerThread;
    private int lastMove = -1;
    
    //LIFE
    public static final int LIFE = 50;
    
    public Walker(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.RED);
        this.lifeBar = LIFE; //VIDA
        
        walkerThread = new WalkerThread(this); //¿(this)debería tener una interfaz?
        walkerThread.start();
    }
    
    @Override
    public void touched(Collidable collidable) {
        for(Boundable boundable: boundables){
            if(collidable == boundable){
                if(lastMove == UP){
                    y = boundable.getY()+boundable.getHeight();
                }
                if(lastMove == DOWN){
                    y = boundable.getY() - HEIGHT;
                }
                if(lastMove == RIGHT){
                    x = boundable.getX() - WIDTH;
                }
                if(lastMove == LEFT){
                    x = boundable.getX()+boundable.getWidth();
                }
            }
        }
    }
    
    @Override
    public void die(){
        walkerThread.stopRun();
        walkerThread = null;
    }
    
    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }

    @Override
    public int getStep() {
        return STEP;
    }
}
