/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;
import elements.Room;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import rooms.InitialRoom;

public class Level {
    private File[] files;
    private ArrayList<Room> rooms;
    private Room actualRoom;
    
    public static final int ROOMS_LEVEL = 10; //Array normal

    
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    
    public Level() {
        rooms = new ArrayList<>();
        
        File filesRooms = new File("C:\\Users\\korez\\OneDrive\\Documents\\NetBeansProjects\\pruebasMapa\\src\\filesRooms");
        files = filesRooms.listFiles();

        InitialRoom room1 = new InitialRoom(files[0]);
        InitialRoom room2 = new InitialRoom(files[1]);
        InitialRoom room3 = new InitialRoom(files[2]);
        InitialRoom room4 = new InitialRoom(files[3]);
        
        room1.setRoomRight(room2);
        room1.setRoomDown(room3);
        
        room2.setRoomLeft(room1);
        room2.setRoomDown(room4);
        
        room3.setRoomUp(room1);
        room3.setRoomRight(room4);
        
        room4.setRoomUp(room2);
        room4.setRoomLeft(room3);
        
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        
        Player player = new Player(500, 500); //Lógica de psiquiatrico
        
        actualRoom = rooms.get(0);
        actualRoom.setPlayer(player);
    }
    
    public void draw(Graphics g) {
        actualRoom.draw(g);
    }
    
    public void keyPressed(int code){
        if(code == KeyEvent.VK_UP | code == KeyEvent.VK_DOWN | code == KeyEvent.VK_RIGHT | code == KeyEvent.VK_LEFT){
            int mov = actualRoom.keyPressed(code);
            Player player = actualRoom.getPlayer();
            
            if(mov == UP & actualRoom.getRoomUp() != null){
                player.setX(500);
                player.setY(1000);
                
                actualRoom = actualRoom.getRoomUp();
                actualRoom.setPlayer(player);
            }
            if(mov == DOWN & actualRoom.getRoomDown() != null){
                player.setX(500);
                player.setY(0);
                
                actualRoom = actualRoom.getRoomDown();
                actualRoom.setPlayer(player);
            }
            if(mov == RIGHT & actualRoom.getRoomRight() != null){
                player.setX(0);
                player.setY(500);
                
                actualRoom = actualRoom.getRoomRight();
                actualRoom.setPlayer(player);
            }
            if(mov == LEFT & actualRoom.getRoomLeft() != null){
                player.setX(1000);
                player.setY(500);
                
                actualRoom = actualRoom.getRoomLeft();
                actualRoom.setPlayer(player);
            }
        }
    }
}
