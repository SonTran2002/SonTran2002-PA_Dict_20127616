import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
public class GUI_Quiz   extends JFrame implements ActionListener{

    JButton btnReturn, btnExit;
    file_Slang file_slang;
    slang_Function slang;
    JButton b1,b2,btnBack,ans1,ans2,ans3,ans4;
    GUI_Quiz()throws IOException {
        JLabel label = new JLabel("Quiz");
        label.setForeground(Color.green);
        label.setFont(new Font("Serif", Font.PLAIN, 35));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setAlignmentY(-100);
        // Add space
        // A Grid
        b1 = new JButton("1. Find Definition");
        slang=new slang_Function();
        file_slang=new file_Slang();
        slang.setSlang(file_slang.readSlang("slang.txt"));
        b1.setFont(new Font("Serif", Font.PLAIN, 18));
        JPanel panelCenter = new JPanel();

        panelCenter.setLayout(new GridLayout(1, 2, 10, 10));
        panelCenter.add(b1);
        b2 = new JButton("2. Find SlangWord");

        b2.setFont(new Font("Serif", Font.PLAIN, 18));
        panelCenter.add(b2);
        Dimension size2 = new Dimension(500, 200);
        panelCenter.setMaximumSize(size2);
        panelCenter.setPreferredSize(size2);
        panelCenter.setMinimumSize(size2);

        // Button back

        btnBack = new JButton("Back");
        btnBack.addActionListener(this);

        JPanel buttonPane = new JPanel();
        buttonPane.add(btnBack);

        // Setting content
        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 100)));
        con.add(label);
        con.add(Box.createRigidArea(new Dimension(0, 100)));
        con.add(panelCenter);

        con.add(Box.createRigidArea(new Dimension(0, 100)));
        con.add(buttonPane);

        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Random random = new Random();
                    int rand = random.nextInt(slang.getSlang().size());
                    String quest= null;
                    String ans= null;
                    // now traverse through hash map
                    int i,k;
                    i = k = 0;
                    // create definition for 3 wrong answer
                    String[] def_ans = new String[4];
                    Integer[] def_ans_index = new Integer[]{random.nextInt(slang.getSlang().size()),random.nextInt(slang.getSlang().size()),
                            random.nextInt(slang.getSlang().size()),random.nextInt(slang.getSlang().size())};
                    Arrays.sort(def_ans_index);
                    int correctChoiceIndex = random.nextInt(4);
                    for (Map.Entry<String, ArrayList<String>> entry : slang.getSlang().entrySet()) {
                        if (i == rand) {
                            quest = entry.getValue().get(random.nextInt(entry.getValue().size()));
                            ans = entry.getKey();
                            def_ans[correctChoiceIndex] = ans;
                        }
                        if (k < 4 && def_ans_index[k] == i){
                            if (k == correctChoiceIndex){
                                k++;
                                continue;
                            }
                            def_ans[k] = entry.getKey();
                            k++;
                        }
                        i++;
                    }
                    int n = JOptionPane.showOptionDialog(null, "Which answer below means " + quest + "?"
                            , "Definition Quiz",0,JOptionPane.QUESTION_MESSAGE, null,def_ans,def_ans[0]);
                    if (n!= -1) {
                        if (def_ans[n].equals(ans)) {
                            JOptionPane.showMessageDialog(null, "Correct Answer!!!");
                        } else {
                            JOptionPane.showMessageDialog(null,"Wrong, correct answer for " + quest
                                    + " is\n - " + ans,"Failed",0);
                        }
                    }
                }
                catch (NullPointerException nullPointerException) {
                } catch (Exception E) {
                }
            }
        });

        b2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Random random = new Random();
                    int rand = random.nextInt(slang.getSlang().size());
                    String quest= null;
                    String ans= null;
                    // now traverse through hash map
                    int i,k;
                    i = k = 0;
                    // create definition for 3 wrong answer
                    String[] def_ans = new String[4];
                    Integer[] def_ans_index = new Integer[]{random.nextInt(slang.getSlang().size()),random.nextInt(slang.getSlang().size()),
                            random.nextInt(slang.getSlang().size()),random.nextInt(slang.getSlang().size())};
                    Arrays.sort(def_ans_index);
                    int correctChoiceIndex = random.nextInt(4);
                    for (Map.Entry<String, ArrayList<String>> entry : slang.getSlang().entrySet()) {
                        if (i == rand) {
                            quest = entry.getKey();
                            ans = entry.getValue().get(random.nextInt(entry.getValue().size()));
                            def_ans[correctChoiceIndex] = ans;
                        }
                        if (k < 4 && def_ans_index[k] == i){
                            if (k == correctChoiceIndex){
                                k++;
                                continue;
                            }
                            def_ans[k] = entry.getValue().get(random.nextInt(entry.getValue().size()));
                            k++;
                        }
                        i++;
                    }
                    int n = JOptionPane.showOptionDialog(null, "Nghĩa của " + quest + " là?"
                            , "Slang Quiz",0,JOptionPane.QUESTION_MESSAGE, null,def_ans,def_ans[0]);
                    if (n!= -1) {
                        if (def_ans[n].equals(ans)) {
                            JOptionPane.showMessageDialog(null, "Chính xác!!!");
                        } else {
                            JOptionPane.showMessageDialog(null,"Đáp án sai " + quest
                                    + " nghĩa là:\n - " + ans,"Failed",0);
                        }
                    }
                }
                catch (NullPointerException nullPointerException) {
                } catch (Exception E) {
                }
            }
        });

        this.setTitle("Quiz choose mode");
        this.setVisible(true);
        this.setSize(700, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            this.dispose();
            new GUI();
        }
        if (e.getSource() == b1) {
            this.dispose();
            try {
                new GUI_Quiz();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == b2) {
            this.dispose();
            try {
                new GUI_Quiz();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
