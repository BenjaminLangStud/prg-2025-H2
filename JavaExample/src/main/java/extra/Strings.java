package extra;

public class Strings {

    public static class MyValue {
        private String value;

        public MyValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        summarize();
        summarize2();
        summarize3();
    //     MyValue val1 = new MyValue("Hel");
    //     MyValue val2 = new MyValue("lo");
    //     swap(val1, val2);
    //     String val12 = val1.getValue() + val2.getValue();
    //     System.out.println(val12);
    }

    private static void summarize() {
        long startTime = System.nanoTime();
        String output = "";
        for (int i=0; i<1000; i++) {
            output = output + i;
        }
        long endTime = System.nanoTime();
        //System.out.println(output);
        System.out.println("Execution time: " + (endTime - startTime) + " ns");
    }

    private static void summarize2() {
        long startTime = System.nanoTime();
        StringBuffer output = new StringBuffer();
        for (int i=0; i<1000; i++) {
            output.append(i);
        }
        long endTime = System.nanoTime();
        //System.out.println(output.toString());
        System.out.println("Execution time: " + (endTime - startTime) + " ns");
    }

    private static void summarize3() {
        long startTime = System.nanoTime();
        StringBuilder output = new StringBuilder();
        for (int i=0; i<1000; i++) {
            output.append(i);
        }
        long endTime = System.nanoTime();
        //System.out.println(output.toString());
        System.out.println("Execution time: " + (endTime - startTime) + " ns");
    }

    private static void swap(MyValue s1, MyValue s2) {
        String temp = s1.getValue();
        s1.setValue(s2.getValue());
        s2.setValue(temp);
        System.out.println(s1.getValue() + " " + s2.getValue());
    }


    private static void swap(String s1, String s2) {
        String temp = s1;
        s1 = s2;
        s2 = temp;
        System.out.println(s1 + " " + s2);
    }
}
