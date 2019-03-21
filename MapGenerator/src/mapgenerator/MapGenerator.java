package mapgenerator;

/**
 *
 * @author Mads S. Mogensen
 */
public class MapGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("creating map");
        Map newMap = new Map(4);
        newMap.print();
        
    }
}
