/**
 * The Tester for the Minneo Data Structures.
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

        MinneoArrayList<String> compareList = new MinneoArrayList<String>();

        compareList.add("Ryan");
        compareList.add("Bro");




    }
}
