import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ToolBox {
	public static boolean[][] tranAlg(boolean[][] graph){
		
		boolean[][] compelement = new boolean[graph.length][graph.length];
		
		for(int i = 0; i<graph.length; i++){
			for(int j = 0; j<graph.length; j++){
				if( i != j)
				compelement[i][j] = !graph[i][j];
			}
		}
		
		return compelement;
	} 
	
	public static List<Integer> getDegree(boolean[][] graph){
		
		List<Integer> degrees = new ArrayList<Integer>(graph.length);
		
		for(int i = 0; i < graph.length; i++){
			int degree = 0;
			for(int j = 0; j < graph.length; j++){
				
				if(graph[i][j]){
					degree++;
				}
				
			}
			degrees.add(degree);
		}
		
		
		return degrees;
	} 
	
	public static List<Integer> naiveBruteForce(boolean[][] graph){
		List<Integer> result = null;
		
		
		
		return result;
	}
	
	
	public static List<LinkedList<Integer>> combination(int n){
		List<LinkedList<Integer>> result = new LinkedList<LinkedList<Integer>>();
		if(n == 0){
			LinkedList<Integer> basecase = new LinkedList<Integer>();
			basecase.add(0);
			result.add(basecase);
			return result;
		}else{
			
			result = combination(n-1);
			
//			System.out.println("    generating combinations for: " + n);
			
			int size = result.size();
			LinkedList<Integer> newCombination;
			newCombination = new LinkedList<Integer>();
			newCombination.add(n);
			result.add(newCombination);
			for(int i = 0; i < size; i++){
//				System.out.println("    prograss: " + i + "/" + (size+1));
				newCombination = new LinkedList<Integer>(result.get(i));
				newCombination.add(n);
				result.add(newCombination);
			}
			
			return result;
			
		}
	}
	
	public static void sortLinkedList(List<LinkedList<Integer>> lists){
		Collections.sort(lists, new Comparator<LinkedList<Integer>>() {
	         @Override
	         public int compare(LinkedList<Integer> o1, LinkedList<Integer> o2) {
	             
	        	 if(o1.size() > o2.size()){
	        		 return -1;
	        	 } else if(o1.size() == o2.size()){
	        		 return 0;
	        	 } else if(o1.size() < o2.size()){
	        		 return 1;
	        	 }
				return 0;
	         }
	     });
	}
	
	public static void sortVertexsList(boolean[][] graph, ArrayList<Integer> nodes){
		Collections.sort(nodes, new Comparator<Integer>() {
	         @Override
	         public int compare(Integer o1, Integer o2) {
	        	 if(ToolBox.adjacentNodes(graph, nodes, o1).size()+1 > ToolBox.adjacentNodes(graph, nodes, o2).size()+1){
	        		 return -1;
	        	 } else if(ToolBox.adjacentNodes(graph, nodes, o1).size()+1 == ToolBox.adjacentNodes(graph, nodes, o2).size()+1){
	        		 return 0;
	        	 } else if(ToolBox.adjacentNodes(graph, nodes, o1).size()+1 < ToolBox.adjacentNodes(graph, nodes, o2).size()+1){
	        		 return 1;
	        	 }
				return 0;
	         }
	     });
	}
	
	/**
	 * 
	 * @param graph
	 * @throws IOException
	 */
	public static void writeGraph(boolean[][] graph) throws IOException{
		
		File file = new File("data/matrix");
		file.createNewFile();
		BufferedWriter fw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), false));
		
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph.length; j++){
				if(graph[i][j]){
					fw.write("0");
				}else{
					fw.write("1");
				}
			}
			fw.write("\n");
		}
		
		fw.close();
		
	}
	
	public static boolean[][] readGraph(String path){
		
		boolean[][] graph = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		    String line = br.readLine();
		    graph = new boolean[line.length()][line.length()];
		    for(int i = 0; i < line.length(); i++){
		    	if(line.charAt(i) == '0'){
		    		graph[0][i] = true;
		    	}else{
		    		graph[0][i] = false;
		    	}
		    	
		    }
		    int counter = 1; //counter for lines. 
		    while ((line = br.readLine()) != null) {
		    	for(int i = 0; i < line.length(); i++){
			    	if(line.charAt(i) == '0'){
			    		graph[counter][i] = true;
			    	}else{
			    		graph[counter][i] = false;
			    	}
			    }
		    	counter++;
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return graph;
	}
	
	public static boolean solutionCheck(List<Integer> solution, boolean[][] graph){
		
		
		
		boolean edges = false; //assume there is no edges
		List<Integer> combination = solution;
		//double loop
		for(int i = 0; i < combination.size() && !edges; i++){
			for(int j = i+1; j < combination.size() && !edges; j++){
				edges = graph[combination.get(i)][combination.get(j)];
			}
		}
		return !edges;
	}
	
	public static boolean cliqueSolutionCheck(List<Integer> solution, boolean[][] graph){
		// assumen there are edges, meaning that, the graph is assumed as complete graph. 
		boolean edges = true; 
		List<Integer> combination = solution;
		for(int i = 0; i < combination.size() && edges; i++){
			for(int j = i+1; j < combination.size() && edges; j++){
				edges = graph[combination.get(i)][combination.get(j)];
			}
		}
		
		
		return edges;
	}
	
	
	public static ArrayList<Integer> removeAdjacentNode(boolean[][] graph, ArrayList<Integer> nodes, int node){
//		System.out.println("removing adjavent...");
		ArrayList<Integer> removed = new ArrayList<Integer>();
		for(Integer i : nodes){
			if(graph[node][i] == false){
				removed.add(i);
			}
		}
		
		return removed;
	}
	
	public static ArrayList<Integer> adjacentNodes(boolean[][] graph, ArrayList<Integer> nodes, int node){
		
		ArrayList<Integer> adjacents = new ArrayList<Integer>();
		for(Integer i : nodes){
			if(graph[node][i] == true){
				adjacents.add(i);
			}
		}
		
		return adjacents;
		
		
	}
	
}




















