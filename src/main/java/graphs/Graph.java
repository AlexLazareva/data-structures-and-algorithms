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

        for (int j = 0; j < MAX_VERTS; j++) { // Заполняем матрицу смежности бесконечными расстояниями
            for (int k = 0; k < MAX_VERTS; k++) {
                adjMat[j][k] = INFINITY;
            }
        }
        sPath = new DistPar[MAX_VERTS]; // кратчайшие пути
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
            adjust_sPath();
        }

        // Очистка дерева
        displayPaths();
        nTree = 0;

        for (int i = 0; i < nVerts; i++) {
            vertexList[i].isInTree = false;
        }
    }

    private void displayPaths() {
        for (int i = 0; i < nVerts; i++) {
            System.out.print(vertexList[i].label + "=");

            if (sPath[i].distance == INFINITY) {
                System.out.print("inf");
            } else {
                System.out.print(sPath[i].distance);
                char parent = vertexList[sPath[i].parentVert].label;
                System.out.print("(" + parent + ") ");
            }
            System.out.println("");
        }
    }

    // возвращает индекс элемента с наименьшим расстоянием
    private int getMin() {
        int minDist = INFINITY;
        int indexMin = 0;

        for (int i = 1; i < nVerts; i++) {
            if (!vertexList[i].isInTree && sPath[i].distance < minDist) {
                minDist = sPath[i].distance;
                indexMin = i;
            }
        }
        return indexMin;
    }

    private void adjust_sPath() {
        int column = 1; // пропускаем начальную вершину

        while (column < nVerts) {
            // Если вершина column уже включена в дерево, она пропускается
            if (vertexList[column].isInTree) {
                column++;
                continue;
            }

            int currentToFringe = adjMat[currentVert][column];
            int startToFringe = startToCurrent + currentToFringe;
            int sPathDist = sPath[column].distance;

            if (startToFringe < sPathDist) {
                sPath[column].distance = currentVert;
                sPath[column].parentVert = startToFringe;
            }
            column++;
        }
    }
}
