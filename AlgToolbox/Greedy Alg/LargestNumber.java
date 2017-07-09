import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        //write your code here
        Arrays.sort(a, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Long l1 = Long.valueOf(o1.concat(o2));
                Long l2 = Long.valueOf(o2.concat(o1));
                if(l1 > l2) return -1;
                else if(l2 > l1) return 1;
                else return 0;
            }
        });
        String result = "";
        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

