import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CandidatesPaths {

    public static final int k = 30;

    private List<Path> listOfPaths = new ArrayList<>(); //rozmiar zawsze 30 (k)

    public static CandidatesPaths[][] loadAllPaths(String path, String patFileName, String specFileName){
        CandidatesPaths[][] candidatesPaths = new CandidatesPaths[28][28];
        for(int i=0;i<28;i++){
            for(int j=0;j<28;j++){
                candidatesPaths[i][j] = new CandidatesPaths();
            }
        }
        try{
            //pat
            FileInputStream fstream = new FileInputStream(path+patFileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            //
            //spec
            FileInputStream fstreamSPEC = new FileInputStream(path+specFileName);
            DataInputStream inSPEC = new DataInputStream(fstreamSPEC);
            BufferedReader brSPEC = new BufferedReader(new InputStreamReader(inSPEC));
            //
            String strLine;
            String strLineSPEC;
            //
            int rowIndex = 0;
            int columnIndex;
            int sourceNode = 0;
            int destinationNode = 0;
            while ((strLine = br.readLine()) != null && (strLineSPEC = brSPEC.readLine()) != null)   {
                if(rowIndex%k==0 && rowIndex!=0){
                    destinationNode++;
                }
                if(rowIndex%810==0 && rowIndex!=0){
                    sourceNode++;
                }
                if(destinationNode==28){
                    destinationNode=0;
                }
                if(sourceNode==destinationNode){
                    destinationNode++;
                }

                String[] tokens = strLine.split(" ");
                String[] tokensSPEC = strLineSPEC.split(" ");

                Path p = new Path(sourceNode, destinationNode, new ArrayList<Integer>(), rowIndex, new ArrayList<Integer>());
                for(columnIndex = 0; columnIndex<tokens.length; columnIndex++){
                    if(Integer.parseInt(tokens[columnIndex])!=0) {
                        p.getIndexesOfLinks().add(columnIndex);
                    }
                }
                for(columnIndex = 0; columnIndex<tokensSPEC.length; columnIndex++){
                   p.getListOfSpectrums().add(Integer.parseInt(tokensSPEC[columnIndex]));
                }
                candidatesPaths[sourceNode][destinationNode].listOfPaths.add(p);
                //System.out.println(sourceNode+ " " +destinationNode);
                rowIndex++;
            }
            //System.out.println(rowIndex);
            in.close();

            return candidatesPaths;

        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
        return null;

    }

    public List<Path> getListOfPaths() {
        return listOfPaths;
    }

    public void setListOfPaths(List<Path> listOfPaths) {
        this.listOfPaths = listOfPaths;
    }
}
