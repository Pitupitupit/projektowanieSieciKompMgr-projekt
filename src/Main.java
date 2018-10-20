import java.util.List;

public class Main {

    final static String absPath = "D:\\programowanie\\projektowanieSieciKompMgr-projekt\\rssa_dynamic_data\\Euro28 - dynamic\\";
    final static String relPath = "rssa_dynamic_data\\Euro28 - dynamic\\";
    final static String demFile = "100_01.dem";
    final static String netFile = "ff.net";
    final static String patFile = "ff30.pat";

    public static Demand[] demands = new Demand[2000];

    public static void main(String[] args) {
        List<Demand> demands = Demand.loadDem(relPath, demFile);

        System.out.println("Dzien dobry!");

        if(demands != null) {
            for (Demand d : demands) {
                //System.out.println(d.getIteration());
            }
            //System.out.println("ile: "+demands.size());
        }

        Topology topology = new Topology(relPath, netFile);
        for(int i = 0; i < 28 ; i++){
            for(int j = 0; j < 28; j++){
                //System.out.println(topology.getLinks()[i][j]+" ");
            }
            //System.out.println("\n");

        }

        //System.out.println(topology.getLinks()[0][2]);


        CandidatesPaths p = new CandidatesPaths();
        CandidatesPaths[][] candidatesPaths = p.loadAllPaths(relPath, patFile);

        System.out.println("--------");
        for(Integer x : candidatesPaths[27][26].getListOfPaths().get(29).getIndexesOfLinks()){
            System.out.println(x); //czyli tu sie wyswietla zajete linki przy przesylaniu z 27 wezla do 26
        }

        System.out.println(candidatesPaths[27][26].getListOfPaths().get(29).getNoLineInFile()); //a tu nr linii-1 z ff30.pat








    }

}
