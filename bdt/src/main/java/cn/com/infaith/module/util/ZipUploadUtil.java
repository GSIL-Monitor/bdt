package cn.com.infaith.module.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUploadUtil {

    private final static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
    private final static String tomcat_path = "/hosts/tomcat/download/";
//    private final static String tomcat_path = "/Users/lbj/Desktop/wenjian/";

    /**
     * 文件打包下载
     *
     * @param files
     * @throws Exception
     */
    public static HttpServletResponse downLoadFiles(List<File> files, HttpServletResponse response) throws Exception {
        try {
            String zipFilename = tomcat_path + sf.format(Calendar.getInstance().getTime()) + ".zip";
            File file = new File(zipFilename);
            if (!file.exists()) {
                file.createNewFile();
            }
            response.reset();
            //创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);
            ZipOutputStream zipOut = new ZipOutputStream(fous);
            zipFile(files, zipOut);
            zipOut.close();
            fous.close();
            return downloadZip(file, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void zipFile(List<File> files, ZipOutputStream outputStream) {
        int size = files.size();
        for (int i = 0; i < size; i++) {
            File file = files.get(i);
            zipFile(file, outputStream);
        }
    }

    /**
     * 将文件输入到zip流
     *
     * @param inputFile
     * @param ouputStream
     */
    public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 1024);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    int nNumber;
                    byte[] buffer = new byte[1024];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 以流的形式下载文件
     *
     * @param file
     */
    public static HttpServletResponse downloadZip(File file, HttpServletResponse response) {
        if (file.exists() == false) {
            System.out.println("待压缩的文件目录：" + file + "不存在.");
        } else {
            try {
                InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                response.reset();
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(file.getName()));
                response.setHeader("Access-Control-Allow-Origin","*");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                file.delete();
            }
        }
        return response;
    }

    /**
     * 转换文件大小
     *
     * @param size
     * @return
     */
    public static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
