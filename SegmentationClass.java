import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class SegmentationClass {
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
        showChart(mainClass);
    }

    private void showChart(MainClass mainClass) {
        mainClass.panel = new JPanel(new GridLayout(counterOfRequests * 2, 1));
        mainClass.jButtons = new JButton[counterOfRequests * 2];
        removeAllElementsOfJFrame(mainClass);
        mainClass.setPreferredSize(new Dimension(400, 400));
        mainClass.add(mainClass.panel);
        JScrollPane jScrollPane = new JScrollPane(mainClass.panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainClass.getContentPane().add(jScrollPane);
        mainClass.setLocationRelativeTo(mainClass.getOwner());
        for (int i = 0; i < counterOfRequests * 2; i++) {
            createButtons(mainClass, i);
        }
        mainClass.pack();
    }

    public void createButtons(MainClass mainClass, int count) {
        String str = "Process ID:";

        mainClass.jButtons[count] = new JButton();
        mainClass.jButtons[count].setSize(20, 80);
        mainClass.jButtons[count].setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (count % 2 != 0) {
            mainClass.jButtons[count].setActionCommand(str);
            mainClass.jButtons[count].setBackground(Color.RED);
            mainClass.jButtons[count].setText("Hole");
        } else {
            mainClass.jButtons[count].setActionCommand(str);
            mainClass.jButtons[count].setBackground(Color.green);
            mainClass.jButtons[count].setText("Segment: " + count / 2);
        }
        mainClass.jButtons[count].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String choice = e.getActionCommand();
                JOptionPane.showMessageDialog(null, choice, "Process Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mainClass.panel.add(mainClass.jButtons[count]);
        SwingUtilities.updateComponentTreeUI(mainClass);
        mainClass.updateJFrame();
    }

    public void removeAllElementsOfJFrame(MainClass mainClass) {
        mainClass.panel.removeAll();
    }
}
