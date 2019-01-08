import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Topology {

    private int noNodes; //number of Nodes
    private int noLinks;
    private int[][] links = new int[28][28];
    private List<Link> listLinks = new ArrayList<>();

    public Topology(String path, String fileName){
        loadNet(path, fileName);
    }

    public void loadNet(String path, String netFileName){
        try{
            FileInputStream fstream = new FileInputStream(path+netFileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int indexOfLineInFile = 0;
            int rowIndex = 0;
            int columnIndex;
            int countLinks = 0;
            while ((strLine = br.readLine()) != null)   {
                if(indexOfLineInFile != 0 && indexOfLineInFile != 1){
                    String[] tokens = strLine.split("\\t");

                    for(columnIndex = 0; columnIndex<tokens.length; columnIndex++){
                        if(Integer.parseInt(tokens[columnIndex])!=0) {
                            links[rowIndex][columnIndex] = Integer.parseInt(tokens[columnIndex]);
                            listLinks.add(new Link(rowIndex, columnIndex, Integer.parseInt(tokens[columnIndex])));
                            countLinks++;
                        }
                    }
                    rowIndex++;
                }
                if(indexOfLineInFile==0){
                    this.noNodes = Integer.parseInt(strLine);
                } else if(indexOfLineInFile==1) {
                    this.noLinks = Integer.parseInt(strLine);
                }
                indexOfLineInFile++;
            }
            //System.out.println("count links: "+countLinks);
            in.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public int getNoNodes() {
        return noNodes;
    }

    public void setNoNodes(int noNodes) {
        this.noNodes = noNodes;
    }

    public int getNoLinks() {
        return noLinks;
    }

    public void setNoLinks(int noLinks) {
        this.noLinks = noLinks;
    }

    public int[][] getLinks() {
        return links;
    }

    public void setLinks(int[][] links) {
        this.links = links;
    }

    public List<Link> getListLinks() {
        return listLinks;
    }

    public void setListLinks(List<Link> listLinks) {
        this.listLinks = listLinks;
    }

    public void updateOneLink(int index,Link l){
        this.listLinks.set(index,l);
    }
}
