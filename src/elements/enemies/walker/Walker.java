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
import threads.ChaseThread;

public class Walker extends Enemy implements Movable{
    
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int LIFE = 50;
    
    //MOVE
    public static final int STEP = 1;
    public static final int COOLDOWNMOVE = 10;
    private ChaseThread chaseThread;
    
    public Walker(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.RED);
        this.lifeBar = LIFE; //VIDA
    }
    
    @Override
    public void touched(Collidable collidable) {
        for(Boundable boundable: boundables){
            if(boundable == collidable){
                if(!checkCollision(collidable, UP)){
                    setY(y -= STEP);
                }if(!checkCollision(collidable, DOWN)){
                    setY(y += STEP);
                }if(!checkCollision(collidable, RIGHT)){
                    setX(x += STEP);
                }if(!checkCollision(collidable, LEFT)){
                    x -= STEP;
                    setX(x -= STEP);
                }
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
        
        //ES UNA OPCIÓN CUANDO NO PUEDES PASAR DE LAS HABITACIONES
        chaseThread = new ChaseThread(this, player);
        chaseThread.start();
    }
}
