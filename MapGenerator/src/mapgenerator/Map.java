package mapgenerator;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import static mapgenerator.TileType.*;

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
    private int centerBaseMinSize;
    private int normalBaseMinSize;
    
    //Constructor Method for Map
    public Map(int players){
        this.numberOfPlayers = players;
        setMapSize(numberOfPlayers);
        
        createEmptyMap(); //done
        Tile center = getTile(mapSize/2,mapSize/2);
        center.setType(BASE);
        createCenterBase(center);
    }
    
    private void setMapSize(int players){
        switch(players){
            case 2:
                mapSize = 11;
                centerBaseMinSize = 10;
                normalBaseMinSize = 10;
                break;
            case 3:
                mapSize = 21;
                centerBaseMinSize = 15;
                normalBaseMinSize = 10;
                break;
            case 4:
                mapSize = 31;
                centerBaseMinSize = 30;
                normalBaseMinSize = 10;
                break;
            default:
                mapSize = 31;
                centerBaseMinSize = 10;
                normalBaseMinSize = 10;
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
    
    private void createCenterBase(Tile center){
        ArrayList<Tile> base = new ArrayList<>();
       
        //populate the base with the center and it's neighbors
        base.add(center);
        for(Tile tile : getAllNonBaseNeighbors(center)){
            tile.setType(BASE);
            base.add(tile);
        }
        //grow base untill at least desired size
        int iteration = 0;
        int maxAttempts = 1000;
        while(base.size() <= centerBaseMinSize && iteration < maxAttempts){
            ArrayList<Tile> newBaseTiles = new ArrayList<>();
            for(Tile tile : base){
                Tile growTile = getRandom(getAllNonBaseNeighbors(tile));
                if(growTile != null && !base.contains(growTile)){
                    growTile.looseLife();
                    if(growTile.getLives() <= 0){
                        growTile.setType(BASE);
                        newBaseTiles.add(growTile);
                    }
                }
            }
            for(Tile tile : newBaseTiles){ base.add(tile); };
            iteration++;
        }
        //wall around the base except the exits
    }
    
    private void createBase(Tile center){
        
    }
    
    public Tile getTile(int x, int y){
        return map.get(x).get(y);
    }
    
    private Tile getRandom(ArrayList<Tile> neighbors){
        if(neighbors.size() > 0){
            int i = ThreadLocalRandom.current().nextInt(0, neighbors.size()); //actual size not included
            return neighbors.get(i);
        }
        return null; //no neighbors that aren't already part of a base
    }
    
    private ArrayList<Tile> getAllNonBaseNeighbors(Tile center){
        ArrayList<Tile> neighbors = new ArrayList<>();
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                Tile tile = getTile(center.getX()+i,center.getY()+j);
                if(!tile.equals(center)){
                    if(!tile.getType().equals(BASE)){
                        neighbors.add(tile);
                    }
                }
            }
        }
        return neighbors;
    }
    
    private ArrayList<Tile> getAdjecentNeighbors(Tile center){
        return null;
    }
    
    
    public void print(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size(); j++){
                Tile tile = map.get(i).get(j);
                if(tile.getType().equals(BASE)){
                    sb.append("# ");
                }else{
                    sb.append(tile.getLives()+" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

