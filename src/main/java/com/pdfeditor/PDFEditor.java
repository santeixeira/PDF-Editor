package com.pdfeditor;

import java.io.IOException;

import com.pdfeditor.compressor.ZipCompressor;
import com.pdfeditor.reader.Reader;

public class PDFEditor {
    public static void main(String[] args) throws IOException {
        Reader.reader();
        ZipCompressor.zipCompressor();
    }

}