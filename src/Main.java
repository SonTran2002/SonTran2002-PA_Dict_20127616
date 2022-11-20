import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        file_Slang fl=new file_Slang();
        Map<String, ArrayList<String>> result = new HashMap<>();
        result=fl.readSlang("slang.txt");
        System.out.println(result);
    }
}