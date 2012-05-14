package org.kah;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class Launcher {

    private static enum Letter {
        A, B, C
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        EventQueue.invokeAndWait(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                    displayFrame();
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private static void displayFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Combo box test");

        Container contentPane = frame.getContentPane();
        final JComboBox<Letter> comboBox = new JComboBox<>(Letter.values());

        JPanel panel = new JPanel(new MigLayout());
        panel.add(new JLabel("Combo box: "), "split 2");
        panel.add(comboBox, "wrap, wmin 100px, pushx, grow");

        final JCheckBox editableCheckBox = new JCheckBox("Editable");
        editableCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox.setEditable(editableCheckBox.isSelected());
            }
        });

        final JCheckBox disableCheckBox = new JCheckBox("Disable");
        disableCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox.setEnabled(!disableCheckBox.isSelected());
            }
        });

        JButton output = new JButton(new AbstractAction("Print Selected") {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboBox.getSelectedItem());
            }
        });

        JPanel insertionPanel = new JPanel(new MigLayout("ins 0"));
        final JLabel insertLabel = new JLabel("Insert ");
        final JTextField textField = new JTextField(15);
        textField.setText("Enter text");

        JButton insertByText = new JButton(new AbstractAction("by text field") {

            @Override
            public void actionPerformed(ActionEvent e) {
                ((JTextComponent) comboBox.getEditor().getEditorComponent())
                        .setText(textField.getText());
            }
        });

        JButton insertBySelection = new JButton(new AbstractAction(
                "by selection") {

            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox.setSelectedItem(textField.getText());
            }
        });

        insertionPanel.add(insertLabel);
        insertionPanel.add(textField);
        insertionPanel.add(insertByText, "sg button, wrap");
        insertionPanel.add(insertBySelection, "skip 2, sg button, wrap");

        panel.add(editableCheckBox, "wrap");
        panel.add(disableCheckBox, " wrap");
        panel.add(insertionPanel, "wrap");
        panel.add(output, "wrap");
        contentPane.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
