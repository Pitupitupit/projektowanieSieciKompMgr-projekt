
public class Main {

    final static String absPath = "D:\\programowanie\\projektowanieSieciKompMgr-projekt\\rssa_dynamic_data\\Euro28 - dynamic\\";
    final static String relPath = "rssa_dynamic_data\\Euro28 - dynamic\\";
    final static String demFile = "100_01.dem";

    public static Demand[] demands = new Demand[2000];

    public static void main(String[] args) {
        Demand[] demands = Demand.loadDem(relPath, demFile);

        if(demands != null)
            for(Demand d : demands){
                System.out.println(d.getIteration());
            }

    }

}
