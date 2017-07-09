import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FractionalKnapsack {
    private static class Unit implements Comparable<Unit>{
        int idx; double value;
        public Unit(int idx, double value){
            this.idx = idx;
            this.value = value;
        }
        @Override
        public int compareTo(Unit o) {
            if(value > o.value) return -1;
            else if(o.value > value) return 1;
            else return 0;
        }
    }
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        Queue<Unit> queue = new PriorityQueue<>();
        for(int i = 0;i<values.length;++i){
            queue.add(new Unit(i,  values[i]*1.0/weights[i]));
        }
        while(capacity > 0 && queue.size() > 0){
            Unit unit = queue.remove();
            int idx = unit.idx;
            if(weights[idx] > capacity){
                value+=capacity*unit.value;
                capacity = 0;
            }else{
                value+=weights[idx]*unit.value;
                capacity-=weights[idx];
            }
        }
        value = (double)Math.round(value * 10000d) / 10000d;
        return value;
    }
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
