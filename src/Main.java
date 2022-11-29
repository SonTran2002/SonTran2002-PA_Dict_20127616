import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args)throws IOException {
        file_Slang fl=new file_Slang();
        slang_Function s=new slang_Function();
        s.setSlang(fl.readSlang("slang.txt"));
        GUI gui=new GUI();
    }
}