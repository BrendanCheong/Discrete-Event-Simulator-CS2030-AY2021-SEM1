public class TestList {

    public static void main(String[] args) {
        CustomList<String> myListString = new CustomList<String>();
        myListString.addElement("Number 1");
        myListString.addElement("Number 2");
        System.out.println(myListString.toString());

        CustomList<Double> myListDouble = new CustomList<Double>();
        myListDouble.addElement(1.05);
        myListDouble.addElement(2.05);
        System.out.println(myListDouble.toString());
        System.out.println(myListDouble.get(0));
    }

}
