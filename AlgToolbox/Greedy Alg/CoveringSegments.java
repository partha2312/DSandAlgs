import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        Arrays.sort(segments);
        int[] points = new int[2 * segments.length];
        int idx = 0;
        int point = segments[0].end;
        for(int i = 1; i<segments.length;++i){
            if(segments[i].start <= point){

            }else{
                points[idx++] = point;
                point = segments[i].end;
            }
        }
        points[idx++] = point;
        int[] result = new int[idx];
        for(int i = 0;i<idx;i++){
            result[i] = points[i];
        }
        return result;
    }

    private static class Segment implements Comparable<Segment>{
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            if(end > o.end) return 1;
            else if (end < o.end) return -1;
            else return 0;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
