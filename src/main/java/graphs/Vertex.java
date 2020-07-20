package graphs;

public class Vertex {
    public char label; // Метка вершины
    public boolean isInTree;

    public Vertex(char label) {
        this.label = label;
        this.isInTree = false;
    }
}
