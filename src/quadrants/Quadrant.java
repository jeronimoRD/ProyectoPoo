/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quadrants;

import elements.Sprite;
import interfaces.Collidable;
import java.awt.Color;

public abstract class Quadrant extends Sprite implements Collidable{

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    
    public Quadrant(int x, int y, Color color) {
        super(x, y, WIDTH, HEIGHT, color);
    }
}
