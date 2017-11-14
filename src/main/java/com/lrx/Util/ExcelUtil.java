package com.lrx.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class ExcelUtil {
	private String srcXlsPath = "";// // excel文件
	private String desXlsPath = "";
	private String sheetName = "";
	POIFSFileSystem fs = null;
	HSSFWorkbook wb = null;
	HSSFSheet sheet = null;

	/**
	 * 设置excel模板路径
	 * @param srcXlsPath
	 */
	public void setSrcPath(String srcXlsPath) {
		this.srcXlsPath = srcXlsPath;
	}

	/**
	 * 设置要生成excel文件路径
	 * @param desXlsPath
	 */
	public void setDesPath(String desXlsPath) {
		this.desXlsPath = desXlsPath;
	}

	/**
	 * 设置模板中哪个Sheet列
	 * @param sheetName
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * 获取所读取excel模板的对象
	 */
	public HSSFSheet getSheet() {
		try {
			// 获取Excel模板根目录
			String root = ExcelUtil.class.getResource("/").getFile();
			try {
				root = new File(root).getParentFile().getParentFile()
						.getCanonicalPath();
				root += File.separator;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			root = root + "Resources" + File.separator + "Excel"
					+ File.separator + srcXlsPath + ".xls";
			File fi = new File(root);
			if (!fi.exists()) {
				return null;
			}
			fs = new POIFSFileSystem(new FileInputStream(fi));
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * 设置字符串类型的数据
	 * 
	 * @param rowIndex
	 *            --行值
	 * @param cellnum
	 *            --列值
	 * @param value
	 *            --字符串类型的数据
	 */
	public void setCellStrValue(int rowIndex, int cellnum, String value) {
		HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
		cell.setCellValue(value);
	}

	/**
	 * 设置日期/时间类型的数据
	 * 
	 * @param rowIndex
	 *            --行值
	 * @param cellnum
	 *            --列值
	 * @param value
	 *            --日期/时间类型的数据
	 */
	public void setCellDateValue(int rowIndex, int cellnum, Date value) {
		HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
		cell.setCellValue(value);
	}

	/**
	 * 设置浮点类型的数据
	 * 
	 * @param rowIndex
	 *            --行值
	 * @param cellnum
	 *            --列值
	 * @param value
	 *            --浮点类型的数据 .
	 */
	public void setCellDoubleValue(int rowIndex, int cellnum, double value) {
		HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
		cell.setCellValue(value);
	}

	/**
	 * 设置Bool类型的数据 * @param rowIndex--行值 * @param cellnum--列值 * @param
	 * value--Bool类型的数据
	 */
	public void setCellBoolValue(int rowIndex, int cellnum, boolean value) {
		HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
		cell.setCellValue(value);
	}

	/**
	 * 设置日历类型的数据 124. * @param rowIndex--行值 * @param cellnum--列值
	 * 
	 * @param value
	 *            --日历类型的数据
	 */
	public void setCellCalendarValue(int rowIndex, int cellnum, Calendar value) {
		HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
		cell.setCellValue(value);
	}

	/**
	 * 设置富文本字符串类型的数据。可以为同一个单元格内的字符串的不同部分设置不同的字体、颜色、下划线  
	 * * @param
	 * rowIndex--行值
	 * @param cellnum--列值 * @param value--富文本字符串类型的数据 
	 */
	public void setCellRichTextStrValue(int rowIndex, int cellnum,
			RichTextString value) {
		HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
		cell.setCellValue(value);
	}

	/**
	 * 完成导出 .
	 */
	public void exportToNewFile() {
		FileOutputStream out;
		try {
			out = new FileOutputStream(desXlsPath);
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /** 
     * 合并单元格后给合并后的单元格加边框 
     *  
     * @param region 
     * @param cs 
     */  
    public void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs)  
    {  
  
        int toprowNum = region.getFirstRow();  
        for (int i = toprowNum; i <= region.getLastRow(); i++)  
        {  
            HSSFRow row = sheet.getRow(i);  
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++)  
            {  
                HSSFCell cell = row.getCell(j);// XSSFCellUtil.getCell(row,  
                                                // (short) j);  
                cell.setCellStyle(cs);  
            }  
        }  
    }  
  
    /** 
     * 设置表头的单元格样式 
     *  
     * @return 
     */  
    public HSSFCellStyle getHeadStyle()  
    {  
        // 创建单元格样式  
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        // 设置单元格的背景颜色为淡蓝色  
        cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);  
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  
        // 设置单元格居中对齐  
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);  
        // 设置单元格垂直居中对齐  
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  
        // 创建单元格内容显示不下时自动换行  
        cellStyle.setWrapText(true);  
        // 设置单元格字体样式  
        HSSFFont font = wb.createFont();  
        // 设置字体加粗  
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  
        font.setFontName("宋体");  
        font.setFontHeight((short) 200);  
        cellStyle.setFont(font);  
        // 设置单元格边框为细线条  
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  
        return cellStyle;  
    }  
  
    /** 
     * 设置表体的单元格样式 
     *  
     * @return 
     */  
    public HSSFCellStyle getBodyStyle()  
    {  
        // 创建单元格样式  
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        // 设置单元格居中对齐  
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);  
        // 设置单元格垂直居中对齐  
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  
        // 创建单元格内容显示不下时自动换行  
        cellStyle.setWrapText(true);  
        // 设置单元格字体样式  
        HSSFFont font = wb.createFont();  
        // 设置字体加粗  
        //font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD); 
        font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL); 
        font.setFontName("宋体");  
        font.setFontHeight((short) 200);  
        cellStyle.setFont(font);  
        // 设置单元格边框为细线条  
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  
        return cellStyle;  
    }  
    
    /*返回前台*/
	public void exportToNewFile(ServletOutputStream out) {
		try {
			wb.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//模板找不到的提示信息
  	public static String getNotFileHtml(String fileName,String message){
  		StringBuffer sb = new StringBuffer();
  		sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div id='errorInfo' ");
        sb.append(" fileName='"+fileName+"'");
        sb.append(" message='"+message+"'");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
  	}
}

