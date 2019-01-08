
//pole w obiekcie Link. Slices to zajęte slajsy dla jednego żądania w konkretnym linku
public class Slices {
    private int sliceStart;
    private int sliceStop;
    private int slicesTaken;
    private int expire;
    private int coreIndex;

    public Slices(int sliceStart, int sliceStop, int slicesTaken, int expire, int coreIndex) {
        this.sliceStart = sliceStart;
        this.sliceStop = sliceStop;
        this.slicesTaken = slicesTaken;
        this.expire = expire;
        this.coreIndex = coreIndex;
    }

    public int getSliceStart() {
        return sliceStart;
    }

    public void setSliceStart(int sliceStart) {
        this.sliceStart = sliceStart;
    }

    public int getSliceStop() {
        return sliceStop;
    }

    public void setSliceStop(int sliceStop) {
        this.sliceStop = sliceStop;
    }

    public int getSlicesTaken() {
        return slicesTaken;
    }

    public void setSlicesTaken(int slicesTaken) {
        this.slicesTaken = slicesTaken;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getCoreIndex() {
        return coreIndex;
    }

    public void setCoreIndex(int coreIndex) {
        this.coreIndex = coreIndex;
    }
}
