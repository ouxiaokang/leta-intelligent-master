package cn.leta.common.poi.reader;

import java.io.File;
import java.io.InputStream;

/**
 * Created by xiegengcai on 2018/5/31.
 *
 * @author Xie Gengcai
 */
public interface ExcelReader {
    void setRowReader(IRowReader rowReader);

    /**
     * 以文件名的方式遍历excel下所有的sheet
     * @param fileName
     * @throws Exception
     */
    void process(String fileName) throws Exception;

    /**
     * 以文件的方式遍历excel下所有的sheet
     * @param file
     * @throws Exception
     */
    void process(File file) throws Exception;

    /**
     * 以流的方式遍历excel下所有的sheet
     * @param inputStream
     */
    void process(InputStream inputStream);
}
