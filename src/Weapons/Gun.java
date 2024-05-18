/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Weapons;

import elements.Sprite;
import enemies.Enemy;
import psychiatric.*;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Isa
 */
public class Gun extends Weapon {

    public Gun(final int x, final int y, final int attackMinimum, final int attackMaximum, final boolean automatic, final boolean penetrating, final double attacksForSecond, final int updatesForNextAttack) {
        super(x, y, attackMinimum);
    }

    public void attack(final ArrayList<Enemy> enemies) {

    }

    @Override
    public ArrayList<Rectangle> getReach(Player Player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReach'");
    }

    public void scope() {

    }

    public Sprite getSprite() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getHeight() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
