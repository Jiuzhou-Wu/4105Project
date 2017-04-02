import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OutRunner {
	// nodes number n
	public static void main(String[] args){
		int n = 5;
		boolean[][] graph = new boolean[n][n];
		int maxEdges = n*(n-1)/2;

		List<Integer> edges = new ArrayList<Integer>();
		for(int i=0; i<maxEdges; i++){
			edges.add(i);
		}
		Collections.shuffle(edges, new Random());
		int edgesNum = edges.get((int) (Math.random()*maxEdges));	
		

		List<Integer[]> pos = new ArrayList<Integer[]>();
		
		for(int i=0;i<n;i++){
			for(int j=i+1; j<n; j++){
				Integer[] cur = {i,j};
				pos.add(cur);
			}
		}
		
		for(int i=0; i< edgesNum; i++){
		
			boolean flag = false;
			do{
				long seed = System.nanoTime();
				Collections.shuffle(pos, new Random(seed));
				Integer[] position = pos.get(0);
				if( !graph[position[0]][position[1]]){
					graph[position[0]][position[1]] = true;
					graph[position[1]][position[0]] = true;
					flag = true;
				}
			}
			while(!flag);
			pos.remove(0);
		}
		
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(graph[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
