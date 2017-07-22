import java.io.*;
import java.util.*;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;
    Scheduler[] schedulers;
    private FastScanner in;
    private PrintWriter out;

    private class Scheduler implements Comparable<Scheduler>{
        private long nextFreeTime;
        private int id;

        public Scheduler(int id, long nextFreeTime){
            this.id = id;
            this.nextFreeTime = nextFreeTime;
        }

        @Override
        public int compareTo(Scheduler o) {
            if(this.nextFreeTime == o.nextFreeTime){
                if(this.id < o.id)
                    return -1;
                else
                    return 1;
            }else{
                if(this.nextFreeTime < o.nextFreeTime)
                    return -1;
                else if(this.nextFreeTime > o.nextFreeTime)
                    return 1;
                return 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    public void shiftDown(int i){
        int leftIdx = 2*i+1;
        int rightIdx = 2*i+2;
        int minIdx = i;
        if(leftIdx < numWorkers && schedulers[minIdx].compareTo(schedulers[leftIdx]) > 0 ){
            minIdx = leftIdx;
        }
        if(rightIdx < numWorkers && schedulers[minIdx].compareTo(schedulers[rightIdx]) > 0){
            minIdx = rightIdx;
        }
        if(i != minIdx){
            Scheduler temp = schedulers[i];
            schedulers[i] = schedulers[minIdx];
            schedulers[minIdx] = temp;
            shiftDown(minIdx);
        }
    }

    public void minHeapify(){
        /*for(int i=numWorkers/2;i>=0;--i){
            shiftDown(i);
        }*/
        shiftDown(0);
    }

    private void assignJobs(){
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        /*schedulers = new Scheduler[jobs.length];
        for(int i=0;i<numWorkers;++i){
            schedulers[i] = new Scheduler(i, 0);
        }
        for(int i=0;i<jobs.length;++i){
            Scheduler scheduler = schedulers[0];
            int id = scheduler.id;
            long start = scheduler.nextFreeTime;
            assignedWorker[i] = id;
            startTime[i] = start;
            schedulers[0].nextFreeTime += jobs[i];
            if(jobs[i] > 0) minHeapify();
        }*/
        PriorityQueue<Scheduler> queue = new PriorityQueue<>();
        for(int i=0;i<numWorkers;++i){
            queue.add(new Scheduler(i, 0));
        }
        for(int i=0;i<jobs.length;++i){
            if(jobs[i] > 0){
                Scheduler scheduler = queue.poll();
                assignedWorker[i] = scheduler.id;
                startTime[i] = scheduler.nextFreeTime;
                scheduler.nextFreeTime += jobs[i];
                queue.add(scheduler);
            }else{
                Scheduler scheduler = queue.peek();
                assignedWorker[i] = scheduler.id;
                startTime[i] = scheduler.nextFreeTime;
            }
        }
    }

    /*private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }*/

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
