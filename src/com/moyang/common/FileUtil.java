package com.moyang.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * This util class helps File Operations.
 *
 */
public class FileUtil {

    protected FileUtil(){
        
    }
    
    /**
     * Read file content
     * @param path
     * @return
     */
    public static String getContent(String path){
        
        StringBuffer buffer = new StringBuffer();
        BufferedReader bfr = null;
        
        try {
            FileReader fr = new FileReader(path);

            bfr = new BufferedReader(fr);
                        
            String line = "";
            while ((line = bfr.readLine()) != null) {
                buffer.append(line + "\n");
            }

        } catch(Exception e) {
            System.out.println("Failed to read file " + path + " with error Message: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            closeBufferedReader(bfr);
        }
        
        return buffer.toString();
    }
    
    
    /**
     * This function is created for convenient UT.
     * @param bfr
     */
    public static void closeBufferedReader(BufferedReader bfr) {
        if (bfr != null) {
            try {
                bfr.close();
            } catch (IOException e) {
            }
        }
    }
    
    /**
     * Create file and its parent directory.
     */
    public static boolean createFile(File file) throws IOException{
        if(!file.exists()){
            makeDir(file.getParentFile());
        }
        return file.createNewFile();
    }

    /**
     * Create directory and its parent directories.
     * @param dir
     */
    public static boolean makeDir(File dir){
        if(!dir.getParentFile().exists()){
            makeDir(dir.getParentFile());
        }
        
        return dir.mkdir();
    }
   
    /**
     * Write content to path.
     * @param path
     * @param content
     */
    public static void writeToFile(String path, String content) throws IOException{
        File file = new File(path);

        createFile(file);
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            fos.write(content.getBytes("UTF-8"));
        } finally {
            if(fos != null) {
                closeFileOutputStream(fos);
            }
        } 
    }

    /**
     * This function is created for convenient UT.
     * @param fos
     */
    public static void closeFileOutputStream(FileOutputStream fos){
        try {
            fos.close();
        } catch (Exception e) {

        }
    }
    
    /**
     * Append Content to file
     * 
     * @param path
     * @param content
     * @throws IOException
     */
    public static void appendToFile(String path, String content) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(path), true);
            fos.write(content.getBytes());
        } finally {
            if (fos != null) {
                closeFileOutputStream(fos);
            }
        }
    }
 
    public static File[] getFilesUnderDir(String dir) {
    	File root = new File(dir);
    	if(!root.isDirectory()) {
    		return null;
    	}
    	return root.listFiles();
    }
    
    public static void main(String[] args) throws Exception {
        String rawInput = FileUtil.getContent("com/moyang/files/rawInput.txt");
        System.out.println(rawInput);

        String[] list = rawInput.split("\\)");
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (String tmp : list) {
            int index = tmp.indexOf("(");
            if (index < 0) {
                break;
            }
            count++;
            sb.append(tmp.substring(index + 1, tmp.length()) + "\t" + tmp.substring(0, index).trim() + "\n");

        }
        FileUtil.writeToFile("com/moyang/files/Stocks.txt", sb.toString());
        System.out.println(count);

    }


}

