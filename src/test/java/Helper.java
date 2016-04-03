import java.util.HashMap;
import java.util.Map;

/**
 * Created by kate on 4/3/16.
 */
public class Helper {

    public static Map getOutputMap() {

        Map<String,Integer> correctMap = new HashMap<String, Integer>();
        correctMap.put( "F", 15 );
        correctMap.put( "E", 9 );
        correctMap.put( "D", 13 );
        correctMap.put( "C", 4 );
        correctMap.put( "B", 4 );
        correctMap.put( "A", 9 );
        correctMap.put( "9", 10 );
        correctMap.put( "8", 7 );
        correctMap.put( "7", 8 );
        correctMap.put( "6", 9 );
        correctMap.put( "5", 8 );
        correctMap.put( "4", 6 );
        correctMap.put( "3", 4 );
        correctMap.put( "2", 6 );
        correctMap.put( "1", 8 );
        correctMap.put( "0", 8 );
        return correctMap;
    }

}
