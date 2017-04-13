import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class OutRunner {

	
	public static void main(String[] args){
//		int n = 5; // nodes number n
//		
//		
//		boolean[][] graph = graphGenerator(n);
//		
//		System.out.println(graphToString(graph));
//		
//		System.out.println(graphToString(ToolBox.tranAlg(graph)));
//		
//		System.out.println(ToolBox.getDegree(graph).toString());
		
//		LinkedList<Integer> base = new LinkedList<Integer>();
//		base.add(1);
//		base.add(2);
//		base.add(3);
//		base.add(4);
//		LinkedList<Integer> copy = new LinkedList<Integer>(base);
//		System.out.println(copy.toString());
//		
//		base.add(5);
//		
//		System.out.println(copy.toString());
//		System.out.println(base.toString());
		List<LinkedList<Integer>> lists = ToolBox.combination(5);
		ToolBox.sortLinkedList(lists);
		for(int i = 0; i<lists.size(); i++){
			for(int j = 0; j<lists.get(i).size(); j++){
				System.out.print(lists.get(i).get(j));
			}
			System.out.print("\n");
		}
		
	}
	
	public static boolean[][] graphGenerator(int numNode){
		int n = numNode;
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
		
		return graph;
	}
	
	public static String graphToString(boolean[][] graph){
		StringBuffer buffer = new StringBuffer();
		int n = graph.length;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				buffer.append( graph[i][j] );
				buffer.append(" ");
			}
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
}
