
public class Vertex {
//assigned vertex number
	int number;

	boolean visited;
//encountered vertex order
	int order;
//popping vertex order
	int popc =0;
	public int getPopc() {
		return popc;
	}
//getter and setter for variable
	public void setPopc(int popc) {
		this.popc = popc;
	}
	public Vertex(){
		number = 0;
		visited =  false;
		order = 0;
	}
	public Vertex(int n,boolean visit){
		number = n;
		visited = visit;
		order = 0;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
