import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Demand {
    private int iteration;
    private int sourceNode;
    private int destinationNode;
    private int bitrate;
    private int duration;

    public Demand(int iteration, int sourceNode, int destinationNode, int bitrate, int duration) {
        this.iteration = iteration;
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.bitrate = bitrate;
        this.duration = duration;
    }

    public Demand(String iteration, String sourceNode, String destinationNode, String bitrate, String duration) {
        this.iteration = Integer.parseInt(iteration);
        this.sourceNode = Integer.parseInt(sourceNode);
        this.destinationNode = Integer.parseInt(destinationNode);
        this.bitrate = Integer.parseInt(bitrate);
        this.duration = Integer.parseInt(duration);
    }

    public static Demand[] loadDem(String path, String demFileName){
        Demand[] demands = new Demand[2000];
        try{
            FileInputStream fstream = new FileInputStream(path+demFileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                String[] tokens = strLine.split(" ");
                Demand d = new Demand(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4]);
                demands[Integer.parseInt(tokens[0])] = d;
            }
            in.close();
            return demands;
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
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

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
