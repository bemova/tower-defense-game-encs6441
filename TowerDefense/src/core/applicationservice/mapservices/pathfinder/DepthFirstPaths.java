package core.applicationservice.mapservices.pathfinder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import core.domain.waves.Position;


public class DepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex

    /**
     * Computes a path between <tt>s</tt> and every other vertex in graph <tt>G</tt>.
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a path between the source vertex <tt>s</tt> and vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a path, <tt>false</tt> otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Returns a path between the source vertex <tt>s</tt> and vertex <tt>v</tt>, or
     * <tt>null</tt> if no such path.
     * @param v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *   <tt>s</tt> and vertex <tt>v</tt>, as an Iterable
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    /**
     * Unit tests the <tt>DepthFirstPaths</tt> data type.
     */
    public static Position[] getPath(Map<Position,Integer> nodes, int start, int end) {
    	In in = new In("S.txt");
        Graph G = new Graph(in);
        DepthFirstPaths dfs = new DepthFirstPaths(G, start);
        Map<Position,Integer> map = nodes;
        LinkedList<Position> route = new LinkedList<>();
            if (dfs.hasPathTo(end)) {
                for (int x : dfs.pathTo(end)) {
                    Iterator<Entry<Position, Integer>> iteratotr = map.entrySet().iterator();
                    while (iteratotr.hasNext()) {
            			Entry<Position,Integer> obj = (Entry<Position,Integer>) iteratotr.next();
            			if(x == obj.getValue()){
            				route.add(obj.getKey());
            				break;
            			}
            		}
                }
            }
            Position[]  array = new Position[route.size()];
            int i = route.size();
            for (Position r : route) {
				array[i - 1] = r;
				i --;
			}
            return array;
    }
}