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
import psychiatric.Reward;

public class RewardRoom extends Room{
        
    private int numberRewards;
    private ArrayList<Reward> rewards;
    
    public RewardRoom(File editor, int numberRewards) {
        super(editor);
        this.numberRewards = numberRewards;
        
        //CREATE ROOM
        collisions = new ArrayList<>();
        rewards = new ArrayList<>();
        
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
        
        //ADD REWARDS
        rewards = new ArrayList<>();
        
        for(int i = 0; i < numberRewards; i++){
            boolean aggregate;
            Reward reward = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                reward = new Reward(px, py);
                aggregate = true;

                for(Collidable collision: collisions){
                    if(reward.checkCollision(collision)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            rewards.add(reward);
            collisions.add(reward);
        }
    }
}
