package com.zyt.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.utils
 * @ClassName: ExcelUploadUtils
 * @Author: zhou_yangtao@yeah.net
 * @Description: excel表格数据上传工具类
 * @Date: 16:33 2021/1/26
 * @Version: 1.0
 */
public class ExcelUploadUtils {

    private Logger logger = LoggerFactory.getLogger(ExcelUploadUtils.class);

    //定义excel表格不同版本的后缀名
    private final static String excel2003L = ".xls"; //2003- 版本
    private final static String excel2007U = ".xlsx"; //2007+ 版本

    /**
     * @Method: getBankListByExcel
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: java.util.List<java.util.List<java.lang.Object>>
     * @Exception:
     * @Date: 2021/1/26 16:35
     * @Param: * @param in
     * @param fileName
     * @Return: java.util.List<java.util.List<java.lang.Object>>
     */
    public List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws  Exception{
        List<List<Object>> list = null;
        //创建Excel工作簿
        Workbook workBook = this.getWorkbook(in,fileName);
        if(null == workBook){
            throw  new Exception("创建工作簿为空");
        }
        Sheet sheet = null;  //页数
        Row row = null; //行数
        Cell cell = null; //列数
        list = new ArrayList<List<Object>>();
        Map<String, PictureData> maplist=null;
        //遍历Excel表格中所有的sheet
        for (int i = 0;i < workBook.getNumberOfSheets();i++){
            sheet = workBook.getSheetAt(i);
            if(sheet == null){continue;}
            //遍历当前sheet中所有行
            for (int j =sheet.getFirstRowNum();j< sheet.getLastRowNum();j++){
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j){continue;}
                //遍历所有列
                List<Object> li = new ArrayList<Object>();
                logger.info("得到的列数为:"+row.getFirstCellNum());
                for (int k = row.getFirstCellNum();i< row.getLastCellNum();k++){
                    cell = row.getCell(k);
                    if((row.getCell(k) != null )){
                        li.add(this.getValue(row.getCell(k)));

                    }else{
                        break;
                        //throw new  Exception("已结没有数据了");
                    }
                }
                list.add(li);
            }
        }
        return  list;
    }

    /**
     * @Method: getWorkbook
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: org.apache.poi.ss.usermodel.Workbook
     * @Exception:
     * @Date: 2021/1/26 16:36
     * @Param: * @param inStr
     * @param fileName
     * @Return: org.apache.poi.ss.usermodel.Workbook
     */
    public Workbook getWorkbook(InputStream inStr,String fileName) throws  Exception{

        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        System.out.println(wb);
        return wb;
    }
    /**
     * @Method: getValue
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: java.lang.String
     * @Exception:
     * @Date: 2021/1/27 16:59
     * @Param: * @param cell
     * @Return: java.lang.String
     */
    public String getValue(Cell cell){
        String value = "";
        if (null == cell){
            return value;
        }else{
            switch (cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC: //数值型
                    if (HSSFDateUtil.isCellDateFormatted(cell)){
                        //如果是日期类型，获取日期值并进行格式化
                        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        value = format.format(date);
                    }else{
                        BigDecimal bigDecimal = new BigDecimal(cell.getNumericCellValue());
                        value = bigDecimal.toString();
                        //解决有小数点问题
                        if(null != value && "".equals(value.trim())){
                            String [] item = value.split("['.']");
                            if (1 < item.length && "0".equals(item[1])){
                                value = item[0];
                            }
                        }
                    }
                    break;
                case Cell.CELL_TYPE_STRING: //字符串类型
                    value = cell.getStringCellValue().toString();break;
                case Cell.CELL_TYPE_FORMULA:  //公式类型
                    //读取计算值
                    value = String.valueOf(cell.getNumericCellValue());
                    //判断获得的值是否为非法的值，如果是则装为字符串
                    if (value.equals("NAN")){
                        value = cell.getStringCellValue().toString();
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN://布尔类型
                    value = " " + cell.getBooleanCellValue(); break;
                case Cell.CELL_TYPE_BLANK:
                    value = String.valueOf(cell.getNumericCellValue());break;
                default:
                    value = cell.getStringCellValue().toString();break;
            }
            if ("null".endsWith(value.trim())){
                value = "";
            }
        }
        return  value;
    }
}
