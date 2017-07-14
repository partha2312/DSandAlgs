import java.util.*;

public class PointsAndSegments {

    static class Pairs{
        int point;
        char symbol;
        public Pairs(int point, char symbol){
            this.point = point;
            this.symbol = symbol;
        }
    }

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        //write your code here
        List<Pairs> list = new ArrayList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < points.length; ++i){
            list.add(new Pairs(points[i], 'p'));
            List<Integer> temp = map.get(points[i]);
            if(temp == null) temp = new ArrayList<>();
            temp.add(i);
            map.put(points[i], temp);
        }
        for(int i = 0; i<starts.length; ++i){
            list.add(new Pairs(starts[i], 'l'));
            list.add(new Pairs(ends[i], 'r'));
        }
        list.sort(new Comparator<Pairs>() {
            @Override
            public int compare(Pairs o1, Pairs o2) {
                if(o1.point < o2.point) return -1;
                else if(o1.point > o2.point) return 1;
                else {
                    if(o1.symbol < o2.symbol) return  -1;
                    else if(o2.symbol < o1.symbol) return 1;
                }
                return 0;
            }
        });
        int count = 0;
        for(Pairs pair : list){
            if(pair.symbol == 'l') ++count;
            else if(pair.symbol == 'r') --count;
            else {
                for(Integer idx : map.get(pair.point)){
                    cnt[idx] = count;
                }
            }
        }
        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

