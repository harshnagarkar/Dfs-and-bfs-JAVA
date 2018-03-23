import java.util.ArrayList;

public class Dfs {
	// adjcancy matrix
	ArrayList<ArrayList<Integer>> graph;

	//matrix
	ArrayList<ArrayList<Integer>> treeedge;
	ArrayList<ArrayList<Integer>> backedge;

	//vertexs
	ArrayList<Vertex> vertexs;

	//counters
	int components = 0;
	int count ;
	int pcount ;

	//Constructor
	public Dfs() {
		treeedge = new ArrayList<ArrayList<Integer>>();
		backedge = new ArrayList<ArrayList<Integer>>();
		count = 0;
		pcount = 0;
		//creates empty tree edge and backedge matrix
		creatempty();
	}

	public Dfs(ArrayList<ArrayList<Integer>> Graph, ArrayList<Vertex> vertx) {
		graph = Graph;
		vertexs = vertx;
		count = 0;
		pcount = 0;
		treeedge = new ArrayList<ArrayList<Integer>>();
		backedge = new ArrayList<ArrayList<Integer>>();
		creatempty();
	}

	//starting dfs
	public void dfscheck() {
		for (Vertex v : vertexs) {
			if (v.isVisited() == false) {
				dfs(v);
				components++;
			}
		}
	}

//recursice dfs
	public void dfs(Vertex v) {
//setting visited
		v.setVisited(true);
		count++;
		v.setOrder(count);

//getting adjacent
		ArrayList<Integer> adjacent = adjacent(v);
		int temp = v.getNumber();
//doing dfs for adjacents
		for (int va : adjacent) {
			Vertex ver = vertexs.get(va);
			if(ver.isVisited()==false){
				dfs(ver);
				treeedge.get(v.getNumber()).set(ver.getNumber(), 1);
			} 
//marking it as a backedge only if it is visited, not tree edge to parent not previously marled backedge in reverse order of vertices and parent order less than child marked		
			else if(ver.isVisited()==true && treeedge.get(v.getNumber()).get(ver.getNumber())!=1 && backedge.get(ver.getNumber()).get(v.getNumber())==0 && v.getOrder()<ver.getOrder()) {
				backedge.get(ver.getNumber()).set(v.getNumber(), 1);
			}
		}
//setting pop count
		pcount++;
		v.setPopc(pcount);
	}

//method to get adjcant of a vertex without itself
	public ArrayList<Integer> adjacent(Vertex v){
		ArrayList<Integer> checklist = graph.get(v.getNumber());
		ArrayList<Integer> adjacent = new ArrayList<Integer>();
		int i = 0;
		while (i < checklist.size()) {
			int j = checklist.get(i);
			if (j == 1 && i!=v.getNumber()) {
				adjacent.add(i);

			}
			i++;
		}
		return adjacent;
	}
	
//method to create a empty matrix for tree and backedge
	public void creatempty(){
		for(int i = 0;i<graph.size();i++){
			treeedge.add(new ArrayList<Integer>());
			backedge.add(new ArrayList<Integer>());
			for(int j = 0;j<graph.size();j++){
				treeedge.get(i).add(0);
				backedge.get(i).add(0);
			}
		}
	}

//printing the data
	public void printtrees(){
		System.out.print("First encountered: ");
		for(int i =0;i<vertexs.size();i++){
			System.out.print(vertexs.get(i).getOrder()+" ");	
		}

		System.out.print("\nFirst dead ends: ");
		for(int i =0;i<vertexs.size();i++){
			System.out.print(vertexs.get(i).getPopc()+" ");
		}

		System.out.println("\nNo. of components is: "+components);
		System.out.println();
		System.out.println("Printing tree edges");
		for(int i = 0;i<graph.size();i++){
			for(int j = 0;j<graph.size();j++){
				System.out.print(treeedge.get(i).get(j)+" ");
			}
			System.out.println();
		}
		System.out.println("\nPrinting backedges edges");
		for(int i = 0;i<graph.size();i++){
			for(int j = 0;j<graph.size();j++){
				System.out.print(backedge.get(i).get(j)+" ");
			}
			System.out.println();
		}
	}
}

