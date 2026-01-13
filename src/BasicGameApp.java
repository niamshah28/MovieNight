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
	public Image schoolPic;
    public Image BackgroundPic;
    public Image GoodPic;
    public Image TitansPic;
    public Image DieHardPic;
    public Image GladPic;
    public Image cowardsPic;
    public Image welcomePic;
    public Image EntertainedPic;
    public Image MATHPic;
    public Image dancePic;


   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private SchoolTies school;
    private GoodWill Good;
    private Titans Titans;
    private DieHard DieHard;
    private Gladiator Glad;



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
        //BackgroundPic = Toolkit.getDefaultToolkit().getImage("TV.jpg");//load the picture
        TitansPic = Toolkit.getDefaultToolkit().getImage("Titans.jpg");
        DieHardPic = Toolkit.getDefaultToolkit().getImage("DieHard.jpg");
        GladPic = Toolkit.getDefaultToolkit().getImage("Gladiator.jpg");
        //cowardsPic = Toolkit.getDefaultToolkit().getImage("cowards.gif");
        welcomePic = Toolkit.getDefaultToolkit().getImage("welcome.gif");
        //EntertainedPic = Toolkit.getDefaultToolkit().getImage("Entertained.gif");
        //MATHPic = Toolkit.getDefaultToolkit().getImage("MATH.gif");
        //dancePic = Toolkit.getDefaultToolkit().getImage("dance.gif");
		school = new SchoolTies(10,100);
        Good = new GoodWill(10,200);
        Titans = new Titans(10,300);
        DieHard = new DieHard(10,400);
        Glad = new Gladiator(10,500);

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
        crashing();

	}
    public void crashing(){
        // check to see if my astros crash into each other
        if(school.hitbox.intersects(Good.hitbox)){
            school.dx = -school.dx;
            Good.dx = -Good.dx;
            school.dy = -school.dy;
            Good.dy = -Good.dy;
            Good.isAlive = false;
        }
        if (school.hitbox.intersects(Titans.hitbox)){
            school.dx = -school.dx;
            Titans.dx = -Titans.dx;
            school.dy = -school.dy;
            Titans.dy = -Titans.dy;
            Good.isAlive = false;
        }
        if (school.hitbox.intersects(Glad.hitbox)) {
            school.dx = -school.dx;
            Glad.dx = -Glad.dx;
            school.dy = -school.dy;
            Glad.dy = -Glad.dy;
            Good.isAlive = false;
        }
        if (school.hitbox.intersects(DieHard.hitbox)) {
            school.dx = -school.dx;
            DieHard.dx = -DieHard.dx;
            school.dy = -school.dy;
            DieHard.dy = -DieHard.dy;
            Good.isAlive = false;
        }
        if (Good.hitbox.intersects(Titans.hitbox)) {
            Good.dx = -Good.dx;
            Titans.dx = -Titans.dx;
            Good.dy = -Good.dy;
            Titans.dy = -Titans.dy;
            Good.isAlive = false;
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

      //draw the image of the astronaut
       // g.drawImage(BackgroundPic, 0, 0, WIDTH, HEIGHT, null);
        //g.drawImage(cowardsPic, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(welcomePic, 0, 0, WIDTH, HEIGHT, null);
        //g.drawImage(EntertainedPic, 0, 0, WIDTH, HEIGHT, null);
        //g.drawImage(MATHPic, 0, 0, WIDTH, HEIGHT, null);
        //g.drawImage(dancePic, 0, 0, WIDTH, HEIGHT, null);
		g.drawImage(schoolPic, school.xpos, school.ypos, school.width, school.height, null);
        g.drawImage(GoodPic, Good.xpos, Good.ypos, Good.width, Good.height, null);
        g.drawImage(TitansPic, Titans.xpos, Titans.ypos, Titans.width, Titans.height, null);
        g.drawImage(DieHardPic, DieHard.xpos, DieHard.ypos, DieHard.width, DieHard.height, null);
        g.drawImage(GladPic, Glad.xpos, Glad.ypos, Glad.width, Glad.height, null);

		g.dispose();

		bufferStrategy.show();
	}
}