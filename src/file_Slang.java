import java.util.*;
import java.io.*;
public class file_Slang {
    public Map<String, ArrayList<String>> readSlang(String filename) throws IOException {
        Map<String, ArrayList<String>> result = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String data;
            while ((data = br.readLine()) != null) {
                // nếu file khác rỗng tiếp tục đọc xuống dứoi
                if (data.equals("Slag`Meaning")) {
                    //nếu là dòng đầu thì bỏ qua
                    continue;
                }
                if (data.contains("`")) {
                    ArrayList<String> def = new ArrayList<>();
                    String[] token = data.split("(`)");
                    // dòng đầu là slang
                    // dòng hai là những định nghĩa của nó
                    try{
                        if (token[1].contains("|")) {
                            String[] definitions = token[1].split("\\| ");

                            for (String definition : definitions) {
                                def.add(definition);
                            }
                            result.put(token[0], def);
                            // nếu có nhiều định nghĩa thì put từng cái vào def
                        } else {
                            def.add(token[1]);
                            result.put(token[0], def);
                        }}catch(ArrayIndexOutOfBoundsException AIOOBE){result.put(token[0],null);}
                }
            }
            return result;
        }
        catch(IOException exc)
        {
            System.out.println("File not exist");
        }
        return result;
    }

    public static void ReadFile(Map<String, ArrayList<String>> s) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("history.txt"));
        String temp;
        while ((temp = br.readLine()) != null){
            System.out.println(temp);
        }

    }

}

