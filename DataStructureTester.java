/**
 * The Tester for the Minneo Data Structures.
 *
 * @author ryanm
 * @version 1.0
 */
public class DataStructureTester
{
    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args)
    {
        MinneoArrayList<String> testArrayList = new MinneoArrayList<String>(3);

        testArrayList.add("Minneo");
        testArrayList.add("Ryan");
        testArrayList.add("Billy");
        testArrayList.add("Welt");

        System.out.println(testArrayList.toString());
    }
}
