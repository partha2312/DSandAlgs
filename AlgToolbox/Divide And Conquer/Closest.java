import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.x == x ? Long.signum(y - o.y) : Long.signum(x - o.x);
        }
    }

    static Point[] list;
    static double minimalDistance(int[] x, int y[]) {
        double ans = Double.POSITIVE_INFINITY;
        //write your code here
        Arrays.sort(list);
        ans = minimalDistanceHelper(0, x.length-1, ans);
        return ans;
    }

    static double verticalDist(List<Point> verticals){
        double answer = Double.POSITIVE_INFINITY;
        for(int i = 0;i<verticals.size()-1;i++){
            for(int j = i+1;j<verticals.size();j++){
                Point p1 = verticals.get(i);
                Point p2 = verticals.get(j);
                double temp = (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
                temp = Math.sqrt(temp);
                if(temp < answer) answer = temp;
            }
        }
        return answer;
    }

    static double minimalDistanceHelper(int start, int end, double ans){
        if(Math.abs(end - start) < 1){
            return Double.POSITIVE_INFINITY;
        }
        if(Math.abs(end - start) == 1){
            return calculateDistance(start, end);
        }
        int mid = (start + (end-start)/2);
        double lans = minimalDistanceHelper(start, mid, ans);
        double rans = minimalDistanceHelper(mid+1, end, ans);
        if(lans < rans){
            ans = lans < ans ? lans : ans;
        }else{
            ans = ans < rans ? ans : rans;
        }

        List<Point> verticals = new ArrayList<>();
        Point midPoint = list[mid];
        for(Point point : list){
            if(Math.abs(point.x - midPoint.x) < ans){
                verticals.add(point);
            }
        }
        verticals.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.y == o2.y){
                    if(o1.x < o2.x) return -1;
                    else if(o1.x > o2.x) return 1;
                }else{
                    if(o1.y < o2.y) return -1;
                    else if(o1.y > o2.y) return 1;
                }
                return 0;
            }
        });
        double verticalAns = verticalDist(verticals);
        return ans < verticalAns ? ans : verticalAns;
    }

    static double calculateDistance(int start, int end){
        double answer = ((list[start].x - list[end].x)*(list[start].x - list[end].x)) +
                ((list[start].y - list[end].y)*(list[start].y - list[end].y));
        return Math.sqrt(answer);
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        list = new Point[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
            list[i] = new Point(x[i], y[i]);
        }
        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
