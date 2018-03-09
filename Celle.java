class Celle {
    int xPlassering;
    int yPlassering;
    boolean åpen;
    boolean harVært;

    public Celle(int x, int y, boolean aapen) {
        xPlassering = x;
        yPlassering = y;
        åpen = aapen;
        harVært = false;
    }

    public int getX() {
        return xPlassering;
    }

    public int getY() {
        return yPlassering;
    }

    public boolean erÅpen() {
        return åpen;
    }

    public void settVært() {
        harVært = true;
    }

    public boolean harVært() {
        return harVært;
    }
}