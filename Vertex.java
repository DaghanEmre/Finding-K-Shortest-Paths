import java.util.ArrayList;

public class Vertex {
	
	/*To hold all vertices in one arraylist*/
	static ArrayList<Vertex> allVertices = new ArrayList<Vertex>();

	private int id;
	private ArrayList<Edge> targetEdges = new ArrayList<Edge>();

	/****Default Constructer****/
	public Vertex() {
		super();
	}
	
	/****Constructer****/
	public Vertex(int id, ArrayList<Edge> targetEdges) {
		super();
		this.id = id;
		this.targetEdges = targetEdges;
	}

	/****Getter - Setters****/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Edge> getTargetEdges() {
		return targetEdges;
	}

}
