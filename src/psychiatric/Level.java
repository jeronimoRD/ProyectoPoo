/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;
import elements.Room;
import elements.Quadrant;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import rooms.InitialRoom;

public class Level {
    private File[] files;
    private Room[][] map; //TEST
    private Room actualRoom;
    
    public static final int ROOMS_LEVEL = 6; //LIMIT

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    
    public Level() {
        
        //FILES LEVELS
        File filesRooms = new File("C:\\Users\\korez\\OneDrive\\Documents\\NetBeansProjects\\pruebasMapa\\src\\filesRooms");
        files = filesRooms.listFiles();
        
        //CREATE MAP
        map = new Room[ROOMS_LEVEL*2][ROOMS_LEVEL*2];
        
        //InitialRoom
        InitialRoom room1 = new InitialRoom(files[0]);
        map[map.length/2][map.length/2] = room1;
        Room actualCreatedRoom = room1;
        
        //Random
        for(int room = 1; room < ROOMS_LEVEL; room++){
            boolean nextRoom = false;
            
            System.out.println(room);
            for(int r = 0; r < map.length; r++){
                for(int c = 0; c < map.length; c++){
                    if(map[r][c] == actualCreatedRoom){
                        System.out.println("cumplio");
                        System.out.println(r + " - " + c);
                        while(true){
                            InitialRoom newRoom = new InitialRoom(files[(int)(Math.random()*(files.length))]);
                            
                            if(map[r][c].isDoorUp()){
                                if(map[r - 1][c] == null){
                                    if(newRoom.isDoorDown()){
                                        map[r - 1][c] = newRoom;

                                        System.out.println("arriba");
                                        actualCreatedRoom = newRoom;
                                        nextRoom = true;
                                        break;
                                    }
                                }
                            }if(map[r][c].isDoorDown()){
                                if(map[r + 1][c] == null){
                                    if(newRoom.isDoorUp()){
                                        map[r + 1][c] = newRoom;

                                        System.out.println("abajo");
                                        actualCreatedRoom = newRoom;
                                        nextRoom = true;
                                        break;
                                    }
                                }
                            }if(map[r][c].isDoorRight()){
                                if(map[r][c + 1] == null){
                                    if(newRoom.isDoorLeft()){
                                        map[r][c + 1] = newRoom;

                                        System.out.println("derecha");
                                        actualCreatedRoom = newRoom;
                                        nextRoom = true;
                                        break;
                                    }
                                }
                            }if(map[r][c].isDoorLeft()){
                                if(map[r][c - 1] == null){
                                    if(newRoom.isDoorRight()){
                                        map[r][c - 1] = newRoom;

                                        System.out.println("izquierda");
                                        actualCreatedRoom = newRoom;
                                        nextRoom = true;
                                        break;
                                    }
                                }
                            }
                        } //ERROR
                        showMap();
                    }
                    if(nextRoom){
                        break;
                    }
                }
                if(nextRoom){
                        break;
                }
            }
        }
        
        //CREATE PLAYER
        Player player = new Player(Room.QUADRANTS_WIDTH*Quadrant.WIDTH/2, Room.QUADRANTS_HEIGHT*Quadrant.HEIGHT/2); //Lógica de psiquiatrico
        
        actualRoom = map[map.length/2][map.length/2];
        actualRoom.setPlayer(player);
    }
    
    public void draw(Graphics g) {
        actualRoom.draw(g);
    }
    
    public void keyPressed(int code){
        if(code == KeyEvent.VK_UP | code == KeyEvent.VK_DOWN | code == KeyEvent.VK_RIGHT | code == KeyEvent.VK_LEFT){
            int mov = actualRoom.keyPressed(code);
            Player player = actualRoom.getPlayer();
            
            boolean exit = false;
            for(int r = 0; r < map.length; r++){
                for(int c = 0; c < map.length; c++){
                    if(map[r][c] == actualRoom){
                        if(mov == UP & actualRoom.isDoorUp()){
                            player.setY(Room.QUADRANTS_HEIGHT*Quadrant.HEIGHT);

                            actualRoom = map[r - 1][c];
                            actualRoom.setPlayer(player);
                            exit = true;
                            break;
                        }
                        if(mov == DOWN & actualRoom.isDoorDown()){
                            player.setY(0);

                            actualRoom = map[r + 1][c];
                            actualRoom.setPlayer(player);
                            exit = true;
                            break;
                        }
                        if(mov == RIGHT & actualRoom.isDoorRight()){
                            player.setX(0);

                            actualRoom = map[r][c + 1];
                            actualRoom.setPlayer(player);
                            exit = true;
                            break;
                        }
                        if(mov == LEFT & actualRoom.isDoorLeft()){
                            player.setX(Room.QUADRANTS_WIDTH*Quadrant.WIDTH);

                            actualRoom = map[r][c - 1];
                            actualRoom.setPlayer(player);
                            exit = true;
                            break;
                        }
                    }
                        
                }
                if(exit){
                    break;
                }
            }
        }
    }
    
    private void showMap(){
        for(Room[] row: map){
            for(Room column: row){
                if(column != null){
                    System.out.print("R");
                }else{
                   System.out.print("-"); 
                }
            }
            System.out.println("");
        }
    }
}
