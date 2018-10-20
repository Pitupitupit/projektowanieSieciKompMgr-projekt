import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

    final static String absPath = "D:\\programowanie\\projektowanieSieciKompMgr-projekt\\rssa_dynamic_data\\Euro28 - dynamic\\";
    final static String relPath = "rssa_dynamic_data\\Euro28 - dynamic\\";
    final static String demFile = "100_01.dem";

    public static Demand[] demands = new Demand[2000];

    public static void main(String[] args) {
        System.out.println("xD");
        System.out.println(System.getProperty("user.dir"));

        try{
            FileInputStream fstream = new FileInputStream(relPath+demFile);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                String[] tokens = strLine.split(" ");
                Demand d = new Demand(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4]);
                demands[Integer.parseInt(tokens[0])] = d;
                System.out.println(d.getIteration());
            }
            in.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

}
