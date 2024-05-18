/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

/**
 *
 * @author korez
 */
public class HeartCooldown extends Thread{
    private volatile boolean paused;
    private boolean running;
    private boolean recover;
    
    public HeartCooldown(){
        this.recover = false;
        this.running = true;
        this.paused = false;
    }
    
    @Override
    public void run(){
        while(running){
            if(recover){
                try{
                    Thread.sleep(2000); //SPEED OF COOLDOWN
                    recover = false;
                } catch (InterruptedException ex) {
                    System.out.println("ERROR");
                }
            }
            System.out.print(""); //¿QUE?
        }
    }
    
    public void pause(){
        this.paused = !this.paused;
    }
    
    public void halt(){ //Stop
        this.running = false;
    }
    
    //GETTERS AND SETTERS
    public boolean isRunning(){
        return running;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public boolean isRecover() {
        return recover;
    }

    public void setRecover(boolean recover) {
        this.recover = recover;
    }
}
