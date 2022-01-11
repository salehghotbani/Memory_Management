import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MemoryManagerClass {
    Random random = new Random();
    ArrayList<ProgramInfoClass> listRequests = new ArrayList<>();
    ArrayList<Integer> listPages = new ArrayList<>();
    int counterOfRequests, limit = 0, sumLimit = 0, hole, sizeOfHole, sizeOfPage, numberFrames, numberOfHoles, numberOfFullPage, numberOfFullPage1, c = 0;

    public void generator(int sizeOfMemory, MainClass mainClass, boolean isSegmentation) {
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
            listRequests.add(new ProgramInfoClass(i, 0, limit));
        }

        if (isSegmentation) {
            hole = sizeOfMemory - sumLimit;
            sizeOfHole = hole / counterOfRequests;
            listRequests.get(0).setPBase(0);

            for (int i = 1; i < counterOfRequests; i++) {
                listRequests.get(i).setPBase(listRequests.get(i - 1).getPBase() + listRequests.get(i - 1).getPLimit() + sizeOfHole);
            }
            showChart(mainClass, true, counterOfRequests * 2);

            counterOfRequests = 0;
            limit = 0;
            sumLimit = 0;
            hole = 0;
            sizeOfHole = 0;
            listRequests.clear();
        } else {
            // do paging

            sizeOfPage = sumLimit / counterOfRequests;
            numberFrames = sizeOfMemory / sizeOfPage;
            numberOfHoles = (sizeOfMemory - sumLimit) / sizeOfPage;
            numberOfFullPage = numberFrames - numberOfHoles;
            numberOfFullPage1 = numberOfFullPage;

            for (int i = 0; i < numberFrames; i++) {
                if (i % 2 == 0 && numberOfFullPage1 > 0) {
                    listPages.add(c);
                    c++;
                    numberOfFullPage1--;
                } else {
                    listPages.add(-1);
                }
            }
            showChart(mainClass, false, numberFrames);

            sizeOfPage = 0;
            sumLimit = 0;
            counterOfRequests = 0;
            numberOfFullPage = 0;
            numberOfHoles = 0;
            numberOfFullPage1 = 0;
            c = 0;
            numberFrames = 0;
            listPages.clear();
        }
    }

    private void showChart(MainClass mainClass, boolean isSegmentation, int counter) {
        removeAllElementsOfJFrame(mainClass);
        mainClass.panel.setLayout(null);
        mainClass.panel.setLayout(new GridLayout(counter, 1));
        mainClass.jButtons = new JButton[counter];
        JScrollPane jScrollPane = new JScrollPane(mainClass.panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainClass.getContentPane().add(jScrollPane);
        mainClass.setLocationRelativeTo(mainClass.getOwner());
        for (int i = 0; i < counter; i++) {
            createButtons(mainClass, i, isSegmentation);
        }
        mainClass.updateJFrame();
    }

    public void createButtons(MainClass mainClass, int count, boolean isSegmentation) {
        mainClass.jButtons[count] = new JButton();
        mainClass.jButtons[count].setSize(20, 80);
        mainClass.jButtons[count].setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (isSegmentation) {
            if (count == 0) {
                mainClass.jButtons[count].setActionCommand("OS" +
                        "\nBase: " + listRequests.get(0).getPBase() +
                        "\nLimit: " + listRequests.get(0).getPLimit());
                mainClass.jButtons[count].setBackground(Color.green);
                mainClass.jButtons[count].setText("OS");
            } else if (count % 2 != 0) {
                mainClass.jButtons[count].setActionCommand("Size of hole: " + sizeOfHole);
                mainClass.jButtons[count].setBackground(Color.RED);
                mainClass.jButtons[count].setText("Hole");
            } else {
                mainClass.jButtons[count].setActionCommand("Segment " + listRequests.get(count / 2).getPId() +
                        "\nBase: " + listRequests.get(count / 2).getPBase() +
                        "\nLimit: " + listRequests.get(count / 2).getPLimit());
                mainClass.jButtons[count].setBackground(Color.green);
                mainClass.jButtons[count].setText("Segment: " + count / 2);
            }
        } else {
            if (count == 0) {
                mainClass.jButtons[count].setActionCommand("Size of OS: " + sizeOfPage);
                mainClass.jButtons[count].setBackground(Color.green);
                mainClass.jButtons[count].setText("OS");
            } else if (listPages.get(count) == -1) {
                mainClass.jButtons[count].setActionCommand("Size of Hole: " + sizeOfPage);
                mainClass.jButtons[count].setBackground(Color.RED);
                mainClass.jButtons[count].setText("Hole");
            } else {
                mainClass.jButtons[count].setActionCommand("Size of page: " + sizeOfPage);
                mainClass.jButtons[count].setBackground(Color.green);
                mainClass.jButtons[count].setText("PAGE: " + listPages.get(count));
            }
        }
        mainClass.jButtons[count].addActionListener(e -> {
            String choice = e.getActionCommand();
            JOptionPane.showMessageDialog(null, choice, "Process Info", JOptionPane.INFORMATION_MESSAGE);
        });
        mainClass.panel.add(mainClass.jButtons[count]);
        SwingUtilities.updateComponentTreeUI(mainClass);
        mainClass.updateJFrame();
    }

    public void removeAllElementsOfJFrame(MainClass mainClass) {
        mainClass.panel.removeAll();
        mainClass.updateJFrame();
    }
}
