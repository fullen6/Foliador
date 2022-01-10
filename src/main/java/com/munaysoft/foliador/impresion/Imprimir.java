package com.munaysoft.foliador.impresion;

import javafx.collections.ObservableSet;
import javafx.print.*;
import javafx.scene.Node;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Locale;


public class Imprimir {
    public static void main(String[] args) {
        ObservableSet<Printer> printers = Printer.getAllPrinters();
        for (Printer printer : printers) {
            System.out.println(printer.getName());
        }
    }


    public void start(Node node, Path path) throws PrintException, FileNotFoundException {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        boolean ejecutar = printerJob.showPrintDialog(node.getScene().getWindow());

        if (ejecutar) {
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(new Copies(printerJob.getJobSettings().getCopies()));
            printRequestAttributeSet.add(new JobName("Foliador", Locale.getDefault()));
            printRequestAttributeSet.add(MediaSizeName.ISO_A4);
            printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
            printRequestAttributeSet.add(printerJob.getJobSettings().getPrintColor() == PrintColor.COLOR ? Chromaticity.COLOR : Chromaticity.MONOCHROME);
            if (printerJob.getJobSettings().getPageRanges() != null)
                for (PageRange pageRange : printerJob.getJobSettings().getPageRanges()) {
                    printRequestAttributeSet.add(new PageRanges(pageRange.getStartPage(), pageRange.getEndPage()));
                }
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            FileInputStream fis = new FileInputStream(path.toString());
            Doc mydoc = new SimpleDoc(fis, flavor, null);
            DocPrintJob job = getPrintService(printerJob.getPrinter().getName()).createPrintJob();
            job.print(mydoc, printRequestAttributeSet);
            printerJob.endJob();
        } else {
            printerJob.cancelJob();
        }
    }

    private PrintService getPrintService(String name) {
        for (PrintService printService : java.awt.print.PrinterJob.lookupPrintServices()) {
            if (name.equalsIgnoreCase(printService.getName())) {
                return printService;
            }
        }
        return null;
    }
}
