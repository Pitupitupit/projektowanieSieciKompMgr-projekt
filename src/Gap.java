public class Gap {
    private int startSlice;
    private int stopSlice;
    private int fiberCore;
    private int width;

    public Gap(int startSlice, int stopSlice, int fiberCore, int width) {
        this.startSlice = startSlice;
        this.stopSlice = stopSlice;
        this.fiberCore = fiberCore;
        this.width = width;
    }

    public int getStartSlice() {
        return startSlice;
    }

    public void setStartSlice(int startSlice) {
        this.startSlice = startSlice;
    }

    public int getStopSlice() {
        return stopSlice;
    }

    public void setStopSlice(int stopSlice) {
        this.stopSlice = stopSlice;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getFiberCore() {
        return fiberCore;
    }

    public void setFiberCore(int fiberCore) {
        this.fiberCore = fiberCore;
    }
}
