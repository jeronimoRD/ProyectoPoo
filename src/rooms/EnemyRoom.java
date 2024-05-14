/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rooms;

import collidables.Wall;
import interfaces.Collidable;
import static rooms.Room.DOOR;
import static rooms.Room.FLOOR;
import static rooms.Room.WALL;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import psychiatric.Enemy;

public class EnemyRoom extends Room{
    
    private int numberEnemies;
    private ArrayList<Enemy> enemies;
    
    public EnemyRoom(File editor, int numberEnemies) {
        super(editor);
        this.numberEnemies = numberEnemies;
        
        //CREATE ROOM
        collisions = new ArrayList<>();
        
        try {
            FileReader file = new FileReader(editor);
            int instruction;
            
            int quadrants_width = WIDTH/100;
            int quadrants_height = HEIGHT/100;
            
            int quadrantX = 0;
            int quadrantY = 0;
            int row = 0;
            int column = 0;
            
            while ((instruction = file.read()) != -1) {
                if((char)instruction == WALL){
                    collisions.add(new Wall(quadrantX, quadrantY));
                    
                    column += 1;
                    quadrantX += Wall.WIDTH;
                    
                }else if((char)instruction == FLOOR){
                    
                    column += 1;
                    quadrantX += Wall.WIDTH;
                    
                }else if((char)instruction == DOOR){
                    //collisions.add(new rDoor(quadrantX, quadrantY));
                    if(row == 0){
                        this.doorUp = true;
                    }else if(row + 1 == quadrants_height){
                        this.doorDown = true;
                    }else if(column == 0){
                        this.doorLeft = true;
                    }else if(column + 1 == quadrants_width){
                        this.doorRight = true;
                    }
                    
                    column += 1;
                    quadrantX += Wall.WIDTH;
                    
                }else{ //2 SPACES FOR LINE
                    if(column == 0){
                        row += 1;
                        quadrantY += Wall.HEIGHT;
                    }else{
                        column = 0;
                        quadrantX = 0; 
                    }
                }
            }
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Error: File no found");
        }
        
        //ADD ENEMIES
        enemies = new ArrayList<>();
        
        for(int i = 0; i < numberEnemies; i++){
            boolean aggregate;
            Enemy enemy = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                enemy = new Enemy(px, py);
                aggregate = true;

                for(Collidable collision: collisions){
                    if(enemy.checkCollision(collision)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            enemies.add(enemy);
            collisions.add(enemy);
        }
    }
}
