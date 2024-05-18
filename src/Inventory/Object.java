/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

import Weapons.Weapon;
import elements.Sprite;
import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import static psychiatric.Player.HEIGHT;
import static psychiatric.Player.WIDTH;

/**
 *
 * @author Isa
 */
public abstract class Object extends Sprite implements Damageable {

    private int id;
    private String name;
    private String description;
    protected int WIDTH = 15;
    protected int HEIGHT = 15;
    protected int quantity = 0;
    private int maxQuantity = 20;
    private Weapon weapon;

    private ArrayList<Collidable> collisions;

    private Rectangle positionMenu;

    public Object(final int id, final int x, final int y, final int width, final int height) {
        super(x, y, width, height, Color.MAGENTA); 
        this.id = id;
        this.WIDTH = width; 
        this.HEIGHT = height; 
        this.positionMenu = new Rectangle(0, 0, 0, 0);
        this.collisions = new ArrayList<>();
    }

    public Object(final int x, final int y) {
        this(0, x, y, 15, 15); // Adjusted constructor call with default WIDTH and HEIGHT
    }

    public abstract Sprite getSprite();

    public boolean increaseQuantity(final int increase) { // Corrected method name
        if (quantity + increase <= maxQuantity) {
            quantity += increase;
            return true;
        }
        return false;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean reduceQuantity(final int reduction) {
        if (quantity - reduction >= 0) {
            quantity -= reduction;
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public boolean checkCollisionHitbox(Collidable collidable) {
        int collidableRight = collidable.getX() + collidable.getWidth();
        int collidableBottom = collidable.getY() + collidable.getHeight();
        int thisRight = x + WIDTH;
        int thisBottom = y + HEIGHT;

        return !(x >= collidableRight || thisRight <= collidable.getX()
                || y >= collidableBottom || thisBottom <= collidable.getY());
    }

    @Override
    public boolean checkCollisionTouch(Collidable collidable) {
        if ((collidable.getY() == y + HEIGHT || collidable.getY() + collidable.getHeight() == y)
                && (x <= collidable.getX() && collidable.getX() <= x + WIDTH
                || x <= collidable.getX() + collidable.getWidth() && collidable.getX() + collidable.getWidth() <= x + WIDTH)) {
            return true;
        }
        if ((collidable.getX() == x + WIDTH || collidable.getX() + collidable.getWidth() == x)
                && (y <= collidable.getY() && collidable.getY() <= y + HEIGHT
                || y <= collidable.getY() + collidable.getHeight() && collidable.getY() + collidable.getHeight() <= y + HEIGHT)) {
            return true;
        }
        return false;
    }

    public boolean contains(int x, int y) {
        return x >= this.x && x <= this.x + WIDTH
                && y >= this.y && y <= this.y + HEIGHT;
    }

    public void setCollisions(ArrayList<Collidable> collisions) {
        this.collisions = collisions;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Rectangle getMenu() {
        return positionMenu;
    }

    public void setPositionMenu(final Rectangle positionMenu) {
        this.positionMenu = positionMenu;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Rectangle getPositionMenu() {
        return positionMenu;
    }
}
