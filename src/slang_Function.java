import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class slang_Function {
    Map<String, ArrayList<String>> slang;
    String Search;

    public Map<String, ArrayList<String>> getSlang() {
        return slang;
    }

    public void setSlang(Map<String, ArrayList<String>> slang) {
        this.slang = slang;
    }
    public void searchByDefinition(Map<String, ArrayList<String>> slang) throws IOException {
        String find;
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap Definition can tim`");

        this.Search = sc.nextLine();
        Pattern pattern = Pattern.compile(Search, Pattern.CASE_INSENSITIVE);
        for (Map.Entry<String, ArrayList<String>> entry : slang.entrySet()) {
            ArrayList<String> key = entry.getValue();
            for (String temp : key) {
                Matcher matcher = pattern.matcher(temp);
                if (matcher.find()) {
                    System.out.println(key);
                }
            }
        }
        FileWriter fw;
        try {
            File file = new File("history.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
        } catch (IOException e) {
            System.out.println("File not exist");
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String buf = formatter.format(date) + ": -" + Search + " -Search by Definition\n";
        fw.write(buf);
        fw.close();
    }

    public void searchBySlang(Map<String, ArrayList<String>> slang) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap slang can tim`");
        this.Search = sc.nextLine();
        Pattern pattern = Pattern.compile(Search, Pattern.CASE_INSENSITIVE);
        for (String key : slang.keySet()) {
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()) {
                System.out.println(key);
            }
        }
        FileWriter fw;
        try {
            File file = new File("history.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
        } catch (IOException e) {
            System.out.println("File not exist");
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String buf = formatter.format(date) + ": -" + Search + " -Search by Slang\n";
        fw.write(buf);
        fw.close();
    }

    public void addSlang(Map<String, ArrayList<String>> slang) {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap slang can them:");
        this.Search = sc.nextLine();
        if (slang.containsKey(Search)) {
            int i;
            System.out.println("Slang da ton tai vui long lua chon:");
            System.out.println("1. ghi de Slang cu");
            System.out.println("2. them Slang moi");
            i = sc.nextInt();
            if (i == 1) {
                System.out.println("nhap definition:");
                ArrayList<String> def = slang.get(Search);
                String add = sc.nextLine();
                def.add(add);
                slang.remove(Search);
                slang.put(Search, def);
            }
            if (i == 2) {
                System.out.println("nhap definition:");
                ArrayList<String> def = new ArrayList<>();
                String add = sc.nextLine();
                def.add(add);
                String dup=Search + "new";
                slang.put(dup, def);
            }
        }
        else{
            System.out.println("nhap definition:");
            ArrayList<String> def = new ArrayList<>();
            String add = sc.nextLine();
            def.add(add);
            slang.put(Search, def);
        }
    }

}
