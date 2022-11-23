package dataDriven;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class ExcelSheetLibrary {

    static Sheet worksheet;
    static Workbook workbook;
    static Hashtable dict = new Hashtable();

    public ExcelSheetLibrary(String ExcelSheetPath) throws BiffException, IOException {
        workbook = Workbook.getWorkbook(new File(ExcelSheetPath));
        worksheet = workbook.getSheet("Sheet1");
        ColumnDictionary();
    }
    public static int RowCount(){
        return worksheet.getRows();
    }
    public static String ReadCell(int column, int row){
        return worksheet.getCell(column, row).getContents();
    }
    public static void ColumnDictionary(){
        for(int col=0; col < worksheet.getColumns();col++ ){
            dict.put(ReadCell(col, 0), col);
        }
    }
    public static int GetCell(String colName){
        try{
            return ((Integer) dict.get(colName)).intValue();
        }
        catch (NullPointerException e){
            return (0);
        }
    }
}
