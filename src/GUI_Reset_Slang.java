import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import static java.awt.Component.CENTER_ALIGNMENT;

public class GUI_Reset_Slang extends JFrame implements ActionListener {
    slang_Function slang;
    JButton btnReturn;
    file_Slang fl = new file_Slang();
    String temp;
    public GUI_Reset_Slang() throws IOException{
        slang = new slang_Function();
        fl = new file_Slang();
        Container con = this.getContentPane();
        slang.setSlang(fl.readSlang("slang_goc.txt"));
        PrintWriter writer = new PrintWriter("slang.txt", "UTF-8");
        writer.write("Slag`Meaning\n"); // header for text file
        for (Map.Entry<String, ArrayList<String>> entry : slang.getSlang().entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            if (value == null) {
                writer.write(key + "`\n");
                continue;
            }
            // build string for definition
            String output = "";
            if (value.size() <= 1) {
                output += value.get(0);
            } else {
                for (String s : value) {
                    if (s.equals(value.get(value.size() - 1))) {
                        output += s;
                        break;
                    }
                    output += s + "| ";
                }
            }
            writer.write(key + "`" + output + "\n");
        }
        writer.close();
        btnReturn = new JButton("Sucess");
        btnReturn.addActionListener(this);

        this.add(btnReturn);
        this.setVisible(true);
        this.setSize(100, 100);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnReturn)
            this.dispose();
        new GUI();
    }
}
