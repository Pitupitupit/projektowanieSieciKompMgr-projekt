import java.util.ArrayList;
import java.util.List;

public class Path {
   //public static final int noOfPathsBetweenNodes = 30; //czyli k z doca
    private int sourceNode;
    private int destinationNode;
    private List<Integer> indexesOfLinks = new ArrayList<>(); // .pat (tu są indeksy kolumn - łącza)
    private int noLineInFile;
    private List<Integer> listOfSpectrums = new ArrayList<>(); //linia z pliku .spec

    public Path(int sourceNode, int destinationNode, List<Integer> indexesOfLinks, int noLineInFile, List<Integer> listOfSpectrums) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.indexesOfLinks = indexesOfLinks;
        this.noLineInFile = noLineInFile;
        this.listOfSpectrums = listOfSpectrums;
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

    public List<Integer> getListOfSpectrums() {
        return listOfSpectrums;
    }

    public void setListOfSpectrums(List<Integer> listOfSpectrums) {
        this.listOfSpectrums = listOfSpectrums;
    }
}
