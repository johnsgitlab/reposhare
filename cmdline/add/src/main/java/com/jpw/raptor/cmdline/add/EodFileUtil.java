package com.jpw.raptor.cmdline.add;

import com.jpw.raptor.lib.properties.FinanceProperties;
import com.jpw.raptor.model.FileQualified;

import java.io.File;
import java.util.*;

/**
 * Created by john on 5/15/18.
 */
public class EodFileUtil {

    public List<FileQualified> getFileList() {

        // import directory
        FinanceProperties fp   = new FinanceProperties();
        Properties prop = fp.get();

        String dir  = prop.getProperty("import_dir");
        if ( dir == null )
        {
            System.out.println("Import directory not specified");
            return null;
        }

        // create a file that is really a directory
        File aDirectory = new File(dir);

        // get a listing of all files in the directory
        String[] filesInDir = aDirectory.list();

        // Create a map where the key is date and equity type
        // value is the full path
        Map<String, String> unsortMap = new HashMap<>();
        for ( String f : filesInDir ) {
            int dash        = f.indexOf('_');
            int period      = f.indexOf('.');
            String key      = f.substring(dash+1, period) + "_" + f.substring(0, dash);
            String fileName = dir + "/" + f;
            File   dataFile = new File(fileName);
            if ( dataFile.isFile() ) {

                unsortMap.put(key, fileName);
            }
        }

        // Create a sorted map
        Map<String, String> treeMap = new TreeMap<>(unsortMap);

        // create an output list
        List<FileQualified> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            String type = entry.getKey().substring(entry.getKey().indexOf('_')+1);
            list.add(new FileQualified(type, entry.getValue()));
        }

        return list;
    }

    public void deleteFile (String srcPath )
    {
        try
        {

            File file = new File(srcPath);

            if ( file.delete() )
            {
                System.out.println(file.getName() + " is deleted!");
            }
            else
            {
                System.out.println("Delete operation is failed.");
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
