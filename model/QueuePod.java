package model;

public class QueuePod implements Comparable<QueuePod> {
    private Node node;
    private int fScore;
    private int insertionOrderValue;

    public QueuePod(Node node, int fScore, int insertionOrderValue) {
        this.node = node;
        this.fScore = fScore;
        this.insertionOrderValue = insertionOrderValue;
    }

    public int getInsertionOrderValue() {
        return insertionOrderValue;
    }

    public Node getNode() {
        return node;
    }

    public int getfScore() {
        return fScore;
    }

    @Override
    public int compareTo(QueuePod o) {
        if (this.fScore < o.fScore) {
            return -1;
        }
        if (this.fScore > o.fScore) {
            return 1;
        }
        return 0;
    }
}
