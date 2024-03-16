import java.util.*;
import java.io.*;
class Kruskal_graph implements Comparable<Kruskal_graph>{
    int v;
    int w;
    int cost;

    public Kruskal_graph(int v,int w,int cost){
        this.v=v;
        this.w=w;
        this.cost=cost;
    }

    @Override
    public int compareTo(Kruskal_graph node){
        return this.cost - node.cost;
    }
}

public class Main {
    static int N,M;
    static PriorityQueue<Kruskal_graph> queue = new PriorityQueue();
    static int[] parents;
    static int result=0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N= Integer.parseInt(br.readLine());
        M= Integer.parseInt(br.readLine());
    parents = new int[N+1];
        for (int i = 0 ; i < M ; i++){
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            queue.offer(new Kruskal_graph(a,b,c));
        }

        makeSet();

        while(!queue.isEmpty()){
            Kruskal_graph now_graph = queue.poll();
            if(find(now_graph.v) == find(now_graph.w)) continue;
            else{
                union(now_graph.v,now_graph.w);
                result += now_graph.cost;
            }
        }
        System.out.println(result);
    }

    private static void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        if( a> b){
            parents[a] = b;
        }else{
            parents[b]=a;
        }
    }

    private static void makeSet() {
        for (int i = 0 ; i <=N ; i++){
            parents[i]=i;
        }
    }
    private static int find(int x){
        if(parents[x]==x){
            return x;
        }else return parents[x] = find(parents[x]);
    }
}