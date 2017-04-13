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
			
			System.out.println("    generating combinations for: " + n);
			
			int size = result.size();
			LinkedList<Integer> newCombination;
			newCombination = new LinkedList<Integer>();
			newCombination.add(n);
			result.add(newCombination);
			for(int i = 0; i < size; i++){
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
}
