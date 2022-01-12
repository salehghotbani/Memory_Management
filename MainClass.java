import javax.swing.*;
import java.awt.*;

public class MainClass extends JFrame {
    private final MemoryManagerClass memoryManagerClass = new MemoryManagerClass();
    private int sizeOfMemory = 0;
    JPanel panel = new JPanel();
    JButton[] jButtons;
    JButton buttonRun = new JButton("Start");
    JButton buttonHelp = new JButton("Help");
    JLabel JLSizeOfMemory = new JLabel(" Size of memory: ");
    JTextField JTFSizeOfMemory = new JTextField();
    JMenuBar menuBar = new JMenuBar();
    JEditorPane editorPane = new JEditorPane();

    private void frame(MainClass mainClass) {
        String[] schedulingMethods = {"Paging", "Segmentation"};
        JComboBox<String> comboBox = new JComboBox<>(schedulingMethods);

        buttonRun.addActionListener(e -> {
            switch (comboBox.getItemAt(comboBox.getSelectedIndex())) {
                case "Paging":
                    if (checkNumber(JTFSizeOfMemory.getText())) {
                        sizeOfMemory = Integer.parseInt(JTFSizeOfMemory.getText());
                        memoryManagerClass.generator(sizeOfMemory, mainClass, false);
                    }
                    break;
                case "Segmentation":
                    if (checkNumber(JTFSizeOfMemory.getText())) {
                        sizeOfMemory = Integer.parseInt(JTFSizeOfMemory.getText());
                        memoryManagerClass.generator(sizeOfMemory, mainClass, true);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Choose one of Paging or Segmentation"
                            , "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateJFrame();
        });

        buttonHelp.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Choose one of elements are in combobox\n" +
                    "then write size of memory and click Start!\nYou can click on any rectangle that shows after click " +
                    "start to see some information about segment or page!", "Help", JOptionPane.INFORMATION_MESSAGE);
        });

        menuBar.add(comboBox);
        menuBar.add(JLSizeOfMemory);
        menuBar.add(JTFSizeOfMemory);
        menuBar.add(buttonRun);
        menuBar.add(buttonHelp);

        panel.add(editorPane);
        editorPane.setSize(500, 500);
        editorPane.setEditable(false);
        setTitle("Memory Allocation");
        setJMenuBar(menuBar);
        panel.setBackground(Color.white);
        add(panel);
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/Files/LOGO.png"));
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private boolean checkNumber(String input) {
        try {
            if (Integer.parseInt(input) < 30) {
                JOptionPane.showMessageDialog(this, "Please write number grater than 30"
                        , "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error has occurred!\nCause maybe:\n" +
                    "1. You don't wrote anything!\n" +
                    "2. You wrote digit with anything else!\n" +
                    "3. You wrote number less than 30\n"+
                    "Error message: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void updateJFrame() {
        SwingUtilities.updateComponentTreeUI(this.panel);
        panel.invalidate();
        panel.validate();
        panel.repaint();
        panel.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        invalidate();
        validate();
        repaint();
        revalidate();
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
