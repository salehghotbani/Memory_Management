import javax.swing.*;
import java.awt.*;

public class MainClass extends JFrame {
    private final String helpText = "";
    private final SegmentationClass segmentationClass = new SegmentationClass();
    private final PagingClass pagingClass = new PagingClass();
    private int sizeOfMemory = 0;
    JPanel panel;
    JButton[] jButtons;

    private void frame(MainClass mainClass) {
        JPanel panel = new JPanel();
        JButton buttonRun = new JButton("Start");
        JButton buttonHelp = new JButton("Help");
        JLabel JLSizeOfMemory = new JLabel(" Size of memory: ");
        JTextField JTFSizeOfMemory = new JTextField();
        JMenuBar menuBar = new JMenuBar();

        String[] schedulingMethods = {"Paging", "Segmentation"};
        JComboBox<String> comboBox = new JComboBox<>(schedulingMethods);

        buttonRun.addActionListener(e -> {
            switch (comboBox.getItemAt(comboBox.getSelectedIndex())) {
                case "Paging":
                    sizeOfMemory = Integer.parseInt(JTFSizeOfMemory.getText());
                    pagingClass.generator(sizeOfMemory, mainClass);
                    break;
                case "Segmentation":
                    sizeOfMemory = Integer.parseInt(JTFSizeOfMemory.getText());
                    segmentationClass.generator(sizeOfMemory, mainClass);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Choose one of Paging or Segmentation"
                            , "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateJFrame();
        });

        buttonHelp.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, helpText, "Help", JOptionPane.INFORMATION_MESSAGE);
        });

        menuBar.add(comboBox);
        menuBar.add(JLSizeOfMemory);
        menuBar.add(JTFSizeOfMemory);
        menuBar.add(buttonRun);
        menuBar.add(buttonHelp);

        setTitle("Memory Allocation");
        setJMenuBar(menuBar);
        panel.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTFSizeOfMemory.setBounds(10, 10, 0, 0);
        setBounds(0, 0, 600, 400);
        add(panel);
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/Files/LOGO.png"));
        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(jScrollPane);
        setLocationRelativeTo(getOwner());
        pack();
    }

    public void updateJFrame() {
        SwingUtilities.updateComponentTreeUI(this);
        invalidate();
        validate();
        repaint();
        pack();
    }

    public static void main(String[] args) {
        try {
            MainClass mainClass = new MainClass();
            mainClass.frame(mainClass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error has occurred!\nPlease Run program again!\n" +
                    "Error Message: " + e, "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
