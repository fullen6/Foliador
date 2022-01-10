package com.munaysoft.foliador.pdf;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.ParagraphRenderer;
import com.itextpdf.layout.renderer.TableRenderer;
import com.munaysoft.foliador.modelo.FoliadorModelo;
import com.munaysoft.foliador.utilidad.NumberToLetterConverter;
import com.munaysoft.foliador.utilidad.NumberToRomanos;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static com.munaysoft.foliador.utilidad.ConstantesFoliador.*;

public class CrearPdf {
    public static void manipulatePdf(String dest, FoliadorModelo foliadorModelo) throws Exception {
        PdfWriter pdfWriter = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        if(foliadorModelo.getOrientarPagina() == PAGINA_VERTICAL)
            pdfDoc.setDefaultPageSize(PageSize.A4.rotate());
        agregarPaginas(pdfDoc, foliadorModelo.getInicio(), foliadorModelo.getFin(), foliadorModelo.getIntervalo());
        Document doc = new Document(pdfDoc);
        agregarNumero(doc, foliadorModelo);

        doc.close();
    }

    private static void agregarPaginas(PdfDocument pdfDoc, int inicio, int fin, int intervalo) {
          for (int i = inicio; i <= fin; i = i + intervalo)
                pdfDoc.addNewPage();
    }

    private static void agregarNumero(Document doc, FoliadorModelo foliadorModelo) {
        int numeroHojas = doc.getPdfDocument().getNumberOfPages();
        int intervaloLocal = foliadorModelo.getInicio();
        for (int i = 1; i <= numeroHojas; i++) {

            PdfPage pdfPage = doc.getPdfDocument().getPage(i);
            Rectangle pageSize = pdfPage.getPageSizeWithRotation();
            PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);

            Table table = new Table(3);


            String texto = crearTexto(foliadorModelo, intervaloLocal);
            Paragraph paragraph = new Paragraph();
            try {
                alinearPargraph(paragraph, texto, foliadorModelo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            posicionCelda(doc, table, foliadorModelo.getPosicionPagina(), paragraph);

            Rectangle rect = getPosicionTabla(doc, pageSize, table, foliadorModelo.getPosicionPagina());

            removeBorder(table);

            new Canvas(pdfCanvas, rect)
                    .add(table)
                    .close();
            intervaloLocal = intervaloLocal + foliadorModelo.getIntervalo();
        }
    }

    private static Rectangle getPosicionTabla(Document doc, Rectangle pageSize, Table table, int posicionPagina) {
        float coordY = 0;
        float coordX = pageSize.getX() + doc.getLeftMargin();
        float width = pageSize.getWidth() - doc.getRightMargin() - doc.getLeftMargin();

        TableRenderer renderer = (TableRenderer) table.createRendererSubTree();
        renderer.setParent(new DocumentRenderer(doc));

        // Simulate the positioning of the renderer to find out how much space the header table will occupy.
        LayoutResult result = renderer.layout(new LayoutContext(new LayoutArea(0, PageSize.A4)));
        float height = result.getOccupiedArea().getBBox().getHeight();

        switch (posicionPagina) {
            case ALINEAR_TOP_IZQUIERDA:
            case ALINEAR_TOP_CENTRO:
            case ALINEAR_TOP_DERECHA:
                coordY = pageSize.getTop()-height-doc.getTopMargin();
                break;
            case ALINEAR_BOTTON_IZQUIERDA:
            case ALINEAR_BOTTON_CENTRO:
            case ALINEAR_BOTTON_DERECHA:
                coordY = pageSize.getBottom()+doc.getBottomMargin();
                break;

        }
        return new Rectangle(coordX, coordY, width, height);
    }

    private static String crearTexto(FoliadorModelo foliadorModelo, int pagina) {
        StringBuilder stringBuilder = new StringBuilder();
        if (foliadorModelo.getLineaUno() != LINEA_NINGUNO) {
            String texto = construirLinea(foliadorModelo.getLineaUno(), pagina, foliadorModelo.getRellenoIzquierda(),
                    foliadorModelo.getTextoAdicionalIzquierda(), foliadorModelo.getTextoAdicionalDerecha(),
                    foliadorModelo.getExpresionLiteral(), foliadorModelo.getTextoAdicionalLiteralIzquierda(),
                    foliadorModelo.getTextoAdicionalLiteralDerecha(), false, foliadorModelo.getTipoNumero(),
                    foliadorModelo.getControlMayusculaMinuscula());
            stringBuilder.append(texto);
        }
        if (foliadorModelo.getLineaDos() != LINEA_NINGUNO) {
            String texto = construirLinea(foliadorModelo.getLineaDos(), pagina, foliadorModelo.getRellenoIzquierda(),
                    foliadorModelo.getTextoAdicionalIzquierda(), foliadorModelo.getTextoAdicionalDerecha(),
                    foliadorModelo.getExpresionLiteral(), foliadorModelo.getTextoAdicionalLiteralIzquierda(),
                    foliadorModelo.getTextoAdicionalLiteralDerecha(), true, foliadorModelo.getTipoNumero(),
                    foliadorModelo.getControlMayusculaMinuscula());
            stringBuilder.append(texto);
        }
        return stringBuilder.toString();
    }

    private static String construirLinea(int linea, int pagina, int rellenoIzquierda, String textoAdicionalIzquierda, String textoAdicionalDerecha,
                                         int expresionLiteral, String textoAdicionalLiteralIzquierda, String textoAdicionalLiteralDerecha,
                                         boolean isLineados, int tipoNumero, int controlMayusculaMinuscula) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (linea) {
            case LINEA_NUMERO: {
                String rellenoFormateado;
                if (tipoNumero == NUMERO_ROMANO)
                    rellenoFormateado = NumberToRomanos.generate(pagina);
                else
                    rellenoFormateado = rellenoIzquierda(pagina, rellenoIzquierda);
                if (isLineados)
                    stringBuilder.append("\n");
                stringBuilder.append(textoAdicionalIzquierda)
                        .append(rellenoFormateado)
                        .append(textoAdicionalDerecha);
                break;
            }
            case LINEA_LITERAL: {
                String numeroLiteral = NumberToLetterConverter.convertNumberToLetter(String.valueOf(pagina), expresionLiteral);
                numeroLiteral = capitalizarLetra(controlMayusculaMinuscula, numeroLiteral);
                if (isLineados)
                    stringBuilder.append("\n");
                stringBuilder.append(textoAdicionalLiteralIzquierda)
                        .append(numeroLiteral.trim())
                        .append(textoAdicionalLiteralDerecha);
                break;
            }
        }
        return stringBuilder.toString();
    }

    private static String capitalizarLetra(int controlMayusculaMinuscula, String texto) {
        texto = StringUtils.lowerCase(texto);
        switch (controlMayusculaMinuscula) {
            case CONTROL_PRIMERA_MAYUSCULA:
                texto = StringUtils.capitalize(texto);
                break;
            case CONTROL_TODO_MAYUSCULA:
                texto = StringUtils.upperCase(texto);
                break;
            case CONTROL_TODO_MINUSCULA:
                texto = StringUtils.lowerCase(texto);
                break;
        }
        return texto;
    }

    private static void alinearPargraph(Paragraph paragraph, String texto, FoliadorModelo foliadorModelo) throws IOException {

        PdfFont font = PdfFontFactory.createFont(foliadorModelo.getFuente());
        paragraph.add(texto);
        paragraph.setFontSize(foliadorModelo.getFuenteTamanio());
        paragraph.setFont(font);
        switch (foliadorModelo.getJustificacion()) {
            case JUSTIFICACION_IZQUIERDA:
                paragraph.setTextAlignment(TextAlignment.LEFT);
                break;
            case JUSTIFICACION_CENTRO:
                paragraph.setTextAlignment(TextAlignment.CENTER);
                break;
            case JUSTIFICACION_DERECHA:
                paragraph.setTextAlignment(TextAlignment.RIGHT);
                break;
        }
    }

    private static void posicionCelda(Document doc, Table table, int posicion, Paragraph paragraph) {
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.useAllAvailableWidth();
        float maxWidth = getRealParagraphWidth(doc, paragraph);
        Cell cell = new Cell();
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell.add(paragraph);
        cell.setWidth(maxWidth);
        switch (posicion) {
            case ALINEAR_TOP_IZQUIERDA:
            case ALINEAR_BOTTON_IZQUIERDA:
                table.addCell(cell);
                table.addCell("");
                table.addCell("");
                break;
            case ALINEAR_TOP_CENTRO:
            case ALINEAR_BOTTON_CENTRO:
                table.addCell("");
                table.addCell(cell);
                table.addCell("");
                break;
            case ALINEAR_TOP_DERECHA:
            case ALINEAR_BOTTON_DERECHA:
                table.addCell("");
                table.addCell("");
                table.addCell(cell);
                break;
        }
    }

    private static void removeBorder(Table table) {
        for (IElement iElement : table.getChildren()) {
            ((Cell) iElement).setBorder(Border.NO_BORDER);
        }
    }


    private static float getRealParagraphWidth(Document doc, Paragraph paragraph) {
        IRenderer paragraphRenderer = paragraph.createRendererSubTree();
        LayoutResult result = paragraphRenderer.setParent(doc.getRenderer()).
                layout(new LayoutContext(new LayoutArea(1, new Rectangle(1000, 100))));
        return ((ParagraphRenderer) paragraphRenderer).getMinMaxWidth().getMaxWidth();
    }

    private static String rellenoIzquierda(int valor, int rellenoTamanio) {
        return String.format("%0" + rellenoTamanio + "d", valor);
    }
}

