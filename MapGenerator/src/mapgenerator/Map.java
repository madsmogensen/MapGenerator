package mapgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mads S. Mogensen
 */

/*
    create center base bounds
    for #players*2
        create base bound
            tiles available for base = total (tiles-centerbaseTiles)/#bases
    add goldmines
    add lumbercamps
    
    */
public class Map {
    
    private ArrayList<ArrayList<Tile>> map = new ArrayList<>();
    private int mapSize; //length and/or width of the map
    private int numberOfPlayers;
    
    //Constructor Method for Map
    public Map(int players){
        numberOfPlayers = players;
        setMapSize(players);
        
        createEmptyMap(); //done
        createCenterBase();
    }
    
    private void setMapSize(int players){
        switch(players){
            case 2:
                mapSize = 10;
                break;
            case 3:
                mapSize = 20;
                break;
            case 4:
                mapSize = 30;
                break;
            default:
                mapSize = 30;
        }      
    }
    
    //populates the map of specified size with empty tiles
    private void createEmptyMap(){
        for(int i = 0; i < mapSize; i++){
            map.add(new ArrayList<>());
            for(int j = 0; j < mapSize; j++){
                map.get(i).add(new Tile(i,j));
            }
        }
    }
    
    private void createCenterBase(){
        
    }
    
    public Tile getTile(int x, int y){
        return map.get(x).get(y);
    }
    
    
    
    
    
}

