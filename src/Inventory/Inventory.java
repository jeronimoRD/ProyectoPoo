/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

import Consumables.Consumable;
import java.util.ArrayList;
import Weapons.Weapon;
import java.awt.Rectangle;
import static psychiatric.Player.HEIGHT;
import static psychiatric.Player.WIDTH;

/**
 *
 * @author Isa
 */
public class Inventory {

    public final ArrayList<Object> objects;

    public Inventory() {
        objects = new ArrayList<>();
    }

    public void collectObjects(final ContainerObjects co) { // Corrected method name to follow Java naming conventions
        for (Object object : co.getObjects()) {
            if (existingObject(object)) { // Corrected method name
                increaseObject(object, object.getQuantity()); // Corrected method name and fixed call to correct method
            } else {
                objects.add(object);
            }
        }
    }

    public boolean existingObject(final Object object) { // Corrected method name
        for (Object existingObject : objects) {
            if (existingObject.getId() == object.getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean increaseObject(final Object object, final int quantity) { // Corrected method name
        for (Object existingObject : objects) {
            if (existingObject.getId() == object.getId()) {
                return existingObject.increaseQuantity(quantity); // Corrected method call
            }
        }
        return false;
    }

    public ArrayList<Object> getConsumables() {
        ArrayList<Object> consumables = new ArrayList<>();
        for (Object object : objects) {
            if (object instanceof Consumable) {
                consumables.add(object);
            }
        }
        return consumables;
    }

    public ArrayList<Object> getWeapons() {
        ArrayList<Object> weapons = new ArrayList<>();
        for (Object object : objects) {
            if (object instanceof Weapon) {
                weapons.add(object);
            }
        }
        return weapons;
    }

    public Object getObject(final int id) {
        for (Object existingObject : objects) {
            if (existingObject.getId() == id) {
                return existingObject;
            }
        }
        return null;
    }

    public void addObject(Object object) {
        objects.add(object);
    }

    public boolean collidesWith(Weapon weapon, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
