package Main;

import java.util.ArrayList;
import java.util.List;

public class FourTree<X extends Comparable<X>, Y extends Comparable<Y>> {
    private Node<X, Y> root;

    public FourTree() {
    }

    public FourTree(X x, Y y) {
        root = new Node(x, y);
    }

    public void add(X x, Y y) {
        root = addHelper(x, y, root);
    }

    public void print() {
        root.print();
    }

    public List<PlainNode<X, Y>> range(X minX, X maxX, Y minY, Y maxY) {
        return rangeHelper(minX, maxX, minY, maxY, root);
    }

    public ArrayList<PlainNode<X, Y>> rangeHelper(X minX, X maxX, Y minY, Y maxY, Node<X, Y> p) {
        ArrayList<PlainNode<X, Y>> returnValue = new ArrayList<>();

        int compareToXMin = p.xValue.compareTo(minX);
        int compareToXMax = p.xValue.compareTo(maxX);
        int compareToYMin = p.yValue.compareTo(minY);
        int compareToYMax = p.yValue.compareTo(maxY);

        if (compareToXMin >= 0 && compareToXMax <= 0 && compareToYMin >= 0 && compareToYMax <= 0) {
            returnValue.add(new PlainNode<>(p.xValue, p.yValue));
        }

        if (compareToXMax > 0 && compareToYMax > 0) {
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant3));
        } else if (compareToXMin >= 0 && compareToXMax <= 0 && compareToYMax > 0) {
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant3));
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant4));
        } else if (compareToXMin >= 0 && compareToXMax <= 0 && compareToYMax > 0) {
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant3));
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant4));
        } else if (compareToXMin < 0 && compareToYMax > 0) {
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant4));
        } else if (compareToYMin >= 0 && compareToYMax <= 0 && compareToXMin < 0) {
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant1));
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant4));
        } else if (compareToXMin < 0 && compareToYMin < 0) {
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant1));
        } else if (compareToXMin >= 0 && compareToXMax <= 0 && compareToYMin < 0) {
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant1));
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant2));
        } else if (compareToXMin > 0 && compareToYMin < 0) {
            returnValue.addAll(rangeHelper(minX, maxX, minY, maxY, p.quadrant2));
        }
        return returnValue;
    }

    private Node addHelper(X x, Y y, Node<X, Y> p) {
        if (p == null) {
            return new Node(x, y);
        }
        int xComp = x.compareTo(p.xValue);
        int yComp = y.compareTo(p.yValue);
        if (xComp > 0 && yComp >= 0) {
            p.quadrant1 = addHelper(x, y, p.quadrant1);
        } else if (xComp <= 0 && yComp > 0) {
            p.quadrant2 = addHelper(x, y, p.quadrant2);
        } else if (xComp < 0 && yComp <= 0) {
            p.quadrant3 = addHelper(x, y, p.quadrant3);
        } else if (xComp >= 0 && yComp < 0) {
            p.quadrant4 = addHelper(x, y, p.quadrant4);
        }
        return p;
    }

    public static void main(String[] args) {
        FourTree<Integer, Integer> test = new FourTree<>();
        test.add(0,0);
        test.add(1, 0);
        test.add(5, 6);
        test.add(3, 4);
        test.add(-2, 3);
        test.add(-1, 2);
        test.add(-5, -5);
        test.add(-7, - 3);
        test.add(6, -1);
        test.add(6, -2);
        test.print();
    }

    private class Node<X, Y> {
        private X xValue;
        private Y yValue;
        private Node<X, Y> quadrant1;
        private Node<X, Y> quadrant2;
        private Node<X, Y> quadrant3;
        private Node<X, Y> quadrant4;

        public Node() {
        }

        public Node(X x, Y y) {
            xValue = x;
            yValue = y;
        }

        public void print() {
            printHelper(this, 1);
        }

        private void printHelper(Node<X, Y> p, int tabLevel) {
            if (p == null) {
                System.out.println("empty");
            } else {
                System.out.println("(" + p.xValue + ", " + p.yValue + ")");

                printTabs(tabLevel);
                printHelper(p.quadrant1, tabLevel + 1);

                printTabs(tabLevel);
                printHelper(p.quadrant2, tabLevel + 1);

                printTabs(tabLevel);
                printHelper(p.quadrant3, tabLevel + 1);

                printTabs(tabLevel);
                printHelper(p.quadrant4, tabLevel + 1);
            }
        }

        private void printTabs(int tabLevel) {
            for (int i = 0; i < tabLevel; i++) {
                System.out.print("\t");
            }
        }
    }

    private class PlainNode<X, Y> {
        private X xValue;
        private Y yValue;

        public PlainNode(X x, Y y) {
            xValue = x;
            yValue = y;
        }

        public void print() {
            System.out.print("(" + xValue + ", " + yValue + ")");
        }
    }
}
