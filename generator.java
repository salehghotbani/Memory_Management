import java.util.ArrayList;
import java.util.Random;

public class generator {
    public ArrayList<ProgramInfoClass> getListRequests() {
        return listRequests;
    }


    ArrayList<ProgramInfoClass> listRequests = new ArrayList<>();

    public int[] ge(int sizeOfMemory, MainClass mainClass) {
        Random random = new Random();
        int counterOfRequests, limit = 0, sumLimit = 0, hole, sizeOfHole;
        if (sizeOfMemory > 1000) {
            counterOfRequests = random.nextInt(Math.abs((sizeOfMemory / 1000) - 2)) + 3;
        } else {
            counterOfRequests = random.nextInt(2) + 3;
        }

        for (int i = 0; i < counterOfRequests; i++) {
            limit = random.nextInt(sizeOfMemory / counterOfRequests);
            if (limit < sizeOfMemory / (counterOfRequests * 3)) {
                limit += sizeOfMemory / (counterOfRequests * 3);
            }
            sumLimit += limit;
            //limit -= random.nextInt(limit - 10) / 7 + 1;
            System.out.println(limit);
            listRequests.add(new ProgramInfoClass(i, 0, limit));
        }

        hole = sizeOfMemory - sumLimit;
        sizeOfHole = hole / counterOfRequests;
        listRequests.get(0).setPBase(0);

        for (int i = 1; i < counterOfRequests; i++) {
            listRequests.get(i).setPBase(listRequests.get(i - 1).getPBase() + listRequests.get(i - 1).getPLimit() + sizeOfHole);
            System.out.println(listRequests.get(i - 1).getPBase() + listRequests.get(i - 1).getPLimit() + sizeOfHole + ":   " + i);
        }
        int[] a={counterOfRequests,sizeOfHole,sizeOfMemory,sumLimit};
        return a;
    }
    public ArrayList<ProgramInfoClass> getarray(ArrayList<ProgramInfoClass> list){
        return list;
    }
}