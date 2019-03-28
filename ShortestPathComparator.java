import java.util.Comparator;

public class ShortestPathComparator implements Comparator<ShortestPath> {

	@Override
	public int compare(ShortestPath p1, ShortestPath p2) {
		if (p1.getTotalDistance() < p2.getTotalDistance())
			return -1;
		if (p1.getTotalDistance() > p2.getTotalDistance())
			return 1;
		return 0;
	}
}