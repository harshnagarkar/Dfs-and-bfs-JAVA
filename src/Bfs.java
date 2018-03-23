import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs {

//matrix
	ArrayList<ArrayList<Integer>> graph;
	
//empty treeedge and backedge matrix
	ArrayList<ArrayList<Integer>> treeedge;
	ArrayList<ArrayList<Integer>> crossedge;
	
//queue for bfs
	Queue<Vertex> q = new LinkedList<>();
	
//vertexs arraylist
	ArrayList<Vertex> vertexs;

//initializing counter
	int components;
	int count ;
	int pcount ;
	
//Constructor
	public Bfs() {
		count = 0;
		pcount = 0;
		components = 0;
		treeedge = new ArrayList<ArrayList<Integer>>();
		crossedge = new ArrayList<ArrayList<Integer>>();
//creates empty treeedge and backedge matrix
		creatempty();
	}

	public Bfs(ArrayList<ArrayList<Integer>> Graph, ArrayList<Vertex> vertx) {
		graph = Graph;
		vertexs = vertx;
		count = 0;
		pcount = 0;
		components = 0;
		treeedge = new ArrayList<ArrayList<Integer>>();
		crossedge = new ArrayList<ArrayList<Integer>>();
//creates empty treeedge and backedge matrix
		creatempty();
	}

//getter setter
	public int getComponents() {
		return components;
	}

	public void setComponents(int components) {
		this.components = components;
	}

//bfs initializer
	public void bfscheck() {
		for (Vertex v : vertexs) {
			if (v.isVisited() == false) { 
				bfs(v);
				components++;
			}
		}
	}
	
//bfs non recurssive
	public void bfs(Vertex v){
		
//marking visited
		count++;
	    v.setVisited(true);
	    v.setOrder(count);
//adding to queue
	    q.add(v);
	    while(q.isEmpty()==false){
//getting adjacent
	    	ArrayList<Integer>adjacent = adjacent(q.peek());
	    	for (int va : adjacent) {
	    		Vertex ver = vertexs.get(va);
	    		if(ver.isVisited()==false){
//marking visited
	    			count++;
	    			ver.setVisited(true);
	    			ver.setOrder(count);
//adding to queue to find adjcent
	    			q.add(ver);
//marking treeedge
	    			treeedge.get(q.peek().getNumber()).set(ver.getNumber(), 1);
	    		}
//marking a cross edge only if vertex is visited, not a ancestor for marking and crossedge for it before does not exsist in reverse order
	    		else if(ver.isVisited()==true && treeedge.get(ver.getNumber()).get(q.peek().getNumber())!=1 && crossedge.get(ver.getNumber()).get(q.peek().getNumber())==0){
	    			crossedge.get(q.peek().getNumber()).set(ver.getNumber(), 1);
	    		}
	    	}
//poping vertex of queue 
	    	Vertex end = q.poll();
	    	pcount++;
	    	end.setPopc(pcount);
	    }
		
	}
	
//creates empty tree edge and backedge matrix
	public void creatempty(){
		for(int i = 0;i<graph.size();i++){
			treeedge.add(new ArrayList<Integer>());
			crossedge.add(new ArrayList<Integer>());
			for(int j = 0;j<graph.size();j++){
				treeedge.get(i).add(0);
				crossedge.get(i).add(0);
			}
		}
	}

//method finds adjacent
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
	
//prints the data
	public void printtrees(){
		System.out.println("\n ----------------------------------------------");
		System.out.print("First encountered: ");
		for(int i =0;i<vertexs.size();i++){
			System.out.print(vertexs.get(i).getOrder()+" ");	
			}
		
		System.out.println("\nNo. of components is: "+components+"\n");
		System.out.println("Printing tree edges");
		for(int i = 0;i<graph.size();i++){
			for(int j = 0;j<graph.size();j++){
				System.out.print(treeedge.get(i).get(j)+" ");
			}
			System.out.println();
		}
		System.out.println("\nPrinting crossedges edges");
		for(int i = 0;i<graph.size();i++){
			for(int j = 0;j<graph.size();j++){
				System.out.print(crossedge.get(i).get(j)+" ");
			}
			System.out.println();
		}
	}


}
