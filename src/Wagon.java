public class Wagon {

    //========== VARIABLE DECLARATION ==========
    public int xpos;           // X position on screen
    public int ypos;           // Y position on screen
    public int dx;             // Speed in X direction (controlled by keyboard)
    public int dy;             // Speed in Y direction (stays on ground)
    public int width;          // Width of wagon image
    public int height;         // Height of wagon image
    public int fruitsCaught;   // Number of fruits this wagon has caught


    //========== CONSTRUCTOR ==========
    // Builds a Wagon object with starting position
    public Wagon(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 0;              // Starts stationary (player controls it)
        dy = 0;              // No vertical movement
        width = 100;         // Wagon is 100 pixels wide
        height = 60;         // Wagon is 60 pixels tall
        fruitsCaught = 0;
    }


    //========== MOVE METHOD (WITH BOUNCING) ==========
    // Moves the wagon and bounces it off screen edges
    public void move() {
        // Update position based on speed
        xpos = xpos + dx;
        ypos = ypos + dy;

        // BOUNCING LOGIC - if wagon hits right edge, bounce back left
        if (xpos + width > 1000) {  // 1000 is screen width
            xpos = 1000 - width;     // Stop at edge
            dx = -dx;                 // Reverse direction (bounce)
        }

        // If wagon hits left edge, bounce back right
        if (xpos < 0) {
            xpos = 0;      // Stop at edge
            dx = -dx;      // Reverse direction (bounce)
        }
    }

}