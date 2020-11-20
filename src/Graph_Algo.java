package ex0;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * This class has set of algorithms to execute on graph object:
 * 1. copy
 * 2. init
 * 3. isConnected();
 * 4. int shortestPathDist(int src, int dest);
 * 5. List<node_data> shortestPath(int src, int dest);
 *
 * @author david
 */
public class Graph_Algo implements graph_algorithms {

    private graph current_graph;

    /***
     * empty constructor
     */
    public Graph_Algo() {
    }

    /***
     * this constructor get a graph and init the graph, on this set of algorithms
     *
     * @param g the graph to init
     */
    public Graph_Algo(graph g) {
        init(g);
    }

    /**
     * Compute a deep copy of this graph.
     * this method uses a copy constructor of {@link Graph_DS}
     * by this the mode_counter will get the same value of current_graph
     *
     * @return copy graph
     */
    @Override
    public graph copy() {
        nullChecker();
        return (graph) new Graph_DS(this.current_graph);
    }

    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g the graph to init
     */
    @Override
    public void init(graph g) {
        this.current_graph = g;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVERY node to each
     * other node. NOTE: assume ubdirectional graph.
     * this method uses BFS algorithms that return the number of the nodes painted
     * if BFS succeeded to painted all the nodes in the graph - the graph connected
     * else - not connected
     *
     * @return true - for connected graph, false - for not connected graph
     */
    @Override
    public boolean isConnected() {
        nullChecker();
        Iterator<node_data> it = this.current_graph.getV().iterator();

        if (!it.hasNext())
            return true;
        node_data n = it.next();
        return BFS(n, n) == current_graph.nodeSize();
    }

    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * if src == dest --> return 0
     * this method uses shortestPath that returns list with the shortest path
     * The size of the list minus 1 is the length of the path.
     * if the list are null - no such path
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return the length (how many edges) of the shortest path between src to dest
     */
    @Override
    public int shortestPathDist(int src, int dest) {
        nullChecker();
        List<node_data> l = shortestPath(src, dest);
        if (l == null)
            return -1;
        return l.size() - 1;
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note: if no such path --> returns null
     * This method calls BFS in order to get the labeling of the vertices.
     * Starts from the end node and advances each time to a neighboring node
     * with a low tag in one of the current tags.
     * When the 0 tag arrives, we have reached the start.
     * If no such node returns null.
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return list of the shortest path between src to dest
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        nullChecker();
        node_data start = this.current_graph.getNode(src);
        node_data end = this.current_graph.getNode(dest);

        if (start == null || end == null)
            return null;

//        init list to save the path, and push end node
        LinkedList<node_data> path = new LinkedList<>();
        path.push(end);

//        execute BFS
        BFS(start, end);

//        start from end node
        int checkTag = end.getTag();
        node_data current = end;

//        while did not get to start node find the next node neighbor with tag equal current tag - 1
        while (checkTag != 0) {
            Iterator<node_data> it = current.getNi().iterator();
            int oldTag = checkTag;
            while (it.hasNext()) {
                node_data candi = it.next();
                if (candi.getTag() == current.getTag() - 1) {
                    current = candi;
                    checkTag--;
                    path.push(current);
                    break;
                }
            }
            if (checkTag == oldTag)
                return null;
            if (current == start)
                return path;
        }
        return path;
    }

    ////////////////////// Private Functions /////////////////////

    /***
     * BFS algorithm
     * The BFS algorithm goes through all the vertices of the graph,
     * starting from src and stops when it reaches the dest.
     * The algorithm starts with the src vertex in a queue.
     * Then, as long as the queue is not empty the first vertex comes out,
     * and all its neighbors go in, and get a tag with one value more than the vertex src.
     *
     * @param src node data to start the algorithm with
     * @param dest node data to stop the algorithm with
     * @return Number of nodes in the graph that painted
     */
    private int BFS(node_data src, node_data dest) {
//        set all the nods to -1, and node to 0
        setTagsToNeg1();
        src.setTag(0);

//        init counter and queue for the neighbors of node
        int counter = 0;
        Queue<node_data> queue = new LinkedList<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            node_data current = queue.poll();
            counter++;

//            for all the neighbors has not visited: add to the queue and paint
            for (node_data i : current.getNi()) {
                if (i.getTag() == -1) {
                    queue.add(i);
                    i.setTag(current.getTag() + 1);
                    if (i == dest)
                        return counter;
                }
            }
        }
        return counter;
    }

    /***
     * Initialize all the tags of the vertices in the graph to -1
     */
    private void setTagsToNeg1() {
        for (node_data i : this.current_graph.getV())
            i.setTag(-1);
    }

    /***
     * method to check if the current_graph was initialized
     * if not - there no graph to work with so throw {@link NullPointerException}.
     * used in every methode works on current_graph
     *
     * @throws NullPointerException No graph was initialized
     */
    private void nullChecker() throws NullPointerException {
        if (null == this.current_graph)
            throw new NullPointerException("No graph was initialized");
    }
}