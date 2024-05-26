/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies.walker;

import elements.enemies.Enemy;
import interfaces.Boundable;
import interfaces.Collidable;
import interfaces.Damageable;
import interfaces.Movable;
import java.awt.Color;
import java.util.ArrayList;
import threads.ChaseThread;
import threads.TouchCollisionThread;

public class Walker extends Enemy implements Movable{
    
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int LIFE = 50;
    
    //MOVE
    public static final int STEP = 1;
    public static final int COOLDOWNMOVE = 10;
    private ChaseThread chaseThread;
    
    //COLLIDABLES
    private TouchCollisionThread touchCollisionThread;
    
    public Walker(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.RED);
        this.lifeBar = LIFE; //VIDA
        touchCollisionThread = new TouchCollisionThread(this);
        touchCollisionThread.start();
    }
    
    @Override
    public void touched(Collidable collidable) {
        for(Boundable boundable: boundables){
            if(boundable == collidable){
                x = chaseThread.getPx();
                y = chaseThread.getPy();
            }
        }
    }
    
    @Override
    public void die(){
        chaseThread.stopRun();
    }

    @Override
    public int getStep() {
        return STEP;
    }

    @Override
    public int getCoolDownMove() {
        return COOLDOWNMOVE;
    }
    @Override
    public void setPlayer(Damageable player){
        this.player = player;
        
        chaseThread = new ChaseThread(this, player);
        chaseThread.start();
    }
    
    @Override
    public void setBoundables(ArrayList<Boundable> boundables){
        this.boundables = boundables;
        
        ArrayList<Collidable> collidables = new ArrayList<>();
        for(Boundable boundable: boundables){
            collidables.add(boundable);
        }
        
        touchCollisionThread.addCollidable(collidables);
    }
}
