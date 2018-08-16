package cn.leta.common.poi.writer;

import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 * Created by xiegengcai on 2018/5/31.
 *
 * @author Xie Gengcai
 */
public interface IRowWriter<T> {
    /**
     * 写表头
     * @param row
     * @param headers
     */
    default void writeHeader(Row row, List<String> headers){
        for (int i = 0; i < headers.size(); i++) {
            row.createCell(i).setCellValue(headers.get(i));
        }
    }
    /**
     * 写入行数据
     * @param row
     * @param data
     */
    void writeRow(Row row, T data);
}
