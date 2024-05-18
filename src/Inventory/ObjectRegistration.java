/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

/**
 *
 * @author Isa
 */
class ObjectRegistration {

    public static Object getObjects(int idobjects) {
        Object object = null;
        switch (idobjects) {
            // Consumable objects
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            // Implement cases for consumable objects

            // Weapon objects
            case 500:
            case 501:
            case 502:
            case 503:
            case 504:
            case 505:
            // Implement cases for weapon objects

            default:
                break;
        }
        return object;
    }
}
