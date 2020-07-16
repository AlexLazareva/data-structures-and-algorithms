package graphs;

public class Graph {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex vertexList[]; // Список вершин
    private int adjMat[][];      // Матрица смежности
    private int nVerts;         // Текущее количество вершин
    private int nTree;          // Количество вершин в дереве
    private DistPar sPath[];    // Массив кратчайших путей

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;

        for (int i = 0; i < MAX_VERTS; i++) { // Заполняем матрицу смежности бесконечными расстояниями
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = INFINITY;
                sPath = new DistPar[MAX_VERTS]; // кратчайшие пути
            }
        }
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
    }

    public void path() {
        System.out.println("path");
    }
}
