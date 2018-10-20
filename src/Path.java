import java.util.ArrayList;
import java.util.List;

public class Path {
   //public static final int noOfPathsBetweenNodes = 30; //czyli k z doca
    private int sourceNode;
    private int destinationNode;
    private List<Integer> indexesOfLinks = new ArrayList<>();
    private int noLineInFile;

    public Path(int sourceNode, int destinationNode, List<Integer> indexesOfLinks, int noLineInFile) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.indexesOfLinks = indexesOfLinks;
        this.noLineInFile = noLineInFile;
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

    public List<Integer> getIndexesOfLinks() {
        return indexesOfLinks;
    }

    public void setIndexesOfLinks(List<Integer> indexesOfLinks) {
        this.indexesOfLinks = indexesOfLinks;
    }

    public int getNoLineInFile() {
        return noLineInFile;
    }

    public void setNoLineInFile(int noLineInFile) {
        this.noLineInFile = noLineInFile;
    }
}
