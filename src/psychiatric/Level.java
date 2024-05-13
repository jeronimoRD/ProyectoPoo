/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;
import rooms.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;

public class Level {
    private File[] oneFiles;
    private File[] twoFiles;
    private File[] threeFiles;
    private Room[] rooms;
    private Room actualRoom;
    
    public static final int ROOMS_TOTAL = 7;
    public static final int ROOMS_REWARDS = 2; 
    
    public static final int HALLWAYS = ROOMS_TOTAL - ROOMS_REWARDS; // >= ROOMS_REWARDS + 2

    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int RIGHT = 2;
    private static final int LEFT = 3;
    
    public Level() {
        
        //CREATE FILES LEVELS
        File newOneFiles = new File("C:\\Users\\korez\\OneDrive\\Documents\\NetBeansProjects\\ProyectoPoo\\src\\filesOneDoor");
        oneFiles = newOneFiles.listFiles();
        
        File newTwoFiles = new File("C:\\Users\\korez\\OneDrive\\Documents\\NetBeansProjects\\ProyectoPoo\\src\\filesTwoDoors");
        twoFiles = newTwoFiles.listFiles();
        
        File newTrheeFiles = new File("C:\\Users\\korez\\OneDrive\\Documents\\NetBeansProjects\\ProyectoPoo\\src\\filesThreeDoors");
        threeFiles = newTrheeFiles.listFiles();
        
        //CREATE ROOMS
        rooms = new Room[ROOMS_TOTAL];
            //INITIAL ROOM
        InitialRoom initialRoom = new InitialRoom(oneFiles[(int)(Math.random()*(oneFiles.length))]);
        rooms[0] = initialRoom;
        
        //CREATE RANDOM HALLWAY
        for(int r = 0; r < HALLWAYS; r++){
            
            if(r == HALLWAYS - 2){
                while(true){
                    FinalRoom finalRoom = new FinalRoom(oneFiles[(int)(Math.random()*(oneFiles.length))]);
                    
                    if(rooms[r].getRoomUp() == null && rooms[r].isDoorUp()){
                        if(finalRoom.isDoorDown()){
                           rooms[r].setRoomUp(finalRoom);
                           finalRoom.setRoomDown(rooms[r]);
                           rooms[rooms.length - 1] = finalRoom;
                           break;
                        }
                    }else if(rooms[r].getRoomDown() == null && rooms[r].isDoorDown()){
                        if(finalRoom.isDoorUp()){
                            rooms[r].setRoomDown(finalRoom);
                            finalRoom.setRoomUp(rooms[r]);
                            rooms[rooms.length - 1] = finalRoom;
                            break;
                        }
                    }else if(rooms[r].getRoomRight() == null && rooms[r].isDoorRight()){
                        if(finalRoom.isDoorLeft()){
                            rooms[r].setRoomRight(finalRoom);
                            finalRoom.setRoomLeft(rooms[r]);
                            rooms[rooms.length - 1] = finalRoom;
                            break;
                        }
                    }else if(rooms[r].getRoomLeft() == null && rooms[r].isDoorLeft()){
                        if(finalRoom.isDoorRight()){
                            rooms[r].setRoomLeft(finalRoom);
                            finalRoom.setRoomRight(rooms[r]);
                            rooms[rooms.length - 1] = finalRoom;
                            break;
                        }
                    }
                }
                break;
            }
            else{
                while(true){
                    EnemyRoom newRoom = new EnemyRoom(twoFiles[(int)(Math.random()*(twoFiles.length))]);
                    
                    if(rooms[r].isDoorUp() && rooms[r].getRoomUp() == null){
                        if(newRoom.isDoorDown()){
                           rooms[r].setRoomUp(newRoom);
                           newRoom.setRoomDown(rooms[r]);
                           rooms[r+1] = newRoom;
                           break;
                        }
                    }else if(rooms[r].isDoorDown() && rooms[r].getRoomDown() == null){
                        if(newRoom.isDoorUp()){
                            rooms[r].setRoomDown(newRoom);
                            newRoom.setRoomUp(rooms[r]);
                            rooms[r+1] = newRoom;
                            break;
                        }
                    }else if(rooms[r].isDoorRight() && rooms[r].getRoomRight() == null){
                        if(newRoom.isDoorLeft()){
                            rooms[r].setRoomRight(newRoom);
                            newRoom.setRoomLeft(rooms[r]);
                            rooms[r+1] = newRoom;
                            break;
                        }
                    }else if(rooms[r].isDoorLeft() && rooms[r].getRoomLeft() == null){
                        if(newRoom.isDoorRight()){
                            rooms[r].setRoomLeft(newRoom);
                            newRoom.setRoomRight(rooms[r]);
                            rooms[r+1] = newRoom;
                            break;
                        }
                    }
                }
            }
        }
        
        //CREATE REWARDS ROOMS
        for(int rr = 0; rr < ROOMS_REWARDS; rr++){
            int doors = 0;
            int indexRoom = (int)(Math.random()*(HALLWAYS - 2))+1;
            
            if(rooms[indexRoom].isDoorUp()){
                doors ++;
            }if(rooms[indexRoom].isDoorDown()){
                doors ++;
            }if(rooms[indexRoom].isDoorRight()){
                doors ++;
            }if(rooms[indexRoom].isDoorLeft()){
                doors ++;
            }
            
            if(doors == 2){
                while(true){
                    EnemyRoom newRoom = new EnemyRoom(threeFiles[(int)(Math.random()*(threeFiles.length))]);
                    
                    if((rooms[indexRoom].isDoorUp() & !newRoom.isDoorUp()) | (rooms[indexRoom].isDoorDown() & !newRoom.isDoorDown())
                    | (rooms[indexRoom].isDoorRight() & !newRoom.isDoorRight()) | (rooms[indexRoom].isDoorLeft() & !newRoom.isDoorLeft())){
                    }else{
                        
                        //ASSOCIATION
                        newRoom.setRoomUp(rooms[indexRoom].getRoomUp());
                        if(rooms[indexRoom].getRoomUp() != null){
                            rooms[indexRoom].getRoomUp().setRoomDown(newRoom);
                        }
                        
                        newRoom.setRoomDown(rooms[indexRoom].getRoomDown());
                        if(rooms[indexRoom].getRoomDown() != null){
                            rooms[indexRoom].getRoomDown().setRoomUp(newRoom);
                        }
                        
                        newRoom.setRoomRight(rooms[indexRoom].getRoomRight());
                        if(rooms[indexRoom].getRoomRight() != null){
                            rooms[indexRoom].getRoomRight().setRoomLeft(newRoom);
                        }
                        
                        newRoom.setRoomLeft(rooms[indexRoom].getRoomLeft());
                        if(rooms[indexRoom].getRoomLeft() != null){
                            rooms[indexRoom].getRoomLeft().setRoomRight(newRoom);
                        }

                        rooms[indexRoom] = newRoom;
                        
                        //CREATE BONUS ROOM
                        while(true){
                            RewardRoom rewardRoom = new RewardRoom(oneFiles[(int)(Math.random()*(oneFiles.length))]);

                            if(newRoom.getRoomUp() == null && newRoom.isDoorUp()){
                                if(rewardRoom.isDoorDown()){
                                   newRoom.setRoomUp(rewardRoom);
                                   rewardRoom.setRoomDown(newRoom);
                                   rooms[(HALLWAYS - 1)+ rr] = rewardRoom;
                                   break;
                                }
                            }else if(newRoom.getRoomDown() == null && newRoom.isDoorDown()){
                                if(rewardRoom.isDoorUp()){
                                   newRoom.setRoomDown(rewardRoom);
                                   rewardRoom.setRoomUp(newRoom);
                                   rooms[(HALLWAYS - 1)+ rr] = rewardRoom;
                                   break;
                                }
                            }else if(newRoom.getRoomRight() == null && newRoom.isDoorRight()){
                                if(rewardRoom.isDoorLeft()){
                                   newRoom.setRoomRight(rewardRoom);
                                   rewardRoom.setRoomLeft(newRoom);
                                   rooms[(HALLWAYS - 1)+ rr] = rewardRoom;
                                   break;
                                }
                            }else if(newRoom.getRoomLeft() == null && newRoom.isDoorLeft()){
                                if(rewardRoom.isDoorRight()){
                                   newRoom.setRoomLeft(rewardRoom);
                                   rewardRoom.setRoomRight(newRoom);
                                   rooms[(HALLWAYS - 1)+ rr] = rewardRoom;
                                   break;
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        
        actualRoom = rooms[0];
    }
    
    public void draw(Graphics g) {
        actualRoom.draw(g);
    }
    
    public void keyPressed(int code){
        if(code == KeyEvent.VK_UP | code == KeyEvent.VK_DOWN | code == KeyEvent.VK_RIGHT | code == KeyEvent.VK_LEFT){
            int mov = actualRoom.keyPressed(code);
            Player player = actualRoom.getPlayer();
            
            if(mov == UP & actualRoom.isDoorUp()){
                player.setY(Room.HEIGHT);
                actualRoom = actualRoom.getRoomUp();
                actualRoom.setPlayer(player);
                
            }else if(mov == DOWN & actualRoom.isDoorDown()){
                player.setY(0);
                actualRoom = actualRoom.getRoomDown();
                actualRoom.setPlayer(player);
                
            }else if(mov == RIGHT & actualRoom.isDoorRight()){
                player.setX(0);
                actualRoom = actualRoom.getRoomRight();
                actualRoom.setPlayer(player);
                
            }else if(mov == LEFT & actualRoom.isDoorLeft()){
                player.setX(Room.WIDTH);
                actualRoom = actualRoom.getRoomLeft();
                actualRoom.setPlayer(player);
                
            }
        }
    }
    
    public void setPlayer(Player player) {
        actualRoom.setPlayer(player);
    }
}
