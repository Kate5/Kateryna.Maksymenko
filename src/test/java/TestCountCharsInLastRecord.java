import kate.RandomBeacon;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kate on 4/3/16.
 */
public class TestCountCharsInLastRecord {

    public final static RandomBeacon beacon = new RandomBeacon();
    public final static String TEST_SERVICE_ENDPOINT = "https://beacon.nist.gov/rest/record/last";
    private final String outputValue = "123QWEE";
    private final static String TEST_XML_FILE = "src/test/resources/for_test.xml";
    private final static File file = new File(TEST_XML_FILE);
    private final static String OUTPUT_XML = "63069BE0D64A8F9D90D67004F60151";

    // requirement: count the number of characters in the OutputValue
    @Test
    public void testCountCharsInLastRecord() throws Throwable {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("1", 1);
        map.put("2", 1);
        map.put("3", 1);
        map.put("Q", 1);
        map.put("W", 1);
        map.put("E", 2);

        Map<String, Integer> result = beacon.countCharsInLastRecord(outputValue);
        Assert.assertEquals("Check if two HashMap equals.", map, result);
    }

    // requirement: retrieve the most recent event from the randomness beacon: check if correct url: https://beacon.nist.gov/rest/record/last
    @Test
    public void testEndpointForRecentBeacon() throws Throwable {
        Assert.assertEquals("Expected url: https://beacon.nist.gov/rest/record/last.", TEST_SERVICE_ENDPOINT, beacon.SERVICE_ENDPOINT);
    }

    // test method for xml parsing
    @Test
    public void testXMLParser() throws Throwable {
        String result = beacon.parseXML( file );
        Assert.assertEquals("Test result of xml parsing. ", OUTPUT_XML, result);
    }

    // requirement: the first value of any given line is a character, the second value is the number
    @Test
    public void testOutputFormat() throws Throwable {
    }

    // integration test of methods from RandomBeacon class
    @Test
    public void testRandomBeacon() throws Throwable {

        String val = beacon.parseXML( beacon.getData() );
        Assert.assertEquals( Helper.getOutputMap() ,  beacon.countCharsInLastRecord(val) );

    }

}
