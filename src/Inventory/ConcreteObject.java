/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

import elements.Sprite;

/**
 *
 * @author Isa
 */
public class ConcreteObject extends Object{
        public ConcreteObject(final int id, final int x, final int y, final int width, final int height) {
        super(id, x, y, width, height);
    }

    @Override
    public Sprite getSprite() {
        // Implementación específica del sprite
        return null;
    }

    @Override
    public int getWidth() {
        return 50; // O el ancho que desees
    }

    @Override
    public int getHeight() {
        return 50; // O la altura que desees
    }
}
