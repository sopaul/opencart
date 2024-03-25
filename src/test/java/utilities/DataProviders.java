package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

//import testCases.ExcelUtility;

public class DataProviders 
{

	//Data Provider 1: The purpose of this method to read all the data from the Excel sheet and finally put the entire 
	//data in 2 dimensional array and later this data provider is passed to the testcase.
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx"; //accessing excel file from testData folder
		//OR
		//String path=System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx";
		
		ExcelUtility xlutil=new ExcelUtility(path); // Creating a object for ExcelUtility class
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]=new String [totalrows][totalcols];// created a new two dimensional array which 
															//can to store all the data from the excel sheet
		
		for (int i=1;i<=totalrows;i++) //1 //read the data from excel storing in two dimensional array
		{
			for (int j=0;j<totalcols;j++) //0 i is rows and j is col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); //1,0
			}
		}
		return logindata; // returning two dimensional array
	}
	
	//Data Provider 2:
	
	//Data Provider 3:
}
