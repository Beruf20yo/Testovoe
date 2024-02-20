package task1;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("No arguments entered");
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int[] array = fillArray(n);
        System.out.println(outputTheWay(array, m));
    }

    private static int[] fillArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    private static String outputTheWay(int[] array, int m) {
        StringBuilder sb = new StringBuilder();
        int startId = 0;
        int endId = 0;
        do {
            sb.append(array[endId]);
            endId += m - 1;
            if (endId > array.length - 1) {
                endId -= array.length;
            }
        } while (startId != endId);
        return sb.toString();
    }
}