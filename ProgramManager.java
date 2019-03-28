import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class ProgramManager {
	
	static ShortestPathComparator comparator = new ShortestPathComparator();							/*That function produces an object to help priority queue to comparate*/
	static PriorityQueue<ShortestPath> minHeap = new PriorityQueue<ShortestPath>(1000, comparator);		
	static ArrayList<ShortestPath> shortestPaths = new ArrayList<ShortestPath>();						/*That function produces an object to hold the shortest paths*/
	
	public static void programManager(File outputFile){
		
		int starterID=0, destinationID, K=0;
		
		PrintWriter writer = null;
		
		try{
			writer = new PrintWriter(outputFile);

			String token1[] = ioFile.inputList.get(0).split("[ :,]+");  /*This code gets the fisrt line of input*/	
			
			starterID =Integer.parseInt(token1[1]);			/*This code assigns the starter vertex id*/
			destinationID = Integer.parseInt(token1[3]);	/*This code assigns the destination vertex id*/
			if(token1.length > 4){
				K = Integer.parseInt(token1[5]);
			}
					
			/*That loop sends lines of input to the function*/
			for(int i=1 ; i<ioFile.inputList.size(); i++){
				settingVertexes(ioFile.inputList.get(i));;
			}
			
			/*That loop sends lines of input to the function*/
			for(int i=1 ; i<ioFile.inputList.size(); i++){
				settingEdges(ioFile.inputList.get(i));
			}

			kShortestPath(starterID, destinationID, K, writer);
			
			/****That function prints the shortest paths' data****/
			for(int j=0; j<shortestPaths.size();j++ ){
				writer.print(j+1 + ".  Shortest Path:  ");
				writer.printf("%.9f", shortestPaths.get(j).getTotalDistance());
				writer.print("    ");
				writer.print(starterID + "   ");
				for(int i=0; i<shortestPaths.get(j).getPathList().size() ; i++){
					writer.print( shortestPaths.get(j).getPathList().get(i).getTargetVertex().getId() + "  " );
				}
				writer.println();
			}
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}
	
	
		 	
	public static void kShortestPath(int starterID,int destinationID,int K,PrintWriter writer){
		Vertex starterVertex = null;
		for(Vertex x : Vertex.allVertices){		/*Finding starter vertex*/
			if(x.getId() == starterID){
				starterVertex = x;
			}
		}
		/****Producing an object to get first path of priority queue****/
		ShortestPath path = new ShortestPath(0);	
		
		/****Producing new paths which are produced from starter vertex's edges****/
		if(!(starterVertex.getTargetEdges().isEmpty())){
			for(int i=0; i<starterVertex.getTargetEdges().size();i++){
				addingMinHeap(path,starterVertex, i);
			}
		}
		
		/****Iterative function that runs till priority queue is empty****/
		while(!(minHeap.isEmpty())){
			ShortestPath temp;
			temp = minHeap.poll();						/****Removing and returning from priority queue****/

			/****That function checks if the program finded k shortest paths. If so, no more adding that path****/
			if(temp.lastEdge().getTargetVertex().getId() == destinationID){
				shortestPaths.add(temp);
				if(shortestPaths.size() == K){
					break;
				}
			}
			
			if(temp.lastEdge().getTargetVertex().getId() != destinationID){
				
				/****That function controls the cycle that would occur while program is running****/
				for(int i=0; i<temp.lastEdge().getTargetVertex().getTargetEdges().size();i++){			/****Checks target edges of the last edge's target vertex****/
					boolean cycleControl = false;
					
					for(int j = 0; j<temp.getPathList().size(); j++)									/****Checks all edges of the path to find a similarity with target vertex****/
					{
						if(temp.getPathList().get(j).getTargetVertex().getId() == temp.lastEdge().getTargetVertex().getTargetEdges().get(i).getTargetVertex().getId()){
							cycleControl = true; /*One of edges' target vertex of Path's last vertex is equal to one of path's vertexes*/
						}
					}
					if(cycleControl == false){
						addingMinHeap(temp, temp.lastEdge().getTargetVertex(), i);
					}
				}
			}
			
		}
		
	}
	
	
	public static void addingMinHeap(ShortestPath path,Vertex currentVertex, int index){
		ShortestPath temp = new ShortestPath();
		
		/****To transfer all data of path to temp object****/
		for (Edge edge : path.getPathList()) {
			temp.getPathList().add(edge);
		}
		
		temp.setTotalDistance(path.getTotalDistance());
		
		temp.getPathList().add(currentVertex.getTargetEdges().get(index));
		temp.setTotalDistance(currentVertex.getTargetEdges().get(index).getDistance() + temp.getTotalDistance());		/****Adding the current vertex's distance with ex total distance to find new total distance****/
		/****Adding the new path to the priority queue****/
		minHeap.add(temp);
		
	}
	
	

	public static void settingEdges(String line){
		String token1[] = line.split("[ :,()]+");
		double distance;
		int targetVertexID;
		int currentVertexID;
		
		StringBuilder sb = new StringBuilder(token1[0]);					/*That function removes "." from string*/
		sb.deleteCharAt((token1[0].length())-1);
		token1[0]=sb.toString();
		
		currentVertexID = Integer.parseInt(token1[0]);						
		
		/****Inititating new edge****/
		for(int i = 1; i < ((token1.length)+1)/2; i++){
			targetVertexID = Integer.parseInt(token1[2*i-1]);
			distance = Double.parseDouble(token1[2*i]);
			if(targetVertexID == Vertex.allVertices.get(currentVertexID-1).getId()){
				continue;
			}else if(targetVertexID == 0){
				System.out.println("Kose yok!");
				continue;
			}else{
				Edge E = new Edge(distance, Vertex.allVertices.get(targetVertexID-1)); 
				Vertex.allVertices.get(currentVertexID-1).getTargetEdges().add(E);		/*Adding the edge to the current vertex's Edges list that link to the target vertex*/	
			}
			
		}
	

	}
	
	/****Inititating new vertex****/
	public static void settingVertexes(String line){
		String token1[] = line.split("[ :,()]+");
		
			StringBuilder sb = new StringBuilder(token1[0]);		/*That function removes "." from string*/
			sb.deleteCharAt((token1[0].length())-1);
			token1[0]=sb.toString();
			
			setVertexNodes(Integer.parseInt(token1[0]));
		
	}
	
	public static void setVertexNodes(int id){
		Vertex node = new Vertex();
		node.setId(id);
		;
		Vertex.allVertices.add(node);
	}
	
	

}
