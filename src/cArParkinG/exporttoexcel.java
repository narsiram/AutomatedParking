package cArParkinG;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class exporttoexcel
{
String sheetName="Sheet 1";
XSSFWorkbook wb=new XSSFWorkbook();
XSSFSheet sheet =wb.createSheet(sheetName);
//iterating r no. of rows
void write(String filename,int rows,int columns,JTable table)
{
for(int r=0;r<rows;r++)
{
XSSFRow row=sheet.createRow(r);
//iterating c no. of columns
for(int c=0;c<columns;c++)
{
XSSFCell cell =row.createCell(c);
cell.setCellValue(table.getValueAt(r, c).toString());
}
}
try {
FileOutputStream fileout= new FileOutputStream(filename) ;
wb.write(fileout);
JOptionPane.showMessageDialog(null, "Data saved successfully");
fileout.flush();
fileout.close();
}
catch (FileNotFoundException e)
{
e.printStackTrace();
}
catch (IOException e)
{
e.printStackTrace();
}
}

}

