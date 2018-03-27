package DisjointedSets;

import org.junit.Test;
import org.junit.Assert;

public class WeightedDisjointedSetsTreeHeightTests {
    @Test
    public void basicIsConnected() {
        WeightedDisjointedSetsTreeHeight set = new WeightedDisjointedSetsTreeHeight(10);
        Assert.assertEquals(false, set.isConnected(1, 2));
        Assert.assertEquals(1, set.height(1));

        set.connect(1, 2);
        Assert.assertEquals(true, set.isConnected(1, 2));
        Assert.assertEquals(2, set.height(1));
        Assert.assertEquals(2, set.height(2));

        set.connect(1, 2);
        Assert.assertEquals(true, set.isConnected(1, 2));
        Assert.assertEquals(2, set.height(1));
        Assert.assertEquals(2, set.height(2));

        set.connect(1, 5);
        Assert.assertEquals(true, set.isConnected(5, 2));
        Assert.assertEquals(2, set.height(1));
        Assert.assertEquals(2, set.height(2));

        set.connect(1, 0);

        set.connect(8, 9);
        set.connect(7, 9);
        set.connect(6, 4);
        set.connect(6, 3);
        set.connect(6, 9);
        set.connect(1, 9);

        int[] heights = new int[10];
        for (int i = 0; i < 10; i++) {
            heights[i] = 3;
        }

        int[] setHeights = new int[10];
        for (int i = 0; i < 10; i++) {
            setHeights[i] = set.height(i);
        }
        Assert.assertArrayEquals(heights, setHeights);

        int[] parents = new int[10];
        parents[0] = 1;
        parents[1] = 6;
        parents[2] = 1;
        parents[3] = 6;
        parents[4] = 6;
        parents[5] = 1;
        parents[6] = 6;
        parents[7] = 8;
        parents[8] = 6;
        parents[9] = 8;
        Assert.assertArrayEquals(parents, set.getParents());
    }
}
