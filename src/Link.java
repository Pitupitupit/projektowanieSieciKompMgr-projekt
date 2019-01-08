import java.util.ArrayList;
import java.util.List;

public class Link {

    private int sourceNode;
    private int destinationNode;
    private int length;
    private Boolean[][] slots = new Boolean[7][320]; //FALSE wolny, TRUE zajety
    private List<Slices> slices = new ArrayList<>(); //zaalokowane w danym momencie slajsy. Usuwane gdy ich czas się skończy

    public Link(int sourceNode, int destinationNode, int length) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.length = length; //wartość z tolopogii sieci
        for(int i=0;i<7;i++){
            for(int j=0;j<320;j++){
                slots[i][j]=Boolean.FALSE;
            }
        }
        this.slices = new ArrayList<>();
    }

    public int getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(int sourceNode) {
        this.sourceNode = sourceNode;
    }

    public int getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(int destinationNode) {
        this.destinationNode = destinationNode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Boolean[][] getSlots() {
        return slots;
    }

    public void setSlots(Boolean[][] slots) {
        this.slots = slots;
    }

    public List<Slices> getSlices() {
        return slices;
    }

    public void setSlices(List<Slices> slices) {
        this.slices = slices;
    }

    public void setOneSlot(Boolean b, int fiberCoreIndex, int sliceIndex){
        this.slots[fiberCoreIndex][sliceIndex]=b;
    }
}
