package ExcelSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class SheetDetails {

	public Workbook wb;
	FileOutputStream fo;

	public SheetDetails(String ExcelPath) throws EncryptedDocumentException, IOException {
		FileInputStream fls=new FileInputStream(ExcelPath);
		wb = WorkbookFactory.create(fls);
	}
	public int rowCount(String sheetName) {
		return wb.getSheet(sheetName).getLastRowNum();
	}
	public int colCount(String sheetName) {
		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}
	public String getCellData(String sheetname,int row,int column) {
		String data="";
		if (wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC) {
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
		}else {
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//write results into excel
	public void setCelldata(String sheetname,int row,int column,
			String status,String outputpath)throws Throwable
	{
		wb.getSheet(sheetname).getRow(row).createCell(column).setCellValue(status);
		fo=new FileOutputStream(new File(outputpath));
		wb.write(fo);
	}
	public void greencolour(String sheetname,int row,int column,String outputpath){
		Sheet ws = wb.getSheet(sheetname);
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		//style.setFillPattern(CellFormatType.GENERAL);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
		try {
			fo=new FileOutputStream(new File(outputpath));
			wb.write(fo);
		}catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}
	public void redcolour(String sheetname,int row,int column,String outputpath){
		Sheet ws = wb.getSheet(sheetname);
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		//style.setFillPattern(CellType.sol);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
		try {
			fo=new FileOutputStream(new File(outputpath));
			wb.write(fo);
		}catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}
	public void closefiles()throws Throwable
	{
		wb.close();
	}

}
