public class Farmer {

    //========== VARIABLE DECLARATION ==========
    public int xpos;        // X position on screen
    public int ypos;        // Y position on screen
    public int dx;          // Speed in X direction (horizontal)
    public int dy;          // Speed in Y direction (vertical)
    public int width;       // Width of farmer image
    public int height;      // Height of farmer image
    public boolean isHappy; // Whether farmer is happy


    //========== CONSTRUCTOR ==========
    // Builds a Farmer object with starting position
    public Farmer(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 2;           // Farmer walks slowly to the right
        dy = 0;           // No vertical movement
        width = 100;       // Farmer is 80 pixels wide
        height = 150;     // Farmer is 100 pixels tall
        isHappy = true;   // Farmer starts happy
    }


    //========== MOVE METHOD (WITH WRAPPING) ==========
    // Moves the farmer and wraps him around the screen edges
    public void move() {
        // Update position based on speed
        xpos = xpos + dx;
        ypos = ypos + dy;

        // WRAPPING LOGIC - if farmer goes off right edge, wrap to left
        if (xpos > 200) {  // Only moves in a small area on left side
            xpos = -width;  // Start from left edge (off screen)
        }

        // If farmer goes off left edge, wrap to right
        if (xpos < -width) {
            xpos = 200;
        }
    }

}