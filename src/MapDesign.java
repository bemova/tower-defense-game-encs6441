import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class MapDesign {

	HashMap<String, GraphNode> GraphMap = new HashMap<String, GraphNode>();

	void buildMap(int sizeX, int sizeY) {

		for (int i = 0; i <= sizeX - 1; i++) {
			for (int j = 0; j <= sizeY - 1; j++) {

				String curentNode = Integer.toString(i) + Integer.toString(j);
				if (GraphMap.get(curentNode) == null)
					GraphMap.put(curentNode, new GraphNode(curentNode, i, j));

				GraphNode universalGrafNode;
				if (i - 1 >= 0) {
					if ((universalGrafNode = GraphMap.get(Integer
							.toString(i - 1) + Integer.toString(j))) != null) {
						GraphMap.get(curentNode).neighbors
								.add(universalGrafNode);

					} else {

						universalGrafNode = new GraphNode(
								Integer.toString(i - 1) + Integer.toString(j), i-1, j);
						GraphMap.put(universalGrafNode.name, universalGrafNode);
						GraphMap.get(curentNode).neighbors
								.add(universalGrafNode);
					}
				}

				if (j - 1 >= 0) {
					if ((universalGrafNode = GraphMap.get(Integer.toString(i)
							+ Integer.toString(j - 1))) != null) {
						GraphMap.get(curentNode).neighbors
								.add(universalGrafNode);
					} else {
						universalGrafNode = new GraphNode(Integer.toString(i)
								+ Integer.toString(j - 1), i, j-1);
						GraphMap.put(universalGrafNode.name, universalGrafNode);
						GraphMap.get(curentNode).neighbors
								.add(universalGrafNode);
					}

				}

				if (i + 1 < sizeX) {
					if ((universalGrafNode = GraphMap.get(Integer
							.toString(i + 1) + Integer.toString(j))) != null) {
						GraphMap.get(curentNode).neighbors
								.add(universalGrafNode);

					} else {

						universalGrafNode = new GraphNode(
								Integer.toString(i + 1) + Integer.toString(j), i+1, j);
						GraphMap.put(universalGrafNode.name, universalGrafNode);
						GraphMap.get(curentNode).neighbors
								.add(universalGrafNode);
					}

				}

				if (j + 1 < sizeY) {

					if ((universalGrafNode = GraphMap.get(Integer
							.toString(i) + Integer.toString(j+1))) != null) {
						GraphMap.get(curentNode).neighbors
								.add(universalGrafNode);

					} else {

						universalGrafNode = new GraphNode(Integer.toString(i)
								+ Integer.toString(j +1), i, j+1);
						GraphMap.put(universalGrafNode.name, universalGrafNode);
						GraphMap.get(curentNode).neighbors
								.add(universalGrafNode);
					}

				}

			}
		}

	}
}
