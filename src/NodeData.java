
import java.util.Collection;
import java.util.HashMap;

/**
 * This class called NodeData represent node in unadjusted graph
 * evey node get unique id, called key
 * evey node has HashMap to save the neighbors of the node
 * the key in the HashMap is {@link Integer} and he represent the {@link NodeData} key
 * also has info and tag variables for development goals
 *
 * @author david
 */
public class NodeData implements node_data {

    private static int counter = 0;
    private final int key;
    private HashMap<Integer, node_data> neighbors;
    private String info;
    private int tag;


    /***
     * empty contractor for {@link NodeData}
     * init the variables
     */
    public NodeData() {
        this.key = counter++;
        neighbors = new HashMap<>();
        info = "";
        tag = -1;
    }

    /***
     * Copy contractor of {@link Graph_DS}
     * make deep copy of NodeData, and create a new NodeData object with the same data
     * the node will copy without his neighbors
     * (his neighbors will connect in {@link NodeData} copy contractor
     *
     * @param n node_data for copying
     */
    public NodeData(node_data n) {
        this.key = n.getKey();
        this.neighbors = new HashMap<>();
        this.info = n.getInfo();
        this.tag = n.getTag();
    }

    /**
     * Return the key (id) associated with this node.
     * Note: each node_data should have a unique key.
     *
     * @return unique id key
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * This method returns a collection with all the Neighbor nodes of this node_data
     *
     * @return all the values from the HashMap contains all the Neighbor nodes of this node_data
     */
    @Override
    public Collection<node_data> getNi() {
        return this.neighbors.values();
    }

    /**
     * return true iff this<==>key are adjacent, as an edge between them.
     *
     * @param key represent key of some node in the graph
     * @return true if this ans key are adjacent, else - false
     */
    @Override
    public boolean hasNi(int key) {
        return this.neighbors.containsKey(key);
    }

    /**
     * This method adds the node_data (t) to this node_data.
     * This method is wrongly designed! and was used mainly for educational example - to be improved in Ex1
     * if this and key are already neighbors the method dose nothing.
     *
     * @param t node data object to add
     * @throw {@link RuntimeException} if the argument is null
     */
    @Override
    public void addNi(node_data t) {
        if (null == t)
            throw new RuntimeException("The argument is null");
        if (this.neighbors.containsKey(t.getKey()))
            return;
        this.neighbors.put(t.getKey(), t);
    }

    /**
     * Removes the edge this-node, from the neighbors HashMap
     * if node are already removed - the method dose nothing.
     *
     * @param node node_data to remove
     * @throw {@link RuntimeException} if the argument is null
     */
    @Override
    public void removeNode(node_data node) {
        if (null == node)
            throw new RuntimeException("The argument is null");
        this.neighbors.remove(node.getKey(), node);
    }

    /**
     * return the remark (meta data) associated with this node.
     *
     * @return info
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     *
     * @param s {@link String} to set into info
     */
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     *
     * @return tag
     */
    @Override
    public int getTag() {
        return this.tag;
    }

    /**
     * Allow setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     *
     * @param t the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public String toString() {
/*
//        to print neighbors:
        String niStr = "{";
        for (node_data i : neighbors.values())
            niStr += "(" + i.getKey() +"), ";
*/
        return "(" +
                +key +
//                ": tag= " + tag + (!info.equals("") ? " info= " + info : "") +
//                ": " + niStr + "}" +
                ")";
    }
}