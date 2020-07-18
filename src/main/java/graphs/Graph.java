package graphs;

public class Graph {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex vertexList[]; // Список вершин
    private int adjMat[][];      // Матрица смежности
    private int nVerts;         // Текущее количество вершин
    private int nTree;          // Количество вершин в дереве
    private DistPar sPath[];    // Массив кратчайших путей
    private int currentVert;
    private int startToCurrent;

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

    // метод добавления вершины
    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    // метод добавления ребра
    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
    }

    // выбор кратчайших путей
    public void path() {
        int startTree = 0;
        vertexList[startTree].isInTree = true;
        nTree = 1;

        // перемещение строки расстояний из adjMat в sPath
        for (int i = 0; i < nVerts; i++) {
            int tempDist = adjMat[startTree][i];
            sPath[i] = new DistPar(startTree, tempDist);
        }

        // пока все вершины не окажутся в дереве
        while (nTree < nVerts) {
            int indexMin = getMin();
            int minDist = sPath[indexMin].distance;

            if (minDist == INFINITY) {
                System.out.println("There are unreachble vertices");
                break;
            } else {
                currentVert = indexMin;
                startToCurrent = sPath[indexMin].distance;
            }
            vertexList[currentVert].isInTree = true;
            nTree++;
        }
    }

    // возвращает индекс элемента с наименьшим расстоянием
    private int getMin() {
        int minDist = INFINITY;
        int indexMin = 0;

        for (int i = 0; i < nVerts; i++) {
            if (!vertexList[i].isInTree && sPath[i].distance < minDist) {
                minDist = sPath[i].distance;
                indexMin = i;
            }
        }
        return indexMin;
    }
}
