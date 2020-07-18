package graphs;

public class Vertex {
    private char label; // Метка вершины
    public boolean isInTree;

    public Vertex(char label) {
        this.label = label;
        this.isInTree = false;
    }
}
