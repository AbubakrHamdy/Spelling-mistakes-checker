package Entities;

import java.io.File;
import java.util.ArrayList;

public class Images {
    public static ArrayList<String> GetImages(){
        ArrayList<String> Filesnames=new ArrayList<String>();
        String[] pathnames;

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File f = new File("D:\\PD\\Images");

        // Populates the array with names of files and directories
        pathnames = f.list();

        // For each pathname in the pathnames array
        for (String pathname : pathnames) {
            // Print the names of files and directories
            Filesnames.add(pathname);
//            System.out.println(pathname);
        }
        return Filesnames;
    }
}
