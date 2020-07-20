package graphs;

public class DistPar {
    public int distance;
    public int parentVert;

    public DistPar(int distance, int parentVert) {
        this.distance = distance;
        this.parentVert = parentVert;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getParentVert() {
        return parentVert;
    }

    public void setParentVert(int parentVert) {
        this.parentVert = parentVert;
    }
}
