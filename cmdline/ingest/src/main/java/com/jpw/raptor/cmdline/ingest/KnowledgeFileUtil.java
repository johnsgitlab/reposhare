package com.jpw.raptor.cmdline.ingest;

import com.jpw.raptor.lib.properties.FinanceProperties;
import com.jpw.raptor.model.FileQualified;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by john on 5/15/18.
 */
public class KnowledgeFileUtil {

    public List<String> getFileList() {

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

        // create an output list
        List<String> list = new ArrayList<String>();

        for ( String f : filesInDir ) {
            String fileName = dir + "/" + f;
            File   dataFile = new File(fileName);
            if ( dataFile.isFile() ) {
                list.add(fileName);
            }
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

    public void moveFile(String src, String dest)  throws IOException {

        FileUtils.moveFile(
                FileUtils.getFile(src),
                FileUtils.getFile(dest));
    }

    public void moveFileToDir(String src, String dest) throws IOException {

        FileUtils.moveFileToDirectory(
                FileUtils.getFile(src),
                FileUtils.getFile(dest), true);
    }

    public void delFile(File fileToDelete) throws IOException {

        boolean success = FileUtils.deleteQuietly(fileToDelete);

        // or

        //FileUtils.forceDelete(fileToDelete);

    }

    public List<File> listFilesInDirAndSubDir() throws IOException {

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

        System.out.println("Getting all files in " + aDirectory.getCanonicalPath() + " including those in subdirectories");
        List<File> files = (List<File>) FileUtils.listFiles(aDirectory, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

        return files;
    }
}
