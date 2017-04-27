import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class OutRunner {

	
	public static void main(String[] args) throws IOException{
		int sizeOfVertexes = 15;
		for(double density = 0.1; density < 1; density = density+0.2){
			
			System.out.println(density);
			File file = new File("data/matrix");
			file.createNewFile();
			BufferedWriter fw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));
			
			//the three alg share one graph
			boolean[][] graph = graphGenerator(sizeOfVertexes, density);
//			System.out.println(graphToString(graph));
			//save the graph to file
			fw.write("Graph vertexes: " + sizeOfVertexes);
			fw.write("; Graph density: " + density + "\n");
			
			fw.write(graphToString(graph));
			long startTime;
			long endTime;
			
			// run three alg
			/**
			 * begin brute force search
			 */
			startTime = System.nanoTime();
			
			List<LinkedList<Integer>> combinations = ToolBox.combination(graph.length-1);
			
//			System.out.println("System sorting combinations...");
			ToolBox.sortLinkedList(combinations);
			int hope = naiveBruteForce(graph, combinations);
			LinkedList<Integer> nodes = new LinkedList<Integer>();
//			System.out.print("the naive brute force answer: ");
			nodes = combinations.get(hope);
			//save the result to file
			endTime = System.nanoTime();
			fw.write(nodes.toString());
			fw.write("The brute force alg was done in: " + (endTime - startTime) + "\n");
			
			/**
			 * end brute force search 
			 */
			
			/**
			 * begin MIS alg
			 */
			startTime = System.nanoTime();
			
			ArrayList<Integer> vertexes = new ArrayList<Integer>();
			
			for(int i = 0; i < graph.length; i++){
				vertexes.add(i);
			}
			
			ArrayList<Integer> solution = IndependentSet.MIS(graph, vertexes, 0);
			
			endTime = System.nanoTime();
			fw.write(solution.toString());
			fw.write("The miximal independent set alg was done in: " + (endTime - startTime) + "\n");
			
			/**
			 * end MIS alg
			 */
			
			/**
			 * begin MCG alg
			 */
			
			startTime = System.nanoTime();
			ToolBox.sortVertexsList(ToolBox.tranAlg(graph), vertexes);
			solution = Clique.MCG(ToolBox.tranAlg(graph), vertexes, 0);
			endTime = System.nanoTime();
			fw.write(solution.toString());
			fw.write("The miximal clique alg was done in: " + (endTime - startTime) + "\n\n\n");
			fw.close();
		}
		
		
//		boolean found = false;
		

//		while(!found){
//			found = naiveTester();
//		}
//		cliqueTest();
		
		
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
	
	public static boolean naiveTester(){
		int n = 10;
		double dPos = 0.5;
		
//		boolean[][] graph = graphGenerator(n, dPos);
		boolean[][] graph = {
				{     false, false, false, false, false, true , true , false, true , true , },
				{     false, false, false, true , false, false, true , false, true , true , },
				{     false, false, false, false, true , false, true , true , false, true , },
				{     false, true , false, false, false, false, false, false, true , false, },
				{     false, false, true , false, false, true , false, false, false, true , },
				{     true , false, false, false, true , false, false, true , false, true , },
				{     true , true , true , false, false, false, false, true , true , false, },
				{     false, false, true , false, false, true , true , false, true , false, },
				{     true , true , false, true , false, false, true , true , false, false, },
				{     true , true , true , false, true , true , false, false, false, false, },
		};
		
//		
//		System.out.println(graphToString(ToolBox.tranAlg(graph)));
//		
//		System.out.println(ToolBox.getDegree(graph).toString());
		System.out.println("System generating combinations...");
		List<LinkedList<Integer>> combinations = ToolBox.combination(graph.length-1);
		
		System.out.println("System sorting combinations...");
		ToolBox.sortLinkedList(combinations);
		int hope = naiveBruteForce(graph, combinations);
		
		System.out.print("the naive brute force answer: ");
		System.out.println(combinations.get(hope).toString());
		
		System.out.println(graphToString(graph));
		
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		for(int i = 0; i < graph.length; i++){
			nodes.add(i);
		}
		
		ArrayList<Integer> solution = IndependentSet.MIS(graph, nodes, 0);
		System.out.println(solution);
		
		return (combinations.get(hope).size() != solution.size());
	}
	
public static boolean cliqueTest(){
		
		boolean[][] graph = graphGenerator(12, 0.5);
//		boolean[][] graph = {
//				{     false, true , true , true , false, false, true , true , true , true , true , true , },
//				{     true , false, false, false, false, true , true , false, false, true , false, true , },
//				{     true , false, false, false, true , true , true , true , false, true , false, false, },
//				{     true , false, false, false, false, true , false, true , false, false, false, false, },
//				{     false, false, true , false, false, true , true , true , false, false, false, false, },
//				{     false, true , true , true , true , false, true , true , false, false, false, false, },
//				{     true , true , true , false, true , true , false, false, false, true , false, false, },
//				{     true , false, true , true , true , true , false, false, false, false, false, true , },
//				{     true , false, false, false, false, false, false, false, false, false, true , false, },
//				{     true , true , true , false, false, false, true , false, false, false, false, true , },
//				{    true , false, false, false, false, false, false, false, true , false, false, false, },
//				{    true , true , false, false, false, false, false, true , false, true , false, false, }
//		};
		System.out.println("System generating combinations...");
		List<LinkedList<Integer>> combinations = ToolBox.combination(graph.length-1);
		
		System.out.println("System sorting combinations...");
		ToolBox.sortLinkedList(combinations);
		int hope1 = naiveBruteForceCliqueVersion(ToolBox.tranAlg(graph), combinations);
		System.out.print("the naive brute force answer: ");
		System.out.println(combinations.get(hope1).toString());
		
		
		int hope2 = naiveBruteForce(graph, combinations);
		System.out.print("the naive brute force answer: ");
		System.out.println(combinations.get(hope2).toString());
		
		System.out.println(graphToString(graph));
		
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		for(int i = 0; i < graph.length; i++){
			nodes.add(i);
		}
		
		ToolBox.sortVertexsList(ToolBox.tranAlg(graph), nodes);
		
		ArrayList<Integer> solution = Clique.MCG(ToolBox.tranAlg(graph), nodes, 0);
		System.out.println(solution.toString());
		return (combinations.get(hope1).size() != solution.size() || hope1 != hope2);
		
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
		buffer.append("       ");
		for(int size = 0; size<graph.length; size++){
//			System.out.println(graph.length);
			buffer.append(size);
			for(int digit = 0; digit < 5-String.valueOf(size).length(); digit++){
				buffer.append(" ");
			}
			buffer.append("  ");
		}
		buffer.append("\n");
//		System.out.println(buffer);
		for(int i=0;i<n;i++){
			buffer.append(i);
			buffer.append("{");
			for(int digit = 0; digit < 5-String.valueOf(i).length(); digit++){
				buffer.append(" ");
			}
			buffer.append(" ");
			for(int j=0;j<n;j++){
				buffer.append( graph[i][j] );
				
				if(graph[i][j]){
					
					buffer.append( " " );
				}
				
				buffer.append(", ");
			}
			buffer.append("},\n");
		}
		
		return buffer.toString();
	}
	
	public static int naiveBruteForceCliqueVersion(boolean[][] graph, List<LinkedList<Integer>> combinations){
		
		for(int combinationCounter = 0; combinationCounter < combinations.size(); combinationCounter++){
//			System.out.println("checked/totle combinations: " + combinationCounter + "/" + combinations.size());
			if(ToolBox.cliqueSolutionCheck(combinations.get(combinationCounter), graph)){
				return combinationCounter;
			}
		}
		
		return 0;
	}
	
	public static int naiveBruteForce(boolean[][] graph, List<LinkedList<Integer>> combinations){
		
		for(int combinationCounter = 0; combinationCounter < combinations.size(); combinationCounter++){
//			System.out.println("checked/totle combinations: " + combinationCounter + "/" + combinations.size());
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
