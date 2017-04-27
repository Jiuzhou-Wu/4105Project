import java.util.ArrayList;
import java.util.Scanner;

public class IndependentSet {
	public static ArrayList<Integer> MIS(boolean[][] graph, ArrayList<Integer> nodes, int considering){
		Scanner myScanner = new Scanner(System.in);
		
		
		if(ToolBox.solutionCheck(nodes, graph)){
			return nodes;
		}
		
		ArrayList<Integer> nodesCopy = new ArrayList<Integer>();
		
		for(int i = 0; i<nodes.size(); i++){
			if(i!=considering)
			nodesCopy.add(nodes.get(i));
		}
		
		ArrayList<Integer> adjacentRemoved = ToolBox.removeAdjacentNode(graph, nodes, nodes.get(considering));
		
		
		ArrayList<Integer> includeCurrent = MIS(graph, adjacentRemoved, ++considering);
//		includeCurrent.add(nodes.get(0));
		
		ArrayList<Integer> excludeCurrent = MIS(graph, nodesCopy, 0);
//		System.out.println("the node list is: "  +  nodes.toString() + "; the considering is: " + nodes.get(--considering));
//		System.out.println("adjavent nodes removed: " + adjacentRemoved);
//		System.out.println("includeCurrent: " + includeCurrent.toString() + "; excludeCurrent: " + excludeCurrent.toString());
//		myScanner.nextLine();
		if(excludeCurrent.size() > includeCurrent.size()){
			return excludeCurrent;
		} else{
			return includeCurrent;
		}
		
		
	}
}
