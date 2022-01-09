import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class SegmentationClass {
   Random random = new Random();
    int sizeofpage,frames,numberofholes,numberoffullpage,numberoffullpage1;
    ArrayList<ProgramInfoClass> listRequests = new ArrayList<>();
    int counterOfRequests, limit = 0, sumLimit = 0, hole, sizeOfHole;

    public void generator(int sizeOfMemory, MainClass mainClass,boolean issegment) {
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

        if(issegment){

            hole = sizeOfMemory - sumLimit;
            sizeOfHole = hole / counterOfRequests;
            listRequests.get(0).setPBase(0);

            for (int i = 1; i < counterOfRequests; i++) {
                listRequests.get(i).setPBase(listRequests.get(i - 1).getPBase() + listRequests.get(i - 1).getPLimit() + sizeOfHole);
                System.out.println(listRequests.get(i - 1).getPBase() + listRequests.get(i - 1).getPLimit() + sizeOfHole + ":   " + i);
            }
        showChart(mainClass);
        }
        else {
           // do paging
                int c=0;
                 sizeofpage=sumLimit/counterOfRequests;
            System.out.println(sizeofpage+" scccccccccccccccccccc");
                 frames=sizeOfMemory/sizeofpage;
              numberofholes=(sizeOfMemory-sumLimit)/sizeofpage;
            numberoffullpage=frames-numberofholes;
               numberoffullpage1=numberoffullpage;
                ArrayList<Integer> listPages = new ArrayList<>();
                for(int i=0;i<numberoffullpage;i++){
                    if(i%2==0&&numberoffullpage1>0){
                        listPages.add(c);
                        c++;
                        numberoffullpage1--;

                    }
                    else {
                        listPages.add(0);

                    }

                }





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
            for (int i = 0; i < frames ; i++) {
                createButtons1(mainClass, i,listPages);
            }
            mainClass.pack();
        }
        ////////////////////////////////////


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
        for (int i = 0; i < counterOfRequests*2; i++) {
            createButtons(mainClass, i);
        }
        mainClass.pack();
    }
    public void createButtons1(MainClass mainClass, int count,ArrayList<Integer> list) {
        mainClass.jButtons[count] = new JButton();
        mainClass.jButtons[count].setSize(20, 80);
        mainClass.jButtons[count].setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (list.get(count)==0) {
            mainClass.jButtons[count].setActionCommand("Size of page: " + sizeofpage);
            mainClass.jButtons[count].setBackground(Color.RED);
            mainClass.jButtons[count].setText("Hole");
        } else {
            mainClass.jButtons[count].setActionCommand("Size of page: " + sizeofpage);
            mainClass.jButtons[count].setBackground(Color.green);
            mainClass.jButtons[count].setText("PAGE: " + list.get(count));
        }
        mainClass.jButtons[count].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String choice = e.getActionCommand();
                JOptionPane.showMessageDialog(null, choice, "Process Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mainClass.panel.add(mainClass.jButtons[count]);
        count++;
        SwingUtilities.updateComponentTreeUI(mainClass);
        mainClass.updateJFrame();
    }
    public void createButtons(MainClass mainClass, int count) {
        mainClass.jButtons[count] = new JButton();
        mainClass.jButtons[count].setSize(20, 80);
        mainClass.jButtons[count].setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (count % 2 != 0) {
            mainClass.jButtons[count].setActionCommand("Size of hole: " + sizeOfHole);
            mainClass.jButtons[count].setBackground(Color.RED);
            mainClass.jButtons[count].setText("Hole");
        } else {
            mainClass.jButtons[count].setActionCommand("Segment " + listRequests.get(count / 2).getPId() + "\nBase: " + listRequests.get(count / 2).getPBase() +
                    "\nLimit: " + listRequests.get(count / 2).getPLimit());
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
        count++;
        SwingUtilities.updateComponentTreeUI(mainClass);
        mainClass.updateJFrame();
    }

    public void removeAllElementsOfJFrame(MainClass mainClass) {
        mainClass.panel.removeAll();
    }
}
