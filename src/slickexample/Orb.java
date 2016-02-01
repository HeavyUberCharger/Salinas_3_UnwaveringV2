/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slickexample;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author HUC
 */
public class Orb {
    private int x,y,width,height;
    private int dmg,hitboxX,hitboxY;
    private boolean isvisible;
    Image orbpic;
    Shape hitbox;
    
    public Orb (int a,int b) throws SlickException{
        this.x = a;
        this.y = b;
        this.isvisible = false;
        this.orbpic = new Image("res/Orbs/Ninja_15.png");
        this.hitbox = new Rectangle (a,b,32,32);
    }
    //getters and setters are a common concept in java.
    //A design guideline in java, and object oriented programming in general, is to encapulate/isolate values as much as possible.
    //getters- methods use to query the value of instance variables
    //this.getLocation();
    //setters-methods that set values for instance variables
    
    
    
    
    
            
}
