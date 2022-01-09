import java.util.ArrayList;
import java.util.Random;

public class PagingClass {
    Random random = new Random();
    ArrayList<ProgramInfoClass> listRequests = new ArrayList<>();
    int counterOfRequests, limit = 0, sumLimit = 0, hole, sizeOfHole;

    public void generator(int sizeOfMemory, MainClass mainClass) {
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
        sizeOfHole = hole / (counterOfRequests - 1);
        listRequests.get(0).setPBase(0);

        for (int i = 1; i < counterOfRequests; i++) {
            listRequests.get(i).setPBase(listRequests.get(i - 1).getPBase() + listRequests.get(i - 1).getPLimit() + sizeOfHole);
            System.out.println(listRequests.get(i - 1).getPBase() + listRequests.get(i - 1).getPLimit() + sizeOfHole + ":   " + i);
        }
        //showChart(mainClass);
    }
}
