package DisjointedSets;

/**
 * given a numberOfElements, all elements include 0 to numberOfElements - 1
 */
public class WeightedDisjointedSetsTreeHeight {
    private int[] parents;      //parents[i] gives the node above element i
    private int[] heights;      //heights[i] give the height of the tree that has element i if i is the root

    public  WeightedDisjointedSetsTreeHeight(int numberOfElements) {
        parents = new int[numberOfElements];
        heights = new int[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            parents[i] = i;
            heights[i] = 1;
        }
    }

    public void connect(int t1, int t2) {
        int height1 = height(t1);
        int height2 = height(t2);

        if (!isConnected(t1, t2)) {
            if (height1 < height2) {
                parents[find(t1)] = find(t2);
            } else {
                if (height1 == height2) {
                    heights[find(t1)] = height1 + 1;
                }
                parents[find(t2)] = find(t1);
            }
        }
    }

    /**
     * @param element
     * @return the Root of the tree that contains element
     */
    public int find(int element) {
        int child = element;
        int parent = parents[element];
        while (child != parent) {
            child = parent;
            parent = parents[child];
        }
        return parent;
    }

    public boolean isConnected(int t1, int t2) {
        return find(t1) == find(t2);
    }

    public int[] getHeights() {
        int[] treeHeights = new int[parents.length];
        for (int i = 0; i < treeHeights.length; i++) {
            treeHeights[i] = height(i);
        }
        return treeHeights;
    }

    /**
     * @param element
     * @return the height of the tree that contains element
     */
    public int height(int element) {
        return heights[find(element)];
    }

    public int[] getParents() {
        return parents;
    }
}
