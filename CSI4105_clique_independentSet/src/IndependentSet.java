import java.util.ArrayList;

public class IndependentSet {
	public static ArrayList<Integer> MIS(boolean[][] graph, ArrayList<Integer> nodes, int considering){
//		System.out.println(nodes.toString());
//		Scanner myScanner = new Scanner(System.in);
//		System.out.println(nodes.toString());
//		myScanner.nextLine();
		if(ToolBox.solutionCheck(nodes, graph)){
			return nodes;
		}
		
		ArrayList<Integer> nodesCopy = new ArrayList<Integer>();
		
		for(int i = 1; i<nodes.size(); i++){
			nodesCopy.add(nodes.get(i));
		}
		
		ArrayList<Integer> adjacentRemoved = ToolBox.removeAdjacentNode(graph, nodes, nodes.get(considering));
		
		ArrayList<Integer> includeCurrent = MIS(graph, adjacentRemoved, ++considering);
//		includeCurrent.add(nodes.get(0));
		
		ArrayList<Integer> excludeCurrent = MIS(graph, nodesCopy, 0);
		
		if(excludeCurrent.size() > includeCurrent.size()){
			return excludeCurrent;
		} else{
			return includeCurrent;
		}
		
		
	}
}
