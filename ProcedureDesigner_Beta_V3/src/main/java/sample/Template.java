package sample;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;

public class Template {
    public Template(XWPFDocument Temp,String name,String path) throws IOException {
        Temp.write(new FileOutputStream(path+"//"+name));
    }
}
