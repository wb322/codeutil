package com.bitvalue.edgecache.tools;

import java.io.*;
import java.util.*;

/**
 * @author wubo
 */
public class FileUtils {

    /**
     * 读文件
     * @param filePath
     * @return
     */
    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<> ();
        try (BufferedReader br = new BufferedReader (new FileReader (filePath))){
            String line = null;
            while((line = br.readLine ()) != null){
                lines.add (line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return lines;
        }
    }

    /**
     * 改文件
     * @param filePath
     * @param lines
     */
    public static boolean editFile(String filePath,List<String> lines) {
        try {
            BufferedWriter bw = new BufferedWriter (new FileWriter (filePath + ".swp"));
            for (String s : lines) {
                bw.write (s);
                bw.newLine ();
            }
            bw.flush ();
            bw.close ();
            File file = new File (filePath);
            file.delete ();
            File tempFile = new File (filePath + ".swp");
            return tempFile.renameTo (file);
        } catch (Exception e) {
            e.printStackTrace ();
            return false;
        }
    }

}
