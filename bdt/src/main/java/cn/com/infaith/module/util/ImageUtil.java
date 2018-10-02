package cn.com.infaith.module.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@Configuration
@PropertySource("classpath:application.properties")
public class ImageUtil {
    private static final String regEx_img = "<img[\\w\\W]*?src=[\"|\']?([\\w\\W]*?)(.jpg|.png|.bmp)[\\w\\W]*?>";//定义图片img标签
    @Value("${new.oss.url}")
    private static String ossUrl;
    //获取图片标签Url
    public static List<String> getUrlInSrc(String content) {
        List<String> imgSet = new ArrayList<String>();
        if(null == content || (content != null && content.length() == 0))
            return null;
        Pattern compile = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compile.matcher(content);
        while (matcher.find()){
            if(matcher.group(1).startsWith("http://121.201.14.216")){
                imgSet.add(matcher.group(1)+matcher.group(2));
            }
        }

        return imgSet;
    }
    //通过图片的URL地址下载到本地并上传到OSS
    public static List<String> downloadPicFromUrl(List<String> imgUrlList){
        List<String> ossImgUrlList = new ArrayList<>();
        //初始化OSS客户端
        OSSClient client = new OSSClient("http://oss-cn-shanghai.aliyuncs.com", "LTAIvBk8HHPSsw0c", "LodXWDLUoj8YxGbBEyu2zoxO4WY5Nk");
        //获取资源库路径
        File rootFile = new File("");
        if(!rootFile.exists()){
            rootFile.mkdirs();
        }
        InputStream dataInputStream = null;
        FileOutputStream fileOutputStream = null;
            File file = null;
            try {
                for (String imgUrl: imgUrlList
                        ) {
                    if(imgUrl.contains("http://121.201.14.216:8221")){
                        imgUrl = imgUrl.replace("http://121.201.14.216:8221","http://121.201.14.216:8220");
                    }
                    URL url = new URL(imgUrl);
                    URLConnection con = url.openConnection();
                    con.setConnectTimeout(5 * 1000);
                    dataInputStream = con.getInputStream();
                    file = new File(rootFile.getAbsolutePath() + "/" + PublicUtil.getUUID() + imgUrl.substring(imgUrl.lastIndexOf(".")));
                    fileOutputStream = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = dataInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, length);
                    }
                    String ossImgUrl = uploadImg2OSS(client, file);
                    ossImgUrlList.add(ossImgUrl);
                    if(file.exists()){
                        file.delete();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(dataInputStream != null){
                        dataInputStream.close();
                    }
                    if(fileOutputStream != null){
                        fileOutputStream.close();
                    }
                    if(client != null){
                        client.shutdown();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return ossImgUrlList;
    }

    //上传图片到OSS
    public static String uploadImg2OSS(OSSClient client,File file){
        String returnUrl = "";
        try {
            InputStream content = new FileInputStream(file);
            ObjectMetadata meta = new ObjectMetadata(); // 上传Object的Metadata
            meta.setContentLength(file.length());
            meta.setContentType("image/jpeg");
            //设置oss文件夹名称
            //String ossDirName = "xiaoan" + "/";
            String imageName = "xiaoan" + PublicUtil.getUUID() + file.getName().substring(file.getName().lastIndexOf("."))  ;
            PutObjectResult putObjectResult = client.putObject("an-announcement", "data/contentImage/"+imageName, content,meta);
            //returnUrl = "http://an-announcement.oss-cn-shanghai.aliyuncs.com/data/contentImage/"+ imageName;
            returnUrl = ossUrl + "data/contentImage/" + imageName;
            //System.out.println(putObjectResult.getResponse());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return returnUrl;
    }

    public static void main(String[] args) {
        String content = "<div class='content-abs'><p>根据中国证监会<img src=\"http://121.201.14.216:8220/kbase-converter/DATAS/third/biztpl/201703/1490586574744.png\">《关于执行〈亏损上市公司暂停上市和终止上市实施办法（修订）〉的补充规定》（证监公司字［2003］6号）和《关于做好股份有限公司终止上市后续工作的指导意见》<img src=\"http://121.201.14.216:8220/kbase-converter/DATAS/third/biztpl/201703/1490586561661.png\">（证监公司字［2004］6号），为进一步明确股份有限公司依法终止上市后股份转让等相关事宜，特拟定《〈证券上市协议〉补充协议》和《〈证券登记及服务协议〉补充协议》。</p>\n";
        List<String> src = getUrlInSrc(content);
        for (String img: src
                ) {
            System.out.println(img);
        }
        List<String> list = downloadPicFromUrl(src);
        for (String url: list) {
            System.out.println(url);
        }
    }
}
