import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI_Slang extends JFrame implements ActionListener {
    JButton btnReturn, btnExit;
    slang_Function slang;
    JLabel label1 = new JLabel("Slang Word");
    JLabel label2 = new JLabel("Definition");
    file_Slang fl = new file_Slang();
    private JTextArea jTextArea;
    private JTextField search_input;
    private JList slangList;
    private DefaultListModel listModel;

    public GUI_Slang() throws IOException {
        slang = new slang_Function();
        fl = new file_Slang();
        slang.setSlang(fl.readSlang("slang.txt"));
        this.setTitle("Slang Word Function");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        setPreferredSize(new Dimension(720, 480));
        setMinimumSize(new Dimension(720, 480));
        setResizable(false);
        setLayout(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JPanel left_pn = new JPanel();
        left_pn.setLayout(new GridBagLayout());
        left_pn.add(Box.createRigidArea(new Dimension(10, 0)), gbc);
        gbc.gridx++;
        left_pn.add(label1, gbc);
        gbc.gridy++;

        listModel = new DefaultListModel<Set<String>>();
        listModel.addAll(slang.getSlang().keySet());
        slangList = new JList<Set<String>>(listModel);
        slangList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    ArrayList<String> result = slang.getSlang().get(slangList.getSelectedValue().toString());
                    jTextArea.setText(null);
                    for (String str : result) {
                        jTextArea.append(str + "\n");
                    }
                } catch (Exception ex) {
                    //ex.printStackTrace();
                }
            }
        });
        JScrollPane slangListScroll = new JScrollPane(slangList);
        slangListScroll.setPreferredSize(new Dimension(200, 270));
        gbc.gridx = 0;
        left_pn.add(Box.createRigidArea(new Dimension(10, 0)), gbc);
        gbc.gridx++;
        left_pn.add(slangListScroll, gbc);
        gbc.gridy++;
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new FlowLayout());
        JButton delete_btn = new JButton("Delete");
        JButton add_btn = new JButton("Add");
        JButton editSlang_btn = new JButton("Edit");
        //Action event for delete button
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String slangw = slangList.getSelectedValue().toString();
                    int op = JOptionPane.showConfirmDialog(null, "Confirm to delete '"
                            + slangw + "'", "Delete", JOptionPane.OK_CANCEL_OPTION);
                    if (op == JOptionPane.OK_OPTION) {
                        slang.getSlang().remove(slangw);
                        saveSlang("slang.txt");
                        JOptionPane.showMessageDialog(null, "Deleted '"
                                + slangw + "'");
                        refreshSlangList();
                    }
                } catch (NullPointerException nullPointerException) {
                } catch (Exception E) {
                }
            }
        });

        editSlang_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = JOptionPane.showInputDialog(null, "Edit slang",
                            slangList.getSelectedValue().toString());
                    if (!input.equals(null)) {    //OK/Cancel option, cancel option return null
                        if (slang.getSlang().containsKey(input)) {
                            int op = JOptionPane.showConfirmDialog(null, "New edited slang duplicates " +
                                            "'" + input + "'" +
                                            " in dictionary!\nWarning: Perform " +
                                            "overwriting will delete all former definitions and carry" +
                                            " current edited slang's definitions over!!!", "Duplicate warning",
                                    JOptionPane.OK_CANCEL_OPTION);
                            if (op == JOptionPane.OK_OPTION) {
                                slang.getSlang().put(input, slang.getSlang().get(slangList.getSelectedValue().toString()));
                                slang.getSlang().remove(slangList.getSelectedValue().toString());
                                saveSlang("slang.txt");
                                refreshSlangList();
                            }
                        } else {
                            slang.getSlang().put(input, slang.getSlang().get(slangList.getSelectedValue().toString()));
                            slang.getSlang().remove(slangList.getSelectedValue().toString());
                            saveSlang("slang.txt");
                            refreshSlangList();
                        }
                    }
                } catch (NullPointerException nullPointerException) {
                } catch (Exception E) {
                }
            }
        });
        // action event for add button
        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add new slang here

                String input = JOptionPane.showInputDialog(null, "Add slang");
                if (!input.equals(null)) {    //OK/Cancel option, cancel option return null
                    if (slang.getSlang().containsKey(input)) {
                        int op = JOptionPane.showConfirmDialog(null, "New slang found" +
                                " in dictionary\nOK.      Ghi de Slang cu \nCancel. Them Slang moi" , "Slang confirmation", JOptionPane.OK_CANCEL_OPTION);
                        if (op == JOptionPane.OK_OPTION) {
                            String newDef = JOptionPane.showInputDialog(null, "nhap definition" + input);
                            if (slang.getSlang().containsKey(input)) {
                                ArrayList<String> def = slang.getSlang().get(input);
                                if(def==null){
                                    def=new ArrayList<>();
                                }
                                def.add(newDef);
                                slang.getSlang().remove(input);
                                slang.getSlang().put(input, def);
                            }
                            try {
                                saveSlang("slang.txt");
                            } catch (Exception E) {
                            }
                        }
                        if(op == JOptionPane.CANCEL_OPTION){
                            ArrayList<String> def = slang.getSlang().get(input);
                            if(def==null){
                                def=new ArrayList<>();
                            }
                            String newDef = JOptionPane.showInputDialog(null, "nhap definition");
                            String dup= input+ "new";
                            def.add(newDef);
                            slang.getSlang().put(dup, def);
                            try {
                                saveSlang("slang.txt");
                            } catch (Exception E) {
                            }
                        }
                    } else {
                        slang.getSlang().put(input, null);
                        try {
                            saveSlang("slang.txt");
                        } catch (Exception E) {
                        }
                    }
                }
                refreshSlangList();
            }
        });
        button_panel.add(add_btn);
        button_panel.add(editSlang_btn);
        button_panel.add(delete_btn);
        gbc.gridx = 0;
        left_pn.add(Box.createRigidArea(new Dimension(10, 0)), gbc);
        gbc.gridx++;
        left_pn.add(button_panel, gbc);
        add(left_pn, BorderLayout.WEST);

        gbc.gridy = 0;
        gbc.gridx = 0;

        // create middle pannel for
        JPanel middle_pn = new JPanel();
        middle_pn.setLayout(new GridBagLayout());
        middle_pn.add(label2, gbc);
        gbc.gridy++;
        jTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setPreferredSize(new Dimension(480, 308));
        middle_pn.add(jScrollPane, gbc);
        add(middle_pn, BorderLayout.CENTER);

        //create bottom pannel
        JPanel bottom_pn = new JPanel();
        bottom_pn.setPreferredSize(new Dimension(100, 70));
        bottom_pn.setLayout(new FlowLayout());

        JComboBox search_criteria = new JComboBox(new String[]{"Search by slang", "Search by definition"});
        bottom_pn.add(search_criteria);
        //bottom_pn.add(Box.createRigidArea(new Dimension(50, 0)));
        search_input = new JTextField(30);
        bottom_pn.add(search_input);
        JButton search_btn = new JButton("Search");

        search_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (search_input.getText().equals("")){
                    refreshSlangList();
                    jTextArea.setText("");
                    return;
                }
                if (search_criteria.getSelectedItem().toString().equals("Search by slang")) {
                    listModel.clear();
                    Pattern pattern = Pattern.compile(Pattern.quote(search_input.getText()), Pattern.CASE_INSENSITIVE);
                    for (String key : slang.getSlang().keySet()) {
                        Matcher matcher = pattern.matcher(key);
                        if (matcher.find()) {
                            listModel.addElement(key);
                        }
                    }
                    // write to history file
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    try {
                        PrintWriter writer = new PrintWriter(new FileOutputStream(new File("history.txt"),
                                true));
                        writer.append("\n"+formatter.format(date)+" : -" + search_input.getText()+"-Search by Slang");
                        writer.close();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                        //done
                    }
                }
                else{   // search by definition
                    listModel.clear();
                    Pattern pattern = Pattern.compile(Pattern.quote(search_input.getText()), Pattern.CASE_INSENSITIVE);
                    for (Map.Entry<String, ArrayList<String>> entry : slang.getSlang().entrySet()) {
                        String key = entry.getKey();
                        ArrayList<String> value = entry.getValue();
                        if (value == null){
                            listModel.addElement(key);
                            continue;
                        }
                        for (String str : value) {
                            Matcher matcher = pattern.matcher(str);
                            if (matcher.find()) {
                                listModel.addElement(key);
                            }
                        }
                    }

                    // write to history file
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    try {
                        PrintWriter writer = new PrintWriter(new FileOutputStream(new File("history.txt"),
                                true));
                        writer.append("\n"+formatter.format(date)+" : -" + search_input.getText()+" -Search by Definition");
                        writer.close();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                        //done
                    }
                }
            }
        });
        JButton editDef_btn = new JButton("Edit Definition");
        editDef_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // allow performing definition edit here
                String slangw= slangList.getSelectedValue().toString();
                ArrayList<String> slangd = new ArrayList<>();

                // each definition is split by a \n
                String definitions = jTextArea.getText();

                for (String def : definitions.split("\n")){
                    slangd.add(def);
                }
                // now put them to hash map
                slang.getSlang().remove(slangw);
                slang.getSlang().put(slangw,slangd);
                try{
                    saveSlang("slang.txt");
                }catch(Exception E){}
            }
        });
        bottom_pn.add(search_btn);
        bottom_pn.add(editDef_btn);

        btnReturn = new JButton("Return");
        btnExit = new JButton("Exit");
        btnReturn.addActionListener(this);
        btnExit.addActionListener(this);

        bottom_pn.add(btnReturn);
        bottom_pn.add(btnExit);
        this.add(bottom_pn,BorderLayout.SOUTH);
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExit) {
            System.exit(0);
        } else if (e.getSource() == btnReturn) {
            this.dispose();
            new GUI();
        }
    }

    private void refreshSlangList() {
        listModel.clear();
        jTextArea.setText("");
        listModel.addAll(slang.getSlang().keySet());
    }

    private void saveSlang(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
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
    }

}
