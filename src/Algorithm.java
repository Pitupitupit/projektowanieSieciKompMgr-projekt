import java.util.*;

public class Algorithm {
    public static int[] gbps20 = new int[]{50,100,150,200,250,300,350,400,450,500,550,600,650,700,750,800,850,900,950,1000};

    public static List<Demand> sortDemandsByDurationInc(List<Demand> toSortDemands){
        toSortDemands.sort(Comparator.comparing(Demand::getDuration));
        return toSortDemands;
    }

    public static List<Demand> getListOfDemandsInIteration(List<Demand> allDemands, int iteration){
        List<Demand> demandsInIteration = new ArrayList<>();

        for(Demand d : allDemands){
            if(d.getIteration()==iteration)
                demandsInIteration.add(d);
        }
        return demandsInIteration;
    }

    public static int getCorrectNumberOfSlicesForGivenBitrate(int bitrate, List<Integer> listOfSpectrums){
        for(int i=0;i<gbps20.length;i++){
            if(bitrate <= gbps20[i])
                return listOfSpectrums.get(i);
        }
        return -1;
    }

    public static int allocate(int numberOfSlices, int iterationsToLive, Path p, Topology topology){
        //robimy liste linków należących do ścieżki
        List<Link> listOfLinksInPath = new ArrayList<>();
        for(int i=0; i<p.getIndexesOfLinks().size();i++){
            listOfLinksInPath.add(topology.getListLinks().get(p.getIndexesOfLinks().get(i)));
        }
        //
        //Robimy listę list gapów. Jedna lista zawiera gapy dla jednego linka. Lista list zawiera gapy dla wszystkich linków w ścieżce
        List<List<Gap>> listOfListsOfGaps = new ArrayList<>();
        for(Link l : listOfLinksInPath){
            listOfListsOfGaps.add(findGaps(l,numberOfSlices));
        }
        //
        //musimy znaleźć pierwszy taki gap, który powtarza się na każdej z list. Czyli bierzemy z pierwszej listy jeden po drugim i porównujemy z gapami w innych listach
        int repetitions=1;
        Gap hopefullyGoodGap = null;
        for(Gap g : listOfListsOfGaps.get(0)){ //bierzemy gapy z pierwszej listy
            repetitions=1;
            for(int i=1;i<listOfListsOfGaps.size();i++){ //opuszczamy pierwszą listę. i=1
                for(Gap x : listOfListsOfGaps.get(i)){ //bierzemy gapy
                    if(x.getStartSlice()==g.getStartSlice() && x.getStopSlice()==g.getStopSlice() && x.getFiberCore()==g.getFiberCore() && x.getWidth()==g.getWidth()) { //nie mam metody porównawczej więc tak sprawdzam czy gapy są takie same
                        repetitions++;
                    }

                }
                if(i+1==repetitions){

                } else { //jeśli po sprawdzeniu kolejnej listy repetitions się nie zwiększyło, to porównywany Gap (ten z 1. listy) nie powtórzył się. Więc nie ma sensu przeglądać dalszych list
                    break;
                }
            }
            if(repetitions==listOfListsOfGaps.size()){//mamy to. Gap sie powtarza w kazdej liscie
                hopefullyGoodGap = g;
            }
        }
        if(hopefullyGoodGap!=null){ //znalazło dobrego gapa, więc dodajemy do Linków w ścieżce Slices oraz oznaczamy odpowiednie Sloty jako true-zajete
            for(Link l : listOfLinksInPath){
                l.getSlices().add(new Slices(hopefullyGoodGap.getStartSlice(), hopefullyGoodGap.getStopSlice(), hopefullyGoodGap.getWidth(),iterationsToLive, hopefullyGoodGap.getFiberCore()));

                for(int i=hopefullyGoodGap.getStartSlice();i<=hopefullyGoodGap.getStopSlice();i++){
                    l.setOneSlot(true,hopefullyGoodGap.getFiberCore(),i);
                }
            }

            for(int i=0; i<p.getIndexesOfLinks().size();i++){
                topology.updateOneLink(p.getIndexesOfLinks().get(i),listOfLinksInPath.get(i));
            }
            return 1;
        } else { //first fit
            int count=0;
            int numberOfFreeSlots=0;
            int startSlot=0;
            int endSlot=0;
            int core=0;
            for(int i=0;i<7;i++){
                for(int j=0;j<320;j++){
                    if(listOfLinksInPath.get(0).getSlots()[i][j]==Boolean.FALSE){
                        count=1;
                        for(int k=1;k<listOfLinksInPath.size();k++){
                            if(listOfLinksInPath.get(k).getSlots()[i][j]==Boolean.FALSE){
                                count++;
                            } else {
                                count =0;
                                break;
                            }
                        }
                        if(count==listOfLinksInPath.size()){
                            numberOfFreeSlots++;

                        } else {
                            count=0;

                        }
                        if(numberOfFreeSlots==numberOfSlices+1 && (j-numberOfSlices==0 || j==319)){//wygryw
                            endSlot=j;
                            startSlot=j-numberOfSlices;
                            core=i;
                            break;
                        } else if(numberOfFreeSlots==numberOfSlices+2){//wygryw
                            endSlot=j;
                            if(j==0){
                                System.out.println("as");
                            }
                            startSlot=j-numberOfSlices-1;
                            core=i;
                            break;
                        }
                    } else {
                        numberOfFreeSlots=0;

                        count=0;
                    }
                }
                if(numberOfFreeSlots==numberOfSlices+2 || numberOfFreeSlots==numberOfSlices+1){//wygryw
                    break;
                }
                count=0;
                numberOfFreeSlots=0;
            }//
            if(numberOfFreeSlots==numberOfSlices+2 || numberOfFreeSlots==numberOfSlices+1) {
                if(numberOfFreeSlots==0){
                    System.out.println("???");
                }

                for (Link l : listOfLinksInPath) {
                    l.getSlices().add(new Slices(startSlot, endSlot, endSlot-startSlot+1, iterationsToLive, core));

                    for (int i = startSlot; i <= endSlot; i++) {
                        //System.out.println("core: "+core);
                        //System.out.println("slot: "+i);
                        l.setOneSlot(true, core, i);
                    }
                }

                for (int i = 0; i < p.getIndexesOfLinks().size(); i++) {
                    topology.updateOneLink(p.getIndexesOfLinks().get(i), listOfLinksInPath.get(i));
                }
                return 1;
            } else return 0;


        }
    }



    public static List<Gap> findGaps(Link l, int numberOfSlices){
        List<Gap> listGaps = new ArrayList<>();

        int count; //licz wolne sloty
        int startGap=-1;
        for(int i=0;i<7;i++){
            count=0;
            for(int j=0;j<320;j++){
                if(l.getSlots()[i][j] == Boolean.FALSE){//wolny slot
                    if(count==0){
                        startGap=j;
                    }
                    count++;
                    if(j==319){ //napotkał koniec kora
                        listGaps.add(new Gap(startGap,j,i,j-startGap+1));
                        //count sie wyzeruje bo spada do pętli niżej
                    }
                } else { //napotkało zajęty slot
                    if(count>0){
                        listGaps.add(new Gap(startGap,j-1,i,j-startGap));
                    }

                    count = 0;
                }

            }
        }

        //nie można użyc zwykłego for eacha.Bo trzeba usuwac z listy. Bezpieczniej z iteratorem poniżej
        /*for(Gap g : listGaps){
            if(g.getWidth()==numberOfSlices+2) {//+2 guard bandy z obu stron
                //świetnie :D
            } else if(g.getWidth()==numberOfSlices+1){//na poczatku kora OK/ na końcu kora OK / w środku ŹLE
                if(g.getStartSlice()==0){
                    //świetnie (･ω･)
                } else if(g.getStopSlice()==319){
                    //świetnie (･ω･)
                } else {
                    listGaps.remove(g);
                }
            }
        }*/

        Iterator<Gap> i = listGaps.iterator();
        while (i.hasNext()) {
            Gap g = i.next(); // must be called before you can call i.remove()
            if(g.getWidth()==numberOfSlices+2) {//+2 guard bandy z obu stron
                //świetnie :D
            } else if(g.getWidth()==numberOfSlices+1){//na poczatku kora OK/ na końcu kora OK / w środku nie
                if(g.getStartSlice()==0){
                    //świetnie (･ω･)
                } else if(g.getStopSlice()==319){
                    //świetnie (･ω･)
                } else {
                    i.remove();
                }
            } else {
                i.remove();
            }
        }

        return listGaps;
    }

    public static void releaseAndCalculateTTL(Topology topology){
        for(Link link : topology.getListLinks()){
            Iterator<Slices> slices = link.getSlices().iterator();
            while (slices.hasNext()) {
                Slices s = slices.next();
                if(s.getExpire()==0){
                    for (int i = s.getSliceStart(); i <= s.getSliceStop(); i++) {
                        link.setOneSlot(false, s.getCoreIndex(),i);
                    }
                    slices.remove();
                } else {
                    s.setExpire(s.getExpire()-1);
                }
            }


        }
    }
}












