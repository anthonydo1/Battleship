package battleship;

public class Cell {
    private boolean missed;
    private boolean hasShip;
    private boolean destroyed;
    private Ship ship;
    
    public Cell() {
        missed = false;
        hasShip = false;
        destroyed = false;
    }
    
    public void addShip(Ship ship) {
        this.ship = ship;
    }
    
    public void setHasShip(boolean bool) {
        hasShip = bool;
    }
    
    public void setDestroyed(boolean bool) {
        destroyed = bool;
    }
    
    public void setShip(Ship ship) {
        this.ship = ship;
    }
    
    public void setMissed(boolean bool) {
        this.missed = bool;
    }
    
    public boolean getMissed() {
        return missed;
    }
    
    public boolean hasShip() {
        return hasShip;
    }
    
    public boolean isDestroyed() {
        return destroyed;
    }
    
    public Ship getShip() {
        return ship;
    }
}
