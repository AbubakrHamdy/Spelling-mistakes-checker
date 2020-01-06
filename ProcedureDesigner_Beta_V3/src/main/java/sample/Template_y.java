package sample;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Template_y {
    private static String template_path ;
    private XWPFDocument doc;
    private XWPFDocument doc1;

    private static List<XWPFParagraph> paragraphs1;
    private String save_path;
    private File f;
    Template_y(String path, String template_path) throws IOException, InvalidFormatException {
        save_path = path;
        this.template_path = template_path;
        f = new File(template_path);
        OPCPackage pkg = OPCPackage.open(f);
        pkg.replaceContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.template.main+xml",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml");
        doc = new XWPFDocument(pkg);
        doc1 = doc;
    }

    void replaceText(String keyword, String value) throws Exception {
        for (XWPFParagraph p : doc1.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    System.out.println(r.getText(0));
                    if (text != null && text.contains(keyword)) {
                        text = text.replace(keyword, value);

//                        r.setText(text, 0);
                        setText(r,text);
                        //r.setBold(true);
                        r.setFontFamily("JQRNOE Times New Roman Italic");
                        r.setFontSize(14);
                        r.setColor("000000");
                        System.out.println(r.getText(0));
                    }
                }
            }
        }
        for (XWPFTable tbl : doc1.getTables()) {
            System.out.println("hellooooooooo1");

            for (XWPFTableRow row : tbl.getRows()) {
                System.out.println("hellooooooooo2");

                for (XWPFTableCell cell : row.getTableCells()) {
                    System.out.println("hellooooooooo3");

                    for (XWPFParagraph p : cell.getParagraphs()) {
                        System.out.println("hellooooooooo4");

                        for (XWPFRun r : p.getRuns()) {
                            System.out.println("hellooooooooo5");

                            String text = r.getText(0);
                            System.out.println(r.getText(0));

                            if (text != null && text.contains(keyword)) {
                                text = text.replace(keyword, value);
                                setText(r,text);
                                r.setFontFamily("JQRNOE Times New Roman Italic");
                                r.setFontSize(14);
                                r.setColor("000000");
                                System.out.println("hellooooooooo6");

                            }
                        }
                    }
                }
            }
        }
    }
    void saveWord() throws Exception {
        //run.addBreak();
        //run.addPictur, XWPFDocument.PICTURE_TYPE_PNG, screenshot_name, Units.toEMU(350), Units.toEMU(350));
        // captureScreenShot(picName);
     /*   XWPFParagraph paragraph ;
        paragraph =doc1.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addTab();
        run = paragraph.createRun();
        String imgFile="xsxs.png";
       // run.addPicture(new FileInputStream(imgFile), XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(50), Units.toEMU(50));*/
        OutputStream fos = new FileOutputStream(save_path);
        doc1.write(fos);
        // doc.close();
        //   pkg.close();
    }
    void setText(XWPFRun run, String data){
        if (data.contains("\n")) {
            String[] lines = data.split("\n");

            run.setText(lines[0], 0); // set first line into XWPFRun
            for(int i=1;i<lines.length;i++){
                // add break and insert new text

                run.addCarriageReturn(); //ADD THE NEW LINE
                run.setText(lines[i]);
            }
        } else {
            run.setText(data, 0);
        }


    }


}