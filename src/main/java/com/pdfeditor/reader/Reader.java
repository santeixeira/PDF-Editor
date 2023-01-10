package com.pdfeditor.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

public class Reader {

    public static void reader() throws IOException {
        String path = "input/";
        File inputFile = new File(path + pathName(path));
        PDDocument document = PDDocument.load(inputFile);

        Splitter splitter = new Splitter();

        List<PDDocument> pages = splitter.split(document);
        Iterator<PDDocument> iterator = pages.listIterator();

        File folder = new File("output");
        boolean success = folder.mkdir();

        if (success) {
            System.out.println("A pasta output foi criada com sucesso");
        } else {
            System.out.println("Falha ao criar a pasta");
        }

        int i = 1;
        while (iterator.hasNext()) {
            PDDocument pd = iterator.next();
            pd.save("output/pagina-" + (i++) + ".pdf");
        }

        document.close();
    }

    private static String pathName(String path) {
        List<String> results = new ArrayList<String>();
        File[] files = new File("input").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results.get(0);
    }
}
