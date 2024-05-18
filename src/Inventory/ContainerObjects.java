/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

import java.awt.Point;

/**
 *
 * @author Isa
 */
public class ContainerObjects {

    private Point position;
    private Object[] objects;
    
    
    private Inventory inventory;
    

    public ContainerObjects(final Point position, final int[] objectIds, final int[] quantities) {
        if (objectIds.length != quantities.length) {
            throw new IllegalArgumentException("Object IDs and quantities arrays must have the same length.");
        }

        this.position = position;
        this.objects = new Object[objectIds.length];

        for (int i = 0; i < objectIds.length; i++) {
            this.objects[i] = inventory.getObject(objectIds[i]);
            if (this.objects[i] != null) {
                this.objects[i].increaseQuantity(quantities[i]);
            }
        }
    }

    public Point getPosition() {
        return position;
    }

    public Object[] getObjects() {
        return objects;
    }
}
