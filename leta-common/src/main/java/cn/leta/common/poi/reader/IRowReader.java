package cn.leta.common.poi.reader;

import java.util.List;

/**
 * Created by xiegengcai on 2018/5/31.
 *
 * @author Xie Gengcai
 */
public interface IRowReader {
    void getRows(int sheetIndex, int curRow, List<String> rowlist);
}
