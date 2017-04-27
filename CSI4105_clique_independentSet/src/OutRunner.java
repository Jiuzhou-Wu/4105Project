import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class OutRunner {

	
	public static void main(String[] args){
		
//		naiveTester();
		cliqueTest();
	}
	
	
	
	public static void MIStest(){
		
		boolean[][] graph = graphGenerator(25, 0.1);
		boolean[][] graph1 = {{false, false, false}, {false, false, true}, {false, true, false}};
		System.out.println(OutRunner.graphToString(graph));
		
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		
		for(int i = 0; i < graph.length; i++){
			nodes.add(i);
		}
		
		ArrayList<Integer> solution = IndependentSet.MIS(graph, nodes, 0);
		
//		System.out.println(ToolBox.removeAdjacentNode(graph, nodes, nodes.get(0)).toString());
		System.out.println(solution.toString());
		
	}
	
	public static void ioTester(){
				
		// n means nodes, dPos means degree possibility
		int n = 10;
		double dPos = 0.5;
		
		boolean[][] graph = graphGenerator(n, dPos);
		
		
		System.out.println(graphToString(graph));
		
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		for(int i = 0; i < graph.length; i++){
			nodes.add(i);
		}
		
		int node = 0;
		
		ToolBox.removeAdjacentNode(graph, nodes, node);
		
		for(int i = 0; i < nodes.size(); i++){
			System.out.print(nodes.get(i) + " ");
		}
		
//		try {
//			ToolBox.writeGraph(graph);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		graph = ToolBox.readGraph("data/matrix");
//		System.out.println(graphToString(graph));
		
	}
	
	public static void naiveTester(){
		int n = 10;
		double dPos = 0.5;
		
		boolean[][] graph = graphGenerator(n, dPos);
		
		
//		
//		System.out.println(graphToString(ToolBox.tranAlg(graph)));
//		
//		System.out.println(ToolBox.getDegree(graph).toString());
		System.out.println("System generating combinations...");
		List<LinkedList<Integer>> combinations = ToolBox.combination(graph.length-1);
		
		System.out.println("System sorting combinations...");
		ToolBox.sortLinkedList(combinations);
		int hope = naiveBruteForce(graph, combinations);
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		System.out.print("the naive brute force answer: ");
		System.out.println(combinations.get(hope).toString());
		
		System.out.println(graphToString(graph));
		for(int i = 0; i < graph.length; i++){
			nodes.add(i);
		}
		
		ArrayList<Integer> solution = IndependentSet.MIS(graph, nodes, 0);
		System.out.println(solution);
	}
	
public static void cliqueTest(){
		
		boolean[][] graph = graphGenerator(10, 0.1);
		
		System.out.println("System generating combinations...");
		List<LinkedList<Integer>> combinations = ToolBox.combination(graph.length-1);
		
		System.out.println("System sorting combinations...");
		ToolBox.sortLinkedList(combinations);
		int hope = naiveBruteForceCliqueVersion(graph, combinations);
		
		
		System.out.print("the naive brute force answer: ");
		System.out.println(combinations.get(hope).toString());
		
		System.out.println(graphToString(graph));
		
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		for(int i = 0; i < graph.length; i++){
			nodes.add(i);
		}
		System.out.println(Clique.MCG(graph, nodes, 0));
		
	}
	
	public static boolean[][] graphGenerator(int numNode, double dPos){
		int n = numNode;
		boolean[][] graph = new boolean[n][n];
		for(int i=0;i<numNode;i++){
			for(int j=i+1; j<n; j++){
				double random = Math.random();
				if(random <= dPos){
					graph[i][j] = true;
					graph[j][i] = true;
				}
			}
		}
		return graph;
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
		buffer.append("      ");
		for(int size = 0; size<graph.length; size++){
//			System.out.println(graph.length);
			buffer.append(size);
			for(int digit = 0; digit < 5-String.valueOf(size).length(); digit++){
				buffer.append(" ");
			}
			buffer.append(" ");
		}
		buffer.append("\n");
//		System.out.println(buffer);
		for(int i=0;i<n;i++){
			buffer.append(i);
			for(int digit = 0; digit < 5-String.valueOf(i).length(); digit++){
				buffer.append(" ");
			}
			buffer.append(" ");
			for(int j=0;j<n;j++){
				buffer.append( graph[i][j] );
				if(graph[i][j]){
					
					buffer.append( " " );
				}
				
				buffer.append(" ");
			}
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
	
	public static int naiveBruteForceCliqueVersion(boolean[][] graph, List<LinkedList<Integer>> combinations){
		
		for(int combinationCounter = 0; combinationCounter < combinations.size(); combinationCounter++){
			System.out.println("checked/totle combinations: " + combinationCounter + "/" + combinations.size());
			if(ToolBox.cliqueSolutionCheck(combinations.get(combinationCounter), graph)){
				return combinationCounter;
			}
		}
		
		return 0;
	}
	
	public static int naiveBruteForce(boolean[][] graph, List<LinkedList<Integer>> combinations){
		
		for(int combinationCounter = 0; combinationCounter < combinations.size(); combinationCounter++){
			System.out.println("checked/totle combinations: " + combinationCounter + "/" + combinations.size());
			boolean edges = false; //assume there is no edges
			LinkedList<Integer> combination = combinations.get(combinationCounter);
			//double loop
			for(int i = 0; i < combination.size() && !edges; i++){
				for(int j = i+1; j < combination.size() && !edges; j++){
					edges = graph[combination.get(i)][combination.get(j)];
				}
			}
			if(!edges){
				return combinationCounter;
			}
			
		}
		
		
		return 0;
	}
}
