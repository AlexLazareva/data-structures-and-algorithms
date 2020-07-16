package graphs;

public class Vertex {
    public char label; // Метка вершины
    public boolean isInTree;

    public Vertex(char lab) {
        label = lab;
        isInTree = false;
    }
}
