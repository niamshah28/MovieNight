//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
    //the following are the images for the movies
	public Image schoolPic;
    public Image BackgroundPic;
    public Image GoodPic;
    public Image TitansPic;
    public Image DieHardPic;
    public Image GladPic;
    //the following are the images for the background image to play the movies
    public Image SchoolBackgroundPic;
    public Image DieHardBackgroundPic;
    public Image GladBackgroundPic;
    public Image GoodBackgroundPic;
    public Image TitansBackgroundPic;
    public int BattleCounter;
    public int coinflip;
    public Image AltBackgroundPic;
    public Image popcornPic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private SchoolTies school;
    private GoodWill Good;
    private Titans Titans;
    private DieHard DieHard;
    private Gladiator Glad;
    private Popcorn Popcorn;



   // Main method definition
   // This is the code that runs first and automatically public
    static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		schoolPic = Toolkit.getDefaultToolkit().getImage("SchoolTies.jpg");
        GoodPic = Toolkit.getDefaultToolkit().getImage("GoodWill.jpg");
        BackgroundPic = Toolkit.getDefaultToolkit().getImage("TV.jpg");//load the picture
        TitansPic = Toolkit.getDefaultToolkit().getImage("Titans.jpg");
        DieHardPic = Toolkit.getDefaultToolkit().getImage("DieHard.jpg");
        GladPic = Toolkit.getDefaultToolkit().getImage("Gladiator.jpg");
        AltBackgroundPic = Toolkit.getDefaultToolkit().getImage("TV.jpg");
        popcornPic = Toolkit.getDefaultToolkit().getImage("popcorn.jpg");
        SchoolBackgroundPic = Toolkit.getDefaultToolkit().getImage("cowards.gif");
        DieHardBackgroundPic = Toolkit.getDefaultToolkit().getImage("welcome.gif");
        GladBackgroundPic = Toolkit.getDefaultToolkit().getImage("Entertained.gif");
        GoodBackgroundPic = Toolkit.getDefaultToolkit().getImage("MATH.gif");
        TitansBackgroundPic = Toolkit.getDefaultToolkit().getImage("dance.gif");
        // The following decides where the movies will "spawn" in
        Popcorn = new Popcorn((int) (Math.random()*644 + 700),0);
		school = new SchoolTies((int) (Math.random()*455 + 600),100);
        Good = new GoodWill((int) (Math.random()*644 + 200),200);
        Titans = new Titans((int) (Math.random()*670 + 400),300);
        DieHard = new DieHard((int) (Math.random()*700 + 554),400);
        Glad = new Gladiator((int) (Math.random()*100 + 6),500);



	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		school.move();
        Good.move();
        Titans.move();
        DieHard.move();
        Glad.move();
        Popcorn.move();
        crashing();

	}
    public void crashing(){
        // check to see if my movies crash into each other
        if(school.hitbox.intersects(Good.hitbox)){
            school.dx = -school.dx;
            Good.dx = -Good.dx;
            school.dy = -school.dy;
            Good.dy = -Good.dy;
            coinflip = (int) (Math.random() * 2) + 1; //decides which movie dies at random
            if(coinflip == 1) {
                school.stop();
                AltBackgroundPic = GoodBackgroundPic;//changes background to the corresponding background
            }
            if(coinflip == 2) {
                Good.stop();
                AltBackgroundPic = SchoolBackgroundPic;
            }
            BattleCounter = BattleCounter + 1;
        }
        if (school.hitbox.intersects(Titans.hitbox)){
            school.dx = -school.dx;
            Titans.dx = -Titans.dx;
            school.dy = -school.dy;
            Titans.dy = -Titans.dy;
            coinflip = (int) (Math.random() * 2) + 1;
            if(coinflip == 1) {
                school.stop();
                AltBackgroundPic = TitansBackgroundPic;
            }
            if(coinflip == 2) {
                Titans.stop();
                AltBackgroundPic = SchoolBackgroundPic;
            }
            BattleCounter = BattleCounter + 1;
        }
        if (school.hitbox.intersects(Glad.hitbox)) {
            school.dx = -school.dx;
            Glad.dx = -Glad.dx;
            school.dy = -school.dy;
            Glad.dy = -Glad.dy;
            coinflip = (int) (Math.random() * 2) + 1;
            if(coinflip == 1) {
                school.stop();
                AltBackgroundPic = GladBackgroundPic;
            }
            if(coinflip == 2) {
                Glad.stop();
                AltBackgroundPic = SchoolBackgroundPic;
            }
            BattleCounter = BattleCounter + 1;
        }
        if (school.hitbox.intersects(DieHard.hitbox)) {
            school.dx = -school.dx;
            DieHard.dx = -DieHard.dx;
            school.dy = -school.dy;
            DieHard.dy = -DieHard.dy;
            coinflip = (int) (Math.random() * 2) + 1;
            if(coinflip == 1) {
                school.stop();
                AltBackgroundPic = DieHardBackgroundPic;
            }
            if(coinflip == 2) {
                DieHard.stop();
                AltBackgroundPic = SchoolBackgroundPic;
            }
            BattleCounter = BattleCounter + 1;
        }
        if (Good.hitbox.intersects(Titans.hitbox)) {
            Good.dx = -Good.dx;
            Titans.dx = -Titans.dx;
            Good.dy = -Good.dy;
            Titans.dy = -Titans.dy;
            coinflip = (int) (Math.random() * 2) + 1;
            if(coinflip == 1) {
                Good.stop();
                AltBackgroundPic = TitansBackgroundPic;
            }
            if(coinflip == 2) {
                Titans.stop();
                AltBackgroundPic = GoodBackgroundPic;
            }
            BattleCounter = BattleCounter + 1;
        }
        if (Good.hitbox.intersects(Glad.hitbox)) {
            Good.dx = -Good.dx;
            Glad.dx = -Glad.dx;
            Good.dy = -Good.dy;
            Glad.dy = -Glad.dy;
            coinflip = (int) (Math.random() * 2) + 1;
            if(coinflip == 1) {
                Good.stop();
               AltBackgroundPic = GladBackgroundPic;
            }
            if(coinflip == 2) {
                Glad.stop();
                AltBackgroundPic = GoodBackgroundPic;
            }
            BattleCounter = BattleCounter + 1;
        }
        if (Good.hitbox.intersects(DieHard.hitbox)) {
            Good.dx = -Good.dx;
            DieHard.dx = -DieHard.dx;
            Good.dy = -Good.dy;
            DieHard.dy = -DieHard.dy;
            coinflip = (int) (Math.random() * 2) + 1;
            if(coinflip == 1) {
                Good.stop();
                AltBackgroundPic = DieHardBackgroundPic;
            }
            if(coinflip == 2) {
                DieHard.stop();
                AltBackgroundPic = GoodBackgroundPic;
            }
            BattleCounter = BattleCounter + 1;
        }
        if (Titans.hitbox.intersects(DieHard.hitbox)) {
            Titans.dx = -Titans.dx;
            DieHard.dx = -DieHard.dx;
            Titans.dy = -Titans.dy;
            DieHard.dy = -DieHard.dy;
            coinflip = (int) (Math.random() * 2) + 1;
            if(coinflip == 1) {
                Titans.stop();
                AltBackgroundPic = DieHardBackgroundPic;
            }
            if(coinflip == 2) {
                DieHard.stop();
                AltBackgroundPic = TitansBackgroundPic;
            }
            BattleCounter = BattleCounter + 1;
        }
        if (Titans.hitbox.intersects(Glad.hitbox)) {
            Titans.dx = -Titans.dx;
            Glad.dx = -Glad.dx;
            Titans.dy = -Titans.dy;
            Glad.dy = -Glad.dy;
            coinflip = (int) (Math.random() * 2) + 1;
            if(coinflip == 1) {
                Titans.stop();
                AltBackgroundPic = GladBackgroundPic;
            }
            if(coinflip == 2) {
                Glad.stop();
                AltBackgroundPic = TitansBackgroundPic;
            }
            BattleCounter = BattleCounter + 1;
        }
        if (Glad.hitbox.intersects(DieHard.hitbox)) {
            Glad.dx = -Glad.dx;
            DieHard.dx = -DieHard.dx;
            Glad.dy = -Glad.dy;
            DieHard.dy = -DieHard.dy;
            coinflip = (int) (Math.random() * 2) + 1;
            if(coinflip == 1) {
                Glad.stop();
                AltBackgroundPic = DieHardBackgroundPic;
            }
            if(coinflip == 2) {
                DieHard.stop();
                AltBackgroundPic = GladBackgroundPic;
            }
            BattleCounter = BattleCounter + 1;
        }
        if (school.hitbox.intersects(Popcorn.hitbox)&& Popcorn.isCrashing == false){ // makes the popcorn larger when a movie hit it
            Popcorn.isCrashing = true;
            school.dx = -school.dx;
            Popcorn.dx = -Popcorn.dx;
            school.dy = -school.dy;
            Popcorn.dy = -Popcorn.dy;
            Popcorn.height+=10;
            Popcorn.width+=10;
            }
        if (Popcorn.hitbox.intersects(Titans.hitbox)&& Popcorn.isCrashing == false) {
            Popcorn.isCrashing = true;
            Popcorn.dx = -Popcorn.dx;
            Titans.dx = -Titans.dx;
            Popcorn.dy = -Popcorn.dy;
            Titans.dy = -Titans.dy;
            Popcorn.height+=10;
            Popcorn.width+=10;
        }
        if (Good.hitbox.intersects(Popcorn.hitbox)&& Popcorn.isCrashing == false){
            Popcorn.isCrashing = true;
            Good.dx = -Good.dx;
            Popcorn.dx = -Popcorn.dx;
            Good.dy = -Good.dy;
            Popcorn.dy = -Popcorn.dy;
            Popcorn.height+=10;
            Popcorn.width+=10;
        }
        if (Glad.hitbox.intersects(Popcorn.hitbox)&& Popcorn.isCrashing == false){
            Popcorn.isCrashing = true;
            Glad.dx = -Glad.dx;
            Popcorn.dx = -Popcorn.dx;
            school.dy = -school.dy;
            Popcorn.dy = -Popcorn.dy;
            Popcorn.height+=10;
            Popcorn.width+=10;
        }
        if (DieHard.hitbox.intersects(Popcorn.hitbox)&& Popcorn.isCrashing == false){
            Popcorn.isCrashing = true;
            DieHard.dx = -DieHard.dx;
            Popcorn.dx = -Popcorn.dx;
            DieHard.dy = -DieHard.dy;
            Popcorn.dy = -Popcorn.dy;
            Popcorn.height+=10;
            Popcorn.width+=10;
        }
        if(!Popcorn.hitbox.intersects(school.hitbox) && !Popcorn.hitbox.intersects(Titans.hitbox) && !Popcorn.hitbox.intersects(Good.hitbox) && !Popcorn.hitbox.intersects(Glad.hitbox) && !Popcorn.hitbox.intersects(DieHard.hitbox)){
            Popcorn.isCrashing = false;
        }
    }
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the Background GIF
       g.drawImage(BackgroundPic, 0, 0, WIDTH, HEIGHT, null);

       //g.drawImage(cowardsPic, 0, 0, WIDTH, HEIGHT, null);
       //g.drawImage(welcomePic, 0, 0, WIDTH, HEIGHT, null);
       //g.drawImage(EntertainedPic, 0, 0, WIDTH, HEIGHT, null);
       //g.drawImage(MATHPic, 0, 0, WIDTH, HEIGHT, null);
       //g.drawImage(dancePic, 0, 0, WIDTH, HEIGHT, null);
       if(BattleCounter >= 4){ // This makes the background change when 4 "battles" have happened
            BackgroundPic = AltBackgroundPic;
        }

        if(school.isAlive == true){ //All these if statements define how the image is drawn
		g.drawImage(schoolPic, school.xpos, school.ypos, school.width, school.height, null);
        }
        if(Good.isAlive == true) {
            g.drawImage(GoodPic, Good.xpos, Good.ypos, Good.width, Good.height, null);
        }
        if(Titans.isAlive == true) {
            g.drawImage(TitansPic, Titans.xpos, Titans.ypos, Titans.width, Titans.height, null);
        }
        if(DieHard.isAlive == true) {
            g.drawImage(DieHardPic, DieHard.xpos, DieHard.ypos, DieHard.width, DieHard.height, null);
        }
        if(Glad.isAlive == true) {
            g.drawImage(GladPic, Glad.xpos, Glad.ypos, Glad.width, Glad.height, null);
        }
        if(Popcorn.isAlive == true) {
            g.drawImage(popcornPic, Popcorn.xpos, Popcorn.ypos, Popcorn.width, Popcorn.height, null);
        }
       



        g.dispose();

		bufferStrategy.show();
	}
}