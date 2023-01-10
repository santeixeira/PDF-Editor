package com.pdfeditor.compressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompressor {

    public static void zipCompressor () throws IOException {
        String folderName = "output";
        File folder = new File(folderName);
        FileOutputStream fos = new FileOutputStream("output.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        addFolderToZip(folder, folderName, zipOut);
        deleteDirectory(folder);

        zipOut.close();
        fos.close();
    }

    private static void addFolderToZip(File folder, String baseName, ZipOutputStream zipOut) throws IOException {
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                addFolderToZip(file, baseName + "/" + file.getName(), zipOut);
            } else {
                String fileName = baseName + "/" + file.getName();
                FileInputStream fis = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);
                
                byte[] bytes = new byte[1024];
                int length;

                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
        }
    }

    private static boolean deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for(File file : files) {
                if(file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return directory.delete();
    }
}
