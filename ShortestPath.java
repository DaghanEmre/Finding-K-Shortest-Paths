import java.util.ArrayList;

public class ShortestPath {

	/****To hold all edges of path****/
	private ArrayList<Edge> pathList = new ArrayList<Edge>();
	private double totalDistance;

	public ShortestPath() {
	}

	public ShortestPath(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public ArrayList<Edge> getPathList() {
		return pathList;
	}

	public double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(double totalDistance) {
		this.totalDistance = totalDistance;
	}

	/****Returning last edge of path list****/
	public Edge lastEdge() {
		return pathList.get(pathList.size() - 1);
	}
}
