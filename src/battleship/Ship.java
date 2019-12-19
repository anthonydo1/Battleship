package battleship;

import java.util.ArrayList;

public class Ship {
    private String name;
    private int size;
    private ArrayList<Cell> ship;
    
    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        ship = new ArrayList<Cell>();
    }
    
    public void addShipCell(Cell cell) {
        ship.add(cell);
    }
    
    public void setShipName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isDestroyed() {
        for (int i = 0; i < ship.size(); i++) {
            if (!ship.get(i).isDestroyed()) {
                return false;
            }
        }
        return true;
    }
    
    public int getSize() {
        return size;
    }
}