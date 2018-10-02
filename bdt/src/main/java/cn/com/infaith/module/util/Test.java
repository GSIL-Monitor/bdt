package cn.com.infaith.module.util;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/2.
 */
@Component
@Configurable
@EnableScheduling
public class Test {
    //    @Scheduled(cron = "0 */1 *  * * * ")
    public void testScheduled() {
        System.out.println(10001);
    }

    public static void main(String[] args) throws Exception {
//        Process process = Runtime.getRuntime().exec("D:\\software\\pdf2htmlEX-win32-0.14.6-upx-with-poppler-data\\pdf2htmlEX.exe --first-page 1 --last-page 1 --dest-dir d:\\pages  d:\\1.pdf 1.html");
//        int w = process.waitFor();
//        int v = process.exitValue();
//        if (w == 0 && v == 0) {
//            System.out.println("执行成功！");
//        }
//        File file = new File("D:\\2.pdf");
//        PDDocument document = PDDocument.load(file);
//        document.setAllSecurityToBeRemoved(true);
//        document.save("D:\\2.pdf");
//        int pages = document.getNumberOfPages();
//        for (int i = 0; i < pages; i++) {
//            PDFTextStripper stripper = new PDFTextStripper();
//            stripper.setSortByPosition(true);
//            stripper.setStartPage(i);
//            stripper.setEndPage(i + 1);
//            stripper.getText(document);
//        }
//        PDPage page=new PDPage();
//        Pdf2HtmlEXUtil.pdf2html("D:\\software\\pdf2htmlEX-win32-0.14.6-upx-with-poppler-data\\pdf2htmlEX.exe", "D:\\2.pdf", "D:\\test", "test.html");
//        PDDocument document = PDDocument.load(file);
//        PDPage page = new PDPage(PDRectangle.A4);
//        document.addPage(page);
//        // Adobe Acrobat uses Helvetica as a default font and
//        // stores that under the name '/Helv' in the resources dictionary
//        PDFont font = PDType1Font.HELVETICA;
//        PDResources resources = new PDResources();
//        resources.put(COSName.getPDFName("Helv"), font);
//        // Add a new AcroForm and add that to the document
//        PDAcroForm acroForm = new PDAcroForm(document);
//        document.getDocumentCatalog().setAcroForm(acroForm);
//        // Add and set the resources and default appearance at the form level
//        acroForm.setDefaultResources(resources);
//        // Acrobat sets the font size on the form level to be
//        // auto sized as default. This is done by setting the font size to '0'
//        String defaultAppearanceString = "/Helv 0 Tf 0 g";
//        acroForm.setDefaultAppearance(defaultAppearanceString);
//        // --- end of general AcroForm stuff ---
//        // Create empty signature field, it will get the name "Signature1"
//        PDSignatureField signatureField = new PDSignatureField(acroForm);
//        PDAnnotationWidget widget = signatureField.getWidgets().get(0);
//        PDRectangle rect = new PDRectangle(50, 650, 200, 50);
//        widget.setRectangle(rect);
//        widget.setPage(page);
//        page.getAnnotations().add(widget);
//        acroForm.getFields().add(signatureField);
//        document.setAllSecurityToBeRemoved(true);
//        document.save("D:\\EmptySignatureForm.pdf");
//        File file = new File("D:\\2.pdf");
//        File[] fileArray = file.listFiles();
//        String temp = "";
//        String ts="";
//        PDDocument pdfdocument = PDDocument.load(file);
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        OutputStreamWriter writer = new OutputStreamWriter(out);
//        PDFTextStripper stripper = new PDFTextStripper();
//
//        stripper.writeText(pdfdocument, writer);
//
//        pdfdocument.close();
//        out.close();
//        writer.close();
//        byte[] contents = out.toByteArray();
//        ts = new String(contents);
//        for (int i = 0; i < fileArray.length; i++) {
//            if (fileArray[i].getName().contains(".pdf")) {
//                System.out.println(fileArray[i].getName());
//                PDDocument document = null;
//                document = PDDocument.load(fileArray[i]);
//                int pages = document.getNumberOfPages();
//                for (int j = 1; j < pages+1; j++) {
//                    PDFTextStripper stripper = new PDFTextStripper();
//                    stripper.setSortByPosition(true);
//                    stripper.setStartPage(j);
//                    stripper.setEndPage(j+1);
//                    String content = stripper.getText(document);
//                    File newFile = new File("D:\\txt\\" + fileArray[i].getName().replace(".pdf", "")+j + ".txt");
//                    PrintWriter pw = new PrintWriter(newFile);
//                    pw.print(content);
//                    pw.close();
//                }
//
//            } else {
//                continue;
//            }
//        }

    }

    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }
}
