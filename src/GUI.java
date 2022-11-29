import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GUI extends JFrame implements ActionListener {
    private JLabel headerLabel;
    private JPanel t;
    private JPanel panel;
    private JButton b1, b2, b3, b4, b5, b6, b7, b8;
    Container container;

    GUI() {
        container = this.getContentPane();
        this.setTitle("Menu Window");
        this.setVisible(true);
        this.setSize(650, 500);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        JLabel text = new JLabel("SLANG WORD");
        text.setAlignmentX(CENTER_ALIGNMENT);
        panel = new JPanel();
        text.setFont(new Font("Serif", Font.BOLD, 30));
        text.setForeground(Color.red);
        b1 = new JButton("Slang Function");
        b2 = new JButton("History Search");
        b3 = new JButton("Reset Slang");
        b4 = new JButton("Random Slang");
        b5 = new JButton("Quiz Slang");
        b6 = new JButton("Exit");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

        b1.setFocusable(false);
        b2.setFocusable(false);
        b3.setFocusable(false);
        b4.setFocusable(false);
        b5.setFocusable(false);
        b6.setFocusable(false);

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);

        panel.setLayout(new GridLayout(4, 2, 5, 5));
        container.add(text);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(panel);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            this.dispose();
            try {
                new GUI_Slang();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            this.dispose();
            try {
                new GUI_History();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == b3) {
            this.dispose();
            try {
                new GUI_Reset_Slang();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == b4) {
            this.dispose();
            try {
                new GUI_Random();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == b5) {
            this.dispose();
            try {
                new GUI_Quiz();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == b6) {
            this.dispose();
            try {
                System.exit(0);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
