package kate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kate on 4/3/16.
 */
public class RandomBeacon {

    private final static String OUTPUT_VALUE_TAG = "outputValue";
    private final static String ROOT_TAG = "record";
    private final static String FILE = "test.xml";
    public final static String SERVICE_ENDPOINT = "https://beacon.nist.gov/rest/record/last";

    public static void main(String[] args) throws Exception {

        if (args.length > 0) {
            try {
            } catch (NumberFormatException e) {
                String from = args[0].toString();
                String to = args[1].toString();
                try {
                    readWithOption( from , to );
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            }
        }
        else {

            RandomBeacon beacon = new RandomBeacon();
            String val = beacon.parseXML(getData());
            beacon.countCharsInLastRecord(val);

        }
    }

    //1
    public Map<String, Integer> countCharsInLastRecord(String outputValue) throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        char[] chars = outputValue.toCharArray();
        int q = 0;
        for (int a = 0; a < chars.length; a++) {
            String key = Character.toString(chars[a]);
            if (!map.containsKey(key)) {
                map.put(key, ++q);
            } else {
                map.put(key, map.get(key) + 1);
            }
            q = 0;
        }

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
        return map;

    }


    public static String parseXML(File xmlFile) throws Exception {

        String value = "";
        try {
            // File xmlFile = getData();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(ROOT_TAG);

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    value += eElement.getElementsByTagName(OUTPUT_VALUE_TAG).item(0).getTextContent();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

        }
        return value;
    }

    public static File getData() throws Exception {

        File xmlFile = new File(FILE);
        URL website = new URL(SERVICE_ENDPOINT);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(FILE);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        return xmlFile;
    }

    //2
    public static void readWithOption( String from, String to ) throws Throwable {

        String mFrom = from.substring(0, 1);
        String dFrom = from.substring(9, 10);
        String hFrom = from.substring(15, 16);
        String mTo = to.substring(0, 1);
        String dTo = to.substring(8, 9);

    }
}
