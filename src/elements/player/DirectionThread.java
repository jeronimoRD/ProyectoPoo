/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.player;

import interfaces.Movable;
import java.awt.Polygon;

public class DirectionThread extends Thread{
    private boolean running;

    //TRIANGLES
    private Movable movable;
    
    private int x;
    private int y;
    
    private Polygon triangleUp; 
    private Polygon triangleDown; 
    private Polygon triangleRight; 
    private Polygon triangleLeft; 
    
    public DirectionThread(Movable movable) {
        running = true;
        this.movable = movable;
        x = movable.getX();
        y = movable.getY();
        
        createTriangles(x, y);
    }

    @Override
    public void run() {
        while(running){
            System.out.print("");
            if(movable.getX() != x | movable.getY() != y){
                createTriangles(movable.getX(), movable.getY());
                
                x = movable.getX();
                y = movable.getY();
            }
        }
    }
    
    public void createTriangles(int x, int y){
        //TRIANGLE UP
        int[] xPoints1 = {-2500, x, 2500};
        int[] yPoints1 = {-2500, y, -2500};
        triangleUp = new Polygon(xPoints1, yPoints1, 3);
        
        //TRIANGLE DOWN
        int[] xPoints2 = {-2500, x, 2500};
        int[] yPoints2 = {2500, y, 2500};
        triangleDown = new Polygon(xPoints2, yPoints2, 3);
        
        //TRIANGLE RIGHT
        int[] xPoints3 = {2500, x, 2500};
        int[] yPoints3 = {-2500, y, 2500};
        triangleRight = new Polygon(xPoints3, yPoints3, 3);
        
        //TRIANGLE LEFT
        int[] xPoints4 = {-2500, x, -2500};
        int[] yPoints4 = {-2500, y, 2500};
        triangleLeft = new Polygon(xPoints4, yPoints4, 3);
    }
    
    public void stopRun(){
        this.running = false;
    }
    public void startRun(){
        this.running = true;
    }

    public Polygon getTriangleUp() {
        return triangleUp;
    }

    public Polygon getTriangleDown() {
        return triangleDown;
    }

    public Polygon getTriangleRight() {
        return triangleRight;
    }

    public Polygon getTriangleLeft() {
        return triangleLeft;
    }
    
    
}
