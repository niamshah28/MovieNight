import java.awt.*;

public class Popcorn {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;//a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;
    public boolean isCrashing;
    public int health;


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Popcorn(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =2;
        dy =3;
        width = 60;
        height = 80;
        isAlive = true;
    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if(xpos >= 1000-width){ //Right wall
            xpos = 0;
        }

        if(xpos < 0){ // Left wall
            xpos = 1000 - width;
        }

        if(ypos < 0){ // Top wall
            ypos = 700-height;
        }
        if (ypos > 700-height ){ // Bottom wall
            ypos = 0;
        }


        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, width, height);

    }

}
