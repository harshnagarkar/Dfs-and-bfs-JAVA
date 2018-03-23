//Name: Harsh Nagarkar

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList matrix = creatematrix("sample1.txt");
		ArrayList<Vertex> Vlist = new ArrayList<Vertex>();
		Vlist = emptyList(matrix);
		
		Dfs dfssearch = new Dfs(matrix,Vlist);
		dfssearch.dfscheck();
		dfssearch.printtrees();
		
		ArrayList<Vertex> Vlist2 = new ArrayList<Vertex>();
		Vlist2 = emptyList(matrix);
		Bfs bfssearch = new Bfs(matrix,Vlist2);
		bfssearch.bfscheck();
		bfssearch.printtrees();
	}
	
	public static ArrayList creatematrix(String name) throws FileNotFoundException{
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		int k = 0;
		Scanner scan = new Scanner(new File(name));
		while(scan.hasNextLine()){
			String temp = scan.nextLine();
			String[] splits;
			splits = temp.split(" ");
			matrix.add(new ArrayList<Integer>());
			for(int i =0;i<splits.length;i++){
				matrix.get(k).add(Integer.parseInt(splits[i]));
			}
			k++;
		}
		return matrix;
	}
	
	public static ArrayList emptyList(ArrayList m){
		ArrayList<Vertex> Vlist = new ArrayList<Vertex>();
		for(int i = 0; i<m.size();i++){
			Vlist.add(new Vertex(i,false));
		}
		return Vlist;
	}
	
}