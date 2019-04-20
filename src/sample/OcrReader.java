package sample;

import java.io.File;
import net.sourceforge.tess4j.*;

public class OcrReader {

    public static void main(String[] args) throws Exception {
        String inputFilePath = "C:/Users/Kaushik's/Desktop/testocr.png";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:/Tesseract/");
        String fullText = tesseract.doOCR(new File(inputFilePath));
        System.out.println(fullText);
    }
}