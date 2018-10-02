package cn.com.infaith.module.util;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author syc
 * @date 2018/9/13 10:20
 */
public class DbfUtil {
    public static void main(String args[]) throws Exception {
        analysisT1Table("/Users/sunyechen/Movies/股东名册样例/沪市名册样例--新/t16038190320180710all.710");
    }

    public static void analysisT1Table(String filePath) {
        DBFReader reader = null;
        try {
            // create a DBFReader object
            reader = new DBFReader(new FileInputStream(filePath));
            reader.setCharactersetName("GB2312");
            System.out.println(reader.getCharset());
            // get the field count if you want for some reasons like the following
            int numberOfFields = reader.getFieldCount();
            // use this count to fetch all field information
            // if required
            for (int i = 0; i < numberOfFields; i++) {
                DBFField field = reader.getField(i);
                // do something with it if you want
                // refer the JavaDoc API reference for more details
                //
                System.out.println(field.getName());
            }
            // Now, lets us start reading the rows
            Object[] rowObjects;
            while ((rowObjects = reader.nextRecord()) != null) {
                for (int i = 0; i < rowObjects.length; i++) {
                    System.out.println(rowObjects[i]);
                }
            }
            // By now, we have iterated through all of the rows
        } catch (DBFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBFUtils.close(reader);
        }
    }
}
