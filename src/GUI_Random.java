
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI_Random extends JFrame implements ActionListener {
    JButton btnBack, btnReset;
    slang_Function slang ;
    file_Slang file_slang;
    JLabel lb2, lb4;

    GUI_Random() throws IOException {

        Container con = this.getContentPane();
        slang = new slang_Function();
        file_slang = new file_Slang();
        slang.setSlang(file_slang.readSlang("slang.txt"));
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("SLANG WORD RANDOM  ");
        titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.blue);
        titleLabel.setForeground(Color.white);
        titlePanel.setMaximumSize(new Dimension(500, 300));

        // Slang word
        Random random = new Random();
        int rand = random.nextInt(slang.getSlang().size());
        String slangw = "";
        ArrayList<String> slangd = null;
        int i = 0;
        for (Map.Entry<String, ArrayList<String>> entry : slang.getSlang().entrySet()) {
            if (i == rand) {
                slangw = entry.getKey();
                slangd = entry.getValue();
            }
            i++;
        }
        String output ="";
        for (i = 0; i < slangd.size(); i++) {
            output += " - " + slangd.get(i) + "\n";
        }

        JPanel slangPanel = new JPanel();

        JLabel lb1 = new JLabel("Slang: \t");
        lb2 = new JLabel(slangw);
        JLabel lb3 = new JLabel("\tMeaning: \t");
        lb4 = new JLabel(String.valueOf(slangd));
        lb2.setForeground(Color.BLUE);
        lb4.setForeground(Color.red);
        lb1.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        lb2.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        lb3.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        lb4.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        // slangLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        slangPanel.add(lb1);
        slangPanel.add(lb2);
        slangPanel.add(lb3);
        slangPanel.add(lb4);


        // Bottom btnback btnRenew
        btnReset = new JButton("Reset");
        btnBack = new JButton("Back");
        btnBack.setSize(new Dimension(100, 100));
        btnBack.addActionListener(this);
        btnReset.addActionListener(this);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(btnReset);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(btnBack);

        // Setting con
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(titlePanel);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(slangPanel);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(buttonPane);
        // Setting JFrame
        this.setTitle("Ramdom Slang");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == btnBack) {
            this.dispose();
            new GUI();
        }
        else if (e.getSource() == btnReset) {
            this.dispose();
            try {
                new GUI_Random();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}