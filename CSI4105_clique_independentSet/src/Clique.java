import java.util.ArrayList;
import java.util.Scanner;

public class Clique {
	
	public static ArrayList<Integer> MCG(boolean[][] graph, ArrayList<Integer> nodes, int considering){
//		System.out.println(nodes.toString());
//		Scanner myScanner = new Scanner(System.in);
//		System.out.println("the graph now contains nodes: " + nodes.toString());
		
		if(ToolBox.cliqueSolutionCheck(nodes, graph)){
			return nodes;
		}
		
		ArrayList<Integer> maximal = new ArrayList<Integer>();
		
		for(int i = 0; i < nodes.size() && ToolBox.adjacentNodes(graph, nodes, nodes.get(i)).size()+1 > maximal.size() ; i++){
			ArrayList<Integer> newMaximum = MCG(graph, ToolBox.adjacentNodes(graph, nodes, nodes.get(i)), i);
			newMaximum.add(nodes.get(i));
//			System.out.println("new solution found: " + newMaximum.toString());
//			myScanner.nextLine();
			if(newMaximum.size() > maximal.size()){
				maximal = newMaximum;
			}
			
		}
		
		return maximal;
	}	
	
}
