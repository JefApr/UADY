public class TimeCalculator {
    int minutes = 0;
    int seconds = 0;

    // some kinda method that determines the time a player has for each level based
    // on the level size and dimonds.
    // this method should be changed in order to provide a more relistic time system.
    public void calcTimeforMaze(int totalDimonds, int xSize, int ySize) {

        if (xSize / ySize < 1) {
            minutes += (ySize / xSize) * 1 + 1;
        } else
            minutes += (ySize / xSize) * 1 + 1;

        if (totalDimonds > 6 && totalDimonds * .10 + seconds <= 60)
            minutes += (ySize / xSize) * 1 + 1;
        else {
            minutes += 1;
        }
        
        if (minutes == 0)
            minutes = 2;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}// end class
