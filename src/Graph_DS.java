package ex0;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/***
 * This class called Graph_DS represent unadjusted graph
 * graph has {@link HashMap} contains all his nodes
 * the edges of the graph are represented by node's neighbors
 * the key in the HashMap is {@link Integer} and he represent the {@link NodeData} key
 * also has edge_size counter, and mode_count to count changes ao the graph
 *
 * @author david
 */
public class Graph_DS implements graph {

    private HashMap<Integer, node_data> nodes;
    private int edge_size;
    private int mode_count;

    /***
     * empty contractor for {@link Graph_DS}
     * init the variables
     */
    public Graph_DS() {
        nodes = new HashMap<>();
        edge_size = 0;
        mode_count = 0;
    }

    /***
     * Copy contractor for {@link Graph_DS}
     * Compute a deep copy of this graph
     * copy all the data of this graph to anther
     * save on the same nodes keys, but anther objects
     *
     * @param g graph to copy
     */
    public Graph_DS(graph g) {
        nodes = new HashMap<>();
        for (node_data i : g.getV()) {
            this.addNode(new NodeData(i));
            for (node_data j : i.getNi()) {
                connect(i.getKey(), j.getKey());
            }
        }
        this.mode_count = g.getMC();
    }

    /**
     * return the node_data by the node_id,
     *
     * @param key the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        return this.nodes.get(key);
    }

    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     *
     * @param node1 key represent node in the graph
     * @param node2 key represent node in the graph
     * @return true iff node1 and node2 are represent neighbors nodes, else false
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        node_data n1 = getNode(node1);
        node_data n2 = getNode(node2);
        if (null == n1 || null == n2)
            return false;
        return n1.hasNi(node2) && n2.hasNi(node1);
    }

    /**
     * add a new node to the graph with the given node_data.
     * Note: this method should run in O(1) time.
     * if node are already in the graph - the method dose nothing.
     *
     * @param n node to add
     */
    @Override
    public void addNode(node_data n) {
        if (this.getNode(n.getKey()) != null)
            return;
        if (this.nodes.put(n.getKey(), n) == null)
            mode_count++;
    }

    /**
     * Connect an edge between node1 and node2.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply does nothing.
     *
     * @param node1 key represent node in the graph
     * @param node2 key represent node in the graph
     */
    @Override
    public void connect(int node1, int node2) {
        if (this.hasEdge(node1, node2) || node1 == node2)
            return;
        node_data n1 = getNode(node1);
        node_data n2 = getNode(node2);
        if (null == n1 || null == n2)
            return;
        n1.addNi(n2);
        n2.addNi(n1);
        edge_size++;
        mode_count++;
    }

    /**
     * This method return a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return Collection<node_data> of the nodes in the graph
     */
    @Override
    public Collection<node_data> getV() {
        return this.nodes.values();
    }

    /**
     * This method returns a collection containing all the
     * nodes connected to node_id
     * Note: this method should run in O(1) time.
     *
     * @param node_id key represent node in the graph
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        node_data n = getNode(node_id);
        if (null == n)
            return null;
        return n.getNi();
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     *
     * @param key key represent node in the graph
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_data removeNode(int key) {
        node_data n = getNode(key);
        if (null == n)
            return null;

        LinkedList<node_data> nNi = new LinkedList<>(n.getNi());

        for (node_data i : nNi) {
            i.removeNode(n);
            n.removeNode(i);
            edge_size--;
            mode_count++;
        }
        this.nodes.remove(key, n);
        return n;
    }

    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param node1 key represent node in the graph
     * @param node2 key represent node in the graph
     */
    @Override
    public void removeEdge(int node1, int node2) {
        node_data n1 = getNode(node1);
        node_data n2 = getNode(node2);
        if (null == n1 || null == n2 || !n1.hasNi(node2))
            return;

        n1.removeNode(n2);
        n2.removeNode(n1);
        edge_size--;
        mode_count++;
    }

    /**
     * return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return number of vertices
     */
    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     *
     * @return number of edge
     */
    @Override
    public int edgeSize() {
        return this.edge_size;
    }

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     *
     * @return mode_count
     */
    @Override
    public int getMC() {
        return this.mode_count;
    }

    @Override
    public String toString() {
        String niStr = "";
        for (node_data i : nodes.values())
            niStr += i.getKey() + ": " + i.getNi() + " | ";

        return "Graph_DS: " + "ES = " + edge_size + ", NS = " + nodeSize() + ", MC = " + mode_count + "\n" +
                "\tnodes: " + nodes +
                "\n\tneighbors: " + niStr + "\n";
    }
}
