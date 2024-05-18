/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Weapons;

import java.awt.Rectangle;
import Inventory.Object;
import java.util.ArrayList;
import psychiatric.Player;

/**
 *
 * @author Isa
 */
public abstract class Weapon extends Object {

    protected int attackMinimum;
    protected int attackMaximum;
    protected boolean automatic;
    protected boolean penetrating;
    protected double attacksForSecond;
    protected int updatesForNextAttack;

    public Weapon(final int x, final int y, final int attackMinimum) {
        super(x, y);

        this.attackMinimum = attackMinimum;
        this.attackMaximum = attackMaximum;
        this.automatic = automatic;
        this.penetrating = penetrating;
        this.attacksForSecond = attacksForSecond;
        this.updatesForNextAttack = updatesForNextAttack;
    }

    public abstract ArrayList<Rectangle> getReach(final Player Player);

    public void updates() {
        if (updatesForNextAttack > 0) {
            updatesForNextAttack--;
        }
    }
    
    
}
