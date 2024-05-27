/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

/**
 *
 * @author korez
 */
public class CooldownThread extends Thread{
    protected boolean running;
    protected boolean recover;
    protected int time;
    
    public CooldownThread(int time){
        this.running = true;
        this.time = time;
        this.recover = false;
    }
    
    @Override
    public void run(){
        while(running){
            System.out.print("");
            if(recover){
                try{
                    Thread.sleep(time); //SPEED OF COOLDOWN
                } catch (InterruptedException ex) {
                    System.out.println("ERROR");
                }
                recover = false;
            }
        }
    }
    
    public void stopRun(){
        this.running = false;
    }
    public void startRun(){
        this.running = true;
    }
    
    public void startCoolDown() {
        this.recover = true;
    }
    
    public boolean isRecover() {
        return recover;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
