public class Edge {

	private double distance;
	private Vertex targetVertex;

	/****Default Constructer****/
	public Edge() {
		super();
	}

	/****Constructer****/
	public Edge(double distance, Vertex targetVertex) {
		super();
		this.distance = distance;
		this.targetVertex = targetVertex;
	}

	/****Getter - Setters****/
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Vertex getTargetVertex() {
		return targetVertex;
	}

	public void setTargetVertex(Vertex targetVertex) {
		this.targetVertex = targetVertex;
	}

}
