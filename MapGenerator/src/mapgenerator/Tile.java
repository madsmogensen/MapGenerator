package mapgenerator;

import static mapgenerator.TileType.*;

/**
 *
 * @author Mads S. Mogensen
 */
public class Tile {
    private int x;
    private int y;
    private TileType type = UNDEFINED;
    
    //used for base growing
    private int lives = 3;
    
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public void looseLife(){
        this.lives--;
    }
    
}
