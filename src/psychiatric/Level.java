 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;
import elements.player.Player;
import exceptions.ImpossibleStructureRoomsException;
import interfaces.Collidable;
import io.RoomReader;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Level {
    private Room[] rooms;
    private Room actualRoom; 
    
    public Level(int totalRooms, int roomsRewards) throws IOException, ImpossibleStructureRoomsException {
        int hallways = totalRooms - roomsRewards; // >= ROOMS_REWARDS + 2
        
        if(hallways - 2 <= 0){
            throw new ImpossibleStructureRoomsException();
        } 
        RoomReader roomReader = new RoomReader("filesRooms\\oneDoor", "filesRooms\\twoDoors", "filesRooms\\threeDoors");
        
        //CREATE ROOMS
        rooms = new Room[totalRooms];
        rooms[0] = roomReader.read(1); //INITIAL ROOM
        
        //CREATE RANDOM HALLWAY
        for(int r = 0; r < hallways; r++){
            Room room;
            if(r == hallways - 2){
                while(true){
                    room = roomReader.read(1); //FINAL ROOM
                    
                    if(rooms[r].getRoomUp() == null && rooms[r].isDoorUp()){
                        if(room.isDoorDown()){
                           rooms[r].setRoomUp(room);
                           room.setRoomDown(rooms[r]);
                           rooms[rooms.length - 1] = room;
                           break;
                        }
                    }else if(rooms[r].getRoomDown() == null && rooms[r].isDoorDown()){
                        if(room.isDoorUp()){
                            rooms[r].setRoomDown(room);
                            room.setRoomUp(rooms[r]);
                            rooms[rooms.length - 1] = room;
                            break;
                        }
                    }else if(rooms[r].getRoomRight() == null && rooms[r].isDoorRight()){
                        if(room.isDoorLeft()){
                            rooms[r].setRoomRight(room);
                            room.setRoomLeft(rooms[r]);
                            rooms[rooms.length - 1] = room;
                            break;
                        }
                    }else if(rooms[r].getRoomLeft() == null && rooms[r].isDoorLeft()){
                        if(room.isDoorRight()){
                            rooms[r].setRoomLeft(room);
                            room.setRoomRight(rooms[r]);
                            rooms[rooms.length - 1] = room;
                            break;
                        }
                    }
                }
                break;
            }
            else{
                while(true){
                    room = roomReader.read(2); // ENEMY ROOM
                    
                    if(rooms[r].isDoorUp() && rooms[r].getRoomUp() == null){
                        if(room.isDoorDown()){
                           rooms[r].setRoomUp(room);
                           room.setRoomDown(rooms[r]);
                           rooms[r+1] = room;
                           break;
                        }
                    }else if(rooms[r].isDoorDown() && rooms[r].getRoomDown() == null){
                        if(room.isDoorUp()){
                            rooms[r].setRoomDown(room);
                            room.setRoomUp(rooms[r]);
                            rooms[r+1] = room;
                            break;
                        }
                    }else if(rooms[r].isDoorRight() && rooms[r].getRoomRight() == null){
                        if(room.isDoorLeft()){
                            rooms[r].setRoomRight(room);
                            room.setRoomLeft(rooms[r]);
                            rooms[r+1] = room;
                            break;
                        }
                    }else if(rooms[r].isDoorLeft() && rooms[r].getRoomLeft() == null){
                        if(room.isDoorRight()){
                            rooms[r].setRoomLeft(room);
                            room.setRoomRight(rooms[r]);
                            rooms[r+1] = room;
                            break;
                        }
                    }
                }
                //!!Test!!
                room.addWalker(1);
                room.addShooter(1);
            }
        }
        
        //CREATE REWARDS ROOMS
        for(int rr = 0; rr < roomsRewards; rr++){
            int doors = 0;
            int indexRoom = (int)(Math.random()*(hallways - 2))+1;
            
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
                Room room;
                Room rewardRoom;
                while(true){
                    room = roomReader.read(3); // NEW ENEMY ROOM
                    
                    if((rooms[indexRoom].isDoorUp() & !room.isDoorUp()) | (rooms[indexRoom].isDoorDown() & !room.isDoorDown())
                    | (rooms[indexRoom].isDoorRight() & !room.isDoorRight()) | (rooms[indexRoom].isDoorLeft() & !room.isDoorLeft())){
                    }else{
                        
                        //ASSOCIATION
                        room.setRoomUp(rooms[indexRoom].getRoomUp());
                        if(rooms[indexRoom].getRoomUp() != null){
                            rooms[indexRoom].getRoomUp().setRoomDown(room);
                        }
                        
                        room.setRoomDown(rooms[indexRoom].getRoomDown());
                        if(rooms[indexRoom].getRoomDown() != null){
                            rooms[indexRoom].getRoomDown().setRoomUp(room);
                        }
                        
                        room.setRoomRight(rooms[indexRoom].getRoomRight());
                        if(rooms[indexRoom].getRoomRight() != null){
                            rooms[indexRoom].getRoomRight().setRoomLeft(room);
                        }
                        
                        room.setRoomLeft(rooms[indexRoom].getRoomLeft());
                        if(rooms[indexRoom].getRoomLeft() != null){
                            rooms[indexRoom].getRoomLeft().setRoomRight(room);
                        }

                        rooms[indexRoom] = room;
                        
                        //CREATE REWARD ROOM
                        while(true){
                            rewardRoom = roomReader.read(1); //REWARD ROOM

                            if(room.getRoomUp() == null && room.isDoorUp()){
                                if(rewardRoom.isDoorDown()){
                                   room.setRoomUp(rewardRoom);
                                   rewardRoom.setRoomDown(room);
                                   rooms[(hallways - 1)+ rr] = rewardRoom;
                                   break;
                                }
                            }else if(room.getRoomDown() == null && room.isDoorDown()){
                                if(rewardRoom.isDoorUp()){
                                   room.setRoomDown(rewardRoom);
                                   rewardRoom.setRoomUp(room);
                                   rooms[(hallways - 1)+ rr] = rewardRoom;
                                   break;
                                }
                            }else if(room.getRoomRight() == null && room.isDoorRight()){
                                if(rewardRoom.isDoorLeft()){
                                   room.setRoomRight(rewardRoom);
                                   rewardRoom.setRoomLeft(room);
                                   rooms[(hallways - 1)+ rr] = rewardRoom;
                                   break;
                                }
                            }else if(room.getRoomLeft() == null && room.isDoorLeft()){
                                if(rewardRoom.isDoorRight()){
                                   room.setRoomLeft(rewardRoom);
                                   rewardRoom.setRoomRight(room);
                                   rooms[(hallways - 1)+ rr] = rewardRoom;
                                   break;
                                }
                            }
                        }
                        break;
                    }
                }
                //!!Test!!
                room.addWalker(1);
                room.addShooter(1);
                rewardRoom.addReward(1);
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
            
            if(mov == Collidable.UP & actualRoom.isDoorUp()){
                player.setY(Room.HEIGHT);
                actualRoom = actualRoom.getRoomUp();
                actualRoom.setPlayer(player);
                
            }else if(mov == Collidable.DOWN & actualRoom.isDoorDown()){
                player.setY(0);
                actualRoom = actualRoom.getRoomDown();
                actualRoom.setPlayer(player);
                
            }else if(mov == Collidable.RIGHT & actualRoom.isDoorRight()){
                player.setX(0);
                actualRoom = actualRoom.getRoomRight();
                actualRoom.setPlayer(player);
                
            }else if(mov == Collidable.LEFT & actualRoom.isDoorLeft()){
                player.setX(Room.WIDTH);
                actualRoom = actualRoom.getRoomLeft();
                actualRoom.setPlayer(player);
                
            }
        }
    }
    
    public void mousePressed(int code){
        if(code == MouseEvent.BUTTON1){
            actualRoom.mousePressed(code);
        }
    }
    
    //GETTERS AND SETTERS
    public void setPlayer(Player player) {
        actualRoom.setPlayer(player);
    }
}
