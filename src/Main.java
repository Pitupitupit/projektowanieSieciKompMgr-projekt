import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    final static String relPath = "rssa_dynamic_data\\Euro28 - dynamic\\";
    //final static String demFile = "2000_10.dem";
    final static String netFile = "ff.net";
    final static String patFile = "ff30.pat";
    final static String specFile = "f30.spec";

    public static void main(String[] args) {
        Topology topology = new Topology(relPath, netFile);
        CandidatesPaths[][] candidatesPaths = CandidatesPaths.loadAllPaths(relPath, patFile, specFile); //zawiera też spec.

        for(String f : listOfFiles()) {
            List<Demand> demands = Demand.loadDem(relPath, f);
            //List<Demand> demands = Demand.loadDem(relPath, "2000_10.dem");

            assert demands != null;
            assert candidatesPaths != null;
            //
            int licz = 0;
            int wynik = 0;
            for (int i = 0; i < 2000; i++) {
                /*if (i < 1999) {
                    System.out.println("i="+i);
                    showCore0(topology);
                }*/
                Algorithm.releaseAndCalculateTTL(topology);
                List<Demand> inDemands = Algorithm.getListOfDemandsInIteration(demands, i);
                List<Demand> sortedInDemands = Algorithm.sortDemandsByDurationInc(inDemands);
                for (Demand d : sortedInDemands) {
                    for (Path p : candidatesPaths[d.getSourceNode()][d.getDestinationNode()].getListOfPaths()) {
                        int slices = Algorithm.getCorrectNumberOfSlicesForGivenBitrate(d.getBitrate(), p.getListOfSpectrums());

                        wynik = Algorithm.allocate(slices, d.getDuration(), p, topology);
                        if (wynik == 1) {
                            licz = licz + wynik;
                            break;
                        }
                        //else if (wynik == 2) break; //moje testy

                    }
                }
            }

            //System.out.println(licz);
            System.out.println(f+" " + licz);
        }











       /*if(demands != null) {
            for (Demand d : demands) {
                //System.out.println(d.getIteration());
            }
            //System.out.println("ile: "+demands.size());
        }*/

        // S O U Ty topologii
        /*String liniaTopologii="";
        for(int i = 0; i < 28 ; i++){
            for(int j = 0; j < 28; j++){
                liniaTopologii = liniaTopologii+topology.getLinks()[i][j]+" ";
                //System.out.println(topology.getLinks()[i][j]+" ");
            }
            System.out.println(liniaTopologii);
            liniaTopologii="";
            System.out.println("\n");

        }*/

        //System.out.println(topology.getLinks()[0][2]);



        // S O U Ty sprawdzające CandidatesPaths;
        /*System.out.println("--------");
        for(Integer x : candidatesPaths[27][26].getListOfPaths().get(29).getIndexesOfLinks()){
            System.out.println(x); //czyli tu sie wyswietla zajete linki przy przesylaniu z 27 wezla do 26
        }

        System.out.println(candidatesPaths[27][26].getListOfPaths().get(29).getNoLineInFile()); //a tu nr linii-1 z ff30.pat

        for(Integer x : candidatesPaths[27][26].getListOfPaths().get(29).getListOfSpectrums()){
            System.out.println(x); //lista spektrumuf


        }*/

         /* sprawdzenie dzialania sortowania
        List<Demand> list = new ArrayList<>();
        list.add(new Demand(1,1,1,1,10));
        list.add(new Demand(1,1,1,1,5));
        list.add(new Demand(1,1,1,1,13));
        list.add(new Demand(1,1,1,1,1));
        list.add(new Demand(1,1,1,1,0));
        list.add(new Demand(1,1,1,1,10));
        list.add(new Demand(1,1,1,1,22));
        list.add(new Demand(1,1,1,1,22));
        list.add(new Demand(1,1,1,1,42));
        list.add(new Demand(1,1,1,1,2));
        list.add(new Demand(1,1,1,1,4));
        list.sort(Comparator.comparing(Demand::getDuration));

        for(Demand d : list){
            System.out.println(d.getDuration());
        }
        */

    }

    public static List<String> listOfFiles(){
        String s1 = "";
        String s2 = "0";
        List<String> list = new ArrayList<>();

        for(int k=1;k<=20;k++) {
            s1=k*100+"_";
            for (int i = 1; i <= 10; i++) {
                if (i == 10) {
                    list.add(s1 + 10+".dem");
                    //System.out.println(s1+10+".dem");
                } else {
                    list.add(s1 + s2 + i+".dem");
                    //System.out.println(s1 + s2 + i+".dem");
                }

            }
        }
        return list;
    }

    public static void showCore0(Topology topology){
        String s="";
        for(Link link : topology.getListLinks()){
            if(link.getSourceNode()==0 && link.getDestinationNode()==1) {
                System.out.println("Link source n, link dest n: " + link.getSourceNode() + " " + link.getDestinationNode());

                for (int i = 0; i < 320; i++) {
                    if (link.getSlots()[0][i] == false) {
                        s = s + "0";
                    } else
                        s = s + "1";
                }
                System.out.println(s);
                System.out.println("------");
                s = "";
            }
        }
    }

}
