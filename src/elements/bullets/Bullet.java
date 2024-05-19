/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.bullets;

import elements.Sprite;
import interfaces.Boundable;
import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import threads.BulletThread;

public class Bullet extends Sprite implements Collidable{

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int STEP = 5;
    
    protected BulletThread bulletThread;
    protected ArrayList<Boundable> boundables;
    protected ArrayList<Bullet> bullets;
    protected Damageable player;
    protected boolean explode;
    
    public Bullet(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.ORANGE);
        boundables = new ArrayList<>();
        bulletThread = new BulletThread(this);
        explode = false;
    }
    
    public void move(int direction){
        if(!bulletThread.isRunning()){
            bulletThread.setDirection(direction);
            bulletThread.start();
        }
        bulletThread.setRunning(true);
    }
    
    @Override
    public void draw(Graphics g) {
        if(!explode){
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }

    @Override
    public boolean checkCollision(Collidable collidable) {
        //PUNTO ARRIBA-IZQUIERDO
        if((collidable.getY() <= y & y <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x & x <= collidable.getX() + collidable.getWidth())){
            return true;
        //PUNTO ABAJO-DERECHA
        }else if((collidable.getY() <= y + height & y + height <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x + width & x + width <= collidable.getX() + collidable.getWidth())){
            return true;
         //PUNTO ARRIBA-DERECHA
        }else if((collidable.getY() <= y & y <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x + width & x + width <= collidable.getX() + collidable.getWidth())){
            return true;
        //PUNTO ABAJO-IZQUIERDA    
        }else if((collidable.getY() <= y + height & y + height <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x & x <= collidable.getX() + collidable.getWidth())){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCollision(Collidable collidable, int direction) {
        if(direction == UP){
            if(y == collidable.getY() + collidable.getHeight()){
                if(x <= collidable.getX() & collidable.getX() <= x + width){
                    return true;
                }
                else if(x <= collidable.getX() + collidable.getWidth() & collidable.getX() + collidable.getWidth() <= x + width){
                    return true;
                }
            }else{
                return false;
            }
        }
        
        else if(direction == DOWN){
            if(y + height == collidable.getY()){
                if(x <= collidable.getX() & collidable.getX() <= x + width){
                    return true;
                }
                else if(x <= collidable.getX() + collidable.getWidth() & collidable.getX() + collidable.getWidth() <= x + width){
                    return true;
                }
            }else{
                return false;
            }
        }
        
        else if(direction == LEFT){
            if(x == collidable.getX() + collidable.getWidth()){
                if(y <= collidable.getY() & collidable.getY() <= y + height){
                    return true;
                }
                else if(y <= collidable.getY() + collidable.getHeight() & collidable.getY() + collidable.getHeight() <= y + height){
                    return true;
                }
            }else{
                return false;
            }
        }
        
        else if(direction == RIGHT){
            if(x + width == collidable.getX()){
                if(y <= collidable.getY() & collidable.getY() <= y + height){
                    return true;
                }
                else if(y <= collidable.getY() + collidable.getHeight() & collidable.getY() + collidable.getHeight() <= y + height){
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
    }
    
    //GETTERS AND SETTERS
    public void setPlayer(Damageable player) {
        this.player = player;
    }

    public ArrayList<Boundable> getBoundables() {
        return boundables;
    }
    
    public void setBoundables(ArrayList<Boundable> boundables) {
        this.boundables = boundables;
    }

    public Damageable getPlayer() {
        return player;
    }
    
    public BulletThread getBulletThread() {
        return bulletThread;
    }

    public boolean isExplode() {
        return explode;
    }

    public void setExplode(boolean explode) {
        this.explode = explode;
    }

    @Override
    public void touched(Collidable collidable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
