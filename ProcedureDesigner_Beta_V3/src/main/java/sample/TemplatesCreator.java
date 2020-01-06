package sample;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;

public class TemplatesCreator {
    String JD_AR_NameFile = "JobDescriptionTemplate-AR.dotx";
    String JD_EN_NameFile = "JobDescriptionTemplate-EN.dotx";
    String P_AR_NameFile = "ProcedureTemplate-AR.dotx";
    String P_EN_NameFile = "ProcedureTemplate-EN.dotx";
    String Path = "";
    XWPFDocument Default_JD_AR_Template = new XWPFDocument(OPCPackage.open("Job-description-template-V6-ar.dotx"));
    XWPFDocument Default_JD_EN_Template = new XWPFDocument(OPCPackage.open("Job-description-template-V6.dotx"));
    XWPFDocument Default_P_AR_Template = new XWPFDocument(OPCPackage.open("Procedure-template.dotx"));
    XWPFDocument Default_P_EN_Template = new XWPFDocument(OPCPackage.open("Procedure-template.dotx"));

    public TemplatesCreator(String path) throws InvalidFormatException, IOException {
        Path = path;
    }

    public void CreateAllTemplate() throws IOException {
        Create_JD_AR_Template();
        Create_JD_EN_Template();
        Create_P_AR_Template();
        Create_P_EN_Template();
    }

    public void Create_JD_AR_Template() throws IOException {
        Template JD_AR_Template = new Template(Default_JD_AR_Template, JD_AR_NameFile, Path);
    }

    public void Create_JD_EN_Template() throws IOException {
        Template JD_EN_Template = new Template(Default_JD_EN_Template, JD_EN_NameFile, Path);
    }

    public void Create_P_AR_Template() throws IOException {
        Template P_AR_Template = new Template(Default_P_AR_Template, P_AR_NameFile, Path);
    }

    public void Create_P_EN_Template() throws IOException {
        Template P_EN_Template = new Template(Default_P_EN_Template, P_EN_NameFile, Path);
    }

}
