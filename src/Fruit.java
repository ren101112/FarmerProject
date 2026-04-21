
public class Fruit {

    //========== VARIABLE DECLARATION ==========
    public int xpos;              // X position on screen
    public int ypos;              // Y position on screen
    public int dx;                // Speed in X direction
    public int dy;                // Speed in Y direction
    public int width;             // Width of fruit image
    public int height;            // Height of fruit image
    public String fruitType;      // Type of fruit
    public int points;            // Points awarded for catching this fruit
    public boolean isFalling;     // Whether fruit is currently falling


    //========== CONSTRUCTOR ==========
    // Builds a Fruit object with starting position and type
    public Fruit(int pXpos, int pYpos, String pFruitType) {
        xpos = pXpos;
        ypos = pYpos;
        fruitType = pFruitType;
        dx = 0;              // No horizontal movement (falls straight down)
        isFalling = true;    // Fruit starts falling

        // Set size and properties based on fruit type
        if (fruitType==("apple")) {
            width = 40;
            height = 40;
            dy = 3;          // Apples fall at medium speed
            points = 10;     // Worth 10 points

        } else if (fruitType==("orange")) {
            width = 45;
            height = 45;
            dy = 4;          // Oranges fall slower
            points = 15;     // Worth more points (harder to catch)

        } else if (fruitType==("banana")) {
            width = 50;
            height = 35;
            dy = 2;          // Bananas fall faster
            points = 5;      // Worth fewer points (easier to catch)
        }
    }


    //========== MOVE METHOD ==========
    // Moves the fruit downward (falling)
    public void move() {
        // Update position based on speed
        xpos = xpos + dx;
        ypos = ypos + dy;
    }


    //========== RESET TO TOP METHOD ==========
    // Resets fruit to top of screen at random X position
    // Called when fruit is caught or falls off bottom
    public void resetToTop() {
        // Random X position across the screen
        xpos = (int)(Math.random() * 900) + 50;  // Between 50 and 950

        // Start above the screen
        ypos = -60;
        //remember to make new class

        // Fruit is falling again
        isFalling = true;
    }

}
