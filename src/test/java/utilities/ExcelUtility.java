package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
;

public class ExcelUtility 
{
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	// There is not static method or variable , so let the program create an object for ExcelUtility class and 
	// through the object the program can access all the below methods
	// The below method is Construct to pass the 'path' value of the excel file  used by all the below methods
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	//Get Row count
	public  int getRowCount(String sheetName) throws IOException
	{
		fi = new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;	
	}
	

	
	//Get Cell count
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	//Read the data from the cell
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter =new DataFormatter();
		String data;
		try
		{
		data=formatter.formatCellValue(cell);
		}
		catch (Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	//Write data into the cell
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists()) //If file not exists then create new file.
		{
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
			
		}
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1)	//If Sheet not exists then create new Sheet
			workbook.createSheet(sheetName);
		sheet=workbook.getSheet(sheetName);
		
		if(sheet.getRow(rownum)==null)				//If Sheet row not exists then create new row
			sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}
	
	//The below code is used to highlight cell color 'GREEN' when the testcase passes
			public void fillGreenColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
			{
				fi=new FileInputStream(xlfile);
				workbook=new XSSFWorkbook(fi);
				sheet=workbook.getSheet(xlsheet);
				row=sheet.getRow(rownum);
				cell=row.getCell(colnum);
				
				style =workbook.createCellStyle();
				
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				
				cell.setCellStyle(style);
				fo=new FileOutputStream(xlfile);
				workbook.write(fo);
				workbook.close();
				fi.close();
				fo.close();
			}
			
			//The below code is used to highlight cell color 'RED' when the testcase fails
			public void fillRedColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
			{
				fi=new FileInputStream(xlfile);
				workbook=new XSSFWorkbook(fi);
				sheet=workbook.getSheet(xlsheet);
				row=sheet.getRow(rownum);
				cell=row.getCell(colnum);
						
				style =workbook.createCellStyle();
						
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						
						
				cell.setCellStyle(style);
				fo=new FileOutputStream(xlfile);
				workbook.write(fo);
				workbook.close();
				fi.close();
				fo.close();
			}
	
}
