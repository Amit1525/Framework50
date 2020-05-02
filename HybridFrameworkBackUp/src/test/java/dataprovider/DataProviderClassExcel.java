package dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderClassExcel {
	
	public static ExcelDataProvider excel;

	@DataProvider(name = "excelAppData")
	public static Object[][] GetExcelData() {

		excel = new ExcelDataProvider();
		
		int row = excel.rowCount("UserCreation");
		int column = excel.columnCount("UserCreation");

		Object[][] arr = new Object[row][column];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {

				arr[i][j] = excel.getCellData("UserCreation", i, j);
			}
		}
		
		return arr;
	}

}
