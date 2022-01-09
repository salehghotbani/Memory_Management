import java.util.ArrayList;
import java.util.Random;

public class SegmentationClass {
    Random random = new Random();
    ArrayList<ProgramInfoClass> listRequests = new ArrayList<>();

    public void generator(int sizeOfMemory, MainClass mainClass) {
        int counterOfRequests, limit = 0;

        if (sizeOfMemory > 1000) {
            counterOfRequests = random.nextInt((Math.abs(sizeOfMemory / 1000) - 2)) + 3;
        } else {
            counterOfRequests = random.nextInt(2) + 3;
        }

        int sumLimit = 0;
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

        int hole = sizeOfMemory - sumLimit;
        int sizeOfHole = hole / (counterOfRequests - 1);
        listRequests.get(0).setPBase(0);

        for (int i = 1; i < counterOfRequests; i++) {
            listRequests.get(i).setPBase(listRequests.get(i - 1).getPBase() + listRequests.get(i - 1).getPLimit() + sizeOfHole);
            System.out.println(listRequests.get(i - 1).getPBase() + listRequests.get(i - 1).getPLimit() + sizeOfHole + ":   " + i);
        }
        showChart(mainClass);
    }

    private void showChart(MainClass mainClass) {
        private JPanel panel = new JPanel(new GridLayout(100, 1000));
        private JButton[] jButtons = new JButton[100000];
        private int count = 0;

            public FramesProcesses() {
            setTitle("Processes");
            setBackground(java.awt.Color.lightGray);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setPreferredSize(new Dimension(800, 400));
            add(panel);
            setVisible(true);
            JScrollPane jScrollPane = new JScrollPane(panel);
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            getContentPane().add(jScrollPane);
            setLocationRelativeTo(getOwner());
            pack();
        }
    }

    public void createButtons(ArrayList<ProcessClass> listOfItems, int i) {
        String str = "Process ID:" + listOfItems.get(i).getPId() + "\nArrival Time: " + listOfItems.get(i).getPArrivalTime()
                + "\nPriority: " + listOfItems.get(i).getPPriority() + "\nBurst Time: " + listOfItems.get(i).getPBurstTime();

        jButtons[count] = new JButton();
        jButtons[count].setSize(80, 80);
        jButtons[count].setBackground(Color.green);
        jButtons[count].setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtons[count].setActionCommand(str);
        jButtons[count].setText("Process:" + listOfItems.get(i).getPId());
        jButtons[count].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String choice = e.getActionCommand();
                JOptionPane.showMessageDialog(null, choice, "Process Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(jButtons[count]);
        count++;
        SwingUtilities.updateComponentTreeUI(this);
        revalidate();
        repaint();
        pack();
    }

    public void removeAllElementsOfJFrame() {
        panel.removeAll();
    }
}
