public class Farmer {

    //========== VARIABLE DECLARATION ==========
    public int xpos;        // X position on screen
    public int ypos;        // Y position on screen
    public int dx;          // Speed in X direction (horizontal)
    public int dy;          // Speed in Y direction (vertical)
    public int width;       // Width of farmer image
    public int height;      // Height of farmer image



    //========== CONSTRUCTOR ==========
    // Builds a Farmer object with starting position
    public Farmer(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 2;           // Farmer walks slowly to the right
        dy = 3;           // No vertical movement
        width = 100;       // Farmer is 80 pixels wide
        height = 150;     // Farmer is 100 pixels tall

    }


    //========== MOVE METHOD (WITH WRAPPING) ==========
    // Moves the farmer and wraps him around the screen edges
    public void move() {
        // Update position based on speed

        xpos += dx;
        ypos += dy;

        // Bounce off left/right walls
        if (xpos <= 0 || xpos + width >=1000) {
            dx = -dx;
        }

        // Bounce off top/bottom walls
        if (ypos <= 0 || ypos + height >= 700) {
            dy = -dy;
        }
    }

}