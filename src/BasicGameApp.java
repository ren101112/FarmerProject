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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener,MouseListener {

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
    public Image farmerPic;
    public Image wagonPic;
    public Image applePic;
    public Image orangePic;
    public Image bananaPic;
    public Image backgroundPic;
    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    private Farmer farmer;
    private Wagon wagon;
    private Fruit[] fallingFruits;

    private int score;
    private int gameTime;  // Game time in seconds
    private int frameCount;  // Count frames to track seconds
    private boolean speedBoostActive;  // Whether time is sped up from mouse click
    private int speedBoostTimer;  // How long the speed boost lasts


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
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
        farmerPic = Toolkit.getDefaultToolkit().getImage("farmer image.jpeg");
        wagonPic = Toolkit.getDefaultToolkit().getImage("wgon.png");
        applePic = Toolkit.getDefaultToolkit().getImage("apple.jpg");
        orangePic = Toolkit.getDefaultToolkit().getImage("orange.png");
        bananaPic = Toolkit.getDefaultToolkit().getImage("banana.png");
        backgroundPic = Toolkit.getDefaultToolkit().getImage("FARMER BACKGROUND.jpg");


        // Create the farmer (decorative character on the left side)
        farmer = new Farmer(50, 90);

        // Create the wagon
        wagon = new Wagon(90, 500);


        // Create an array of 6 falling fruits with different types
        fallingFruits = new Fruit[6];
        fallingFruits[0] = new Fruit(200, -50, "apple");
        fallingFruits[1] = new Fruit(400, -150, "orange");
        fallingFruits[2] = new Fruit(600, -250, "banana");
        fallingFruits[3] = new Fruit(300, -350, "apple");
        fallingFruits[4] = new Fruit(700, -450, "orange");
        fallingFruits[5] = new Fruit(500, -550, "banana");

        score = 0;
        gameTime = 0;
        frameCount = 0;
        speedBoostActive = false;
        speedBoostTimer = 0;




        //========== ADD LISTENERS ==========
        // Set up keyboard and mouse controls
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);


    }


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


    public void moveThings() {
        //calls the move( ) code in the objects

        // Move the farmer (he walks back and forth with wrapping)
        farmer.move();

        // Move the wagon (controlled by player, bounces at edges)
        wagon.move();

        // Move all the falling fruits in the array
        for (int i = 0; i < fallingFruits.length; i++) {
            Rectangle wagonBox = new Rectangle(
                    wagon.xpos,
                    wagon.ypos,
                    wagon.width,
                    wagon.height
            );

            Rectangle fruitBox = new Rectangle(
                    fallingFruits[i].xpos,
                    fallingFruits[i].ypos,
                    fallingFruits[i].width,
                    fallingFruits[i].height
            );
            if (speedBoostActive) {
                fallingFruits[i].ypos += fallingFruits[i].dy * 2;
            } else {
                fallingFruits[i].move();
            }


            // If fruit falls off bottom of screen, reset it to top
            if (fallingFruits[i].ypos > HEIGHT) {
                fallingFruits[i].resetToTop();
            }

            if (fruitBox.intersects(wagonBox)) {
                score++;

                // reset fruit
                fallingFruits[i].resetToTop();
            }

        }
        // Handle speed boost timer
        if (speedBoostActive==true) {
            speedBoostTimer--;
            if (speedBoostTimer <= 0) {
                speedBoostActive = false;
            }
        }



        frameCount++;

        if (frameCount % 50 == 0) { // ~1 second
            gameTime++;
        }




    }


    // Checks if any fruit hit the wagon


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
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

        // Clear screen with sky blue background
        g.setColor(new Color(36, 223, 255));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw ground (green grass)
        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, HEIGHT - 100, WIDTH, 100);

        //========== DRAW GAME OBJECTS ==========
        // Draw the farmer
        g.drawImage(farmerPic, farmer.xpos, farmer.ypos, farmer.width, farmer.height, null);

        // Draw the wagon (what catches fruit)
        g.drawImage(wagonPic, wagon.xpos, wagon.ypos, wagon.width, wagon.height, null);

        // Draw all falling fruits from the array
        for (int i = 0; i < fallingFruits.length; i++) {
            Fruit fruit = fallingFruits[i];

            // Choose the right image based on fruit type
            Image currentFruitImage = applePic;
            if (fruit.fruitType.equals("orange")) {
                currentFruitImage = orangePic;
            } else if (fruit.fruitType.equals("banana")) {
                currentFruitImage = bananaPic;
            }

            // Draw the fruit
            g.drawImage(currentFruitImage, fruit.xpos, fruit.ypos,
                    fruit.width, fruit.height, null);
        }

        //========== DRAW GAME UI (Score and Time) ==========
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 30);

        // Draw time in top right corner
        g.drawString("Time: " + gameTime + "s", WIDTH - 150, 30);

        // Draw speed boost indicator if active
        if (speedBoostActive==true) {
            g.setColor(Color.RED);
            g.drawString("SPEED BOOST!", WIDTH / 2 - 80, 30);
        }

        // Draw instructions
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Arrow Keys: Move Wagon | Mouse Click: Speed Boost", 20, HEIGHT - 10);
        g.dispose();
        bufferStrategy.show();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        speedBoostActive = true;
        speedBoostTimer = 100;
    }

    // Called when mouse button is released
    @Override
    public void mouseReleased(MouseEvent e) {
        // Not used in this game
    }

    // Called when mouse enters the canvas area
    @Override
    public void mouseEntered(MouseEvent e) {
        // Not used in this game
    }

    // Called when mouse exits the canvas area
    @Override
    public void mouseExited(MouseEvent e) {
        // Not used in this game
    }

    @Override
    public void keyTyped(KeyEvent e) {



    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==37){ // left
            wagon.dx = -4;
        }
        if (e.getKeyCode()==39){ // right
            wagon.dx = 4;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        wagon.dx = 0;

    }
}