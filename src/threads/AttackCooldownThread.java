/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.weapons.Weapon;

/**
 *
 * @author korez
 */
public class AttackCooldownThread extends CooldownThread{
    private Weapon weapon; //¿Interfaz?
    
    public AttackCooldownThread(Weapon weapon){
        this.weapon = weapon;
    }
    
    @Override
    public void run(){
        while(running){
            if(recover){
                try{
                    Thread.sleep(time); //SPEED OF COOLDOWN
                } catch (InterruptedException ex) {
                    System.out.println("ERROR");
                }
                weapon.setHitbox(null);
                recover = false;
            }
            System.out.print("");
        }
    }
}
