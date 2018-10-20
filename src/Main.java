import java.util.List;

public class Main {

    final static String absPath = "D:\\programowanie\\projektowanieSieciKompMgr-projekt\\rssa_dynamic_data\\Euro28 - dynamic\\";
    final static String relPath = "rssa_dynamic_data\\Euro28 - dynamic\\";
    final static String demFile = "100_01.dem";
    final static String netFile = "ff.net";

    public static Demand[] demands = new Demand[2000];

    public static void main(String[] args) {
        List<Demand> demands = Demand.loadDem(relPath, demFile);

        if(demands != null) {
            for (Demand d : demands) {
                //System.out.println(d.getIteration());
            }
            System.out.println("ile: "+demands.size());
        }

        Topology topology = new Topology(relPath, netFile);
        for(int i = 0; i < 28 ; i++){
            for(int j = 0; j < 28; j++){
                //System.out.println(topology.getLinks()[i][j]+" ");
            }
            //System.out.println("\n");

        }

        System.out.println(topology.getLinks()[2][1]);




    }

}
