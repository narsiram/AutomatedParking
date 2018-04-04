package cArParkinG;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class ExcelFile
{
static Connection con;
static PreparedStatement pstmt=null;
public static ArrayList readExcelFile(String filename)
{
/**-- Define a Arraylist
* --Holds Arraylist of cells*/

ArrayList cellArraylistHolder=new ArrayList();
try{
/**Creating Input Stream**/
FileInputStream myInput=new FileInputStream(filename);
/** Create a POIFSFileSystem object**/


/** Create a workbook using FileSystem*/

XSSFWorkbook workbook=new XSSFWorkbook(myInput);
/** Get the first sheet from workbook**/

XSSFSheet mySheet =workbook.getSheetAt(0);
/* we now need something to iterate through cells*/

Iterator rowIter =mySheet.rowIterator();
while(rowIter.hasNext())
{
Row myRow=(Row)rowIter.next();
Iterator cellIter =myRow.cellIterator();
ArrayList cellStoreArraylist =new ArrayList();
while(cellIter.hasNext())
{
Cell myCell=(Cell)cellIter.next();
cellStoreArraylist.add(myCell);

}
cellArraylistHolder.add(cellStoreArraylist);
}//while closed

}//try closed
catch(Exception e)
{
e.printStackTrace();
}

return cellArraylistHolder;

}//method closed
public static void Read()
{
String filename="D://Book1.xlsx";
/*read an excel file and store it in Arraylist */

ArrayList dataholder = readExcelFile(filename);
//print data read
//print cell data to console(dataholder)
try
{
con=coNNection.docoNNect();
if(con!=null)
JOptionPane.showMessageDialog(null, "Connected");
pstmt=con.prepareStatement("insert into student values(?,?,?,?)");
}

catch (SQLException e)
{
e.printStackTrace();
}

int count=0;

ArrayList cellStoreArrayList=null;
//For inserting into database

for(int i=1;i<dataholder.size();i++)
{
cellStoreArrayList=(ArrayList)dataholder.get(i);
try {
pstmt.setString(1, (cellStoreArrayList.get(0)).toString());
pstmt.setString(2, (cellStoreArrayList.get(1)).toString());
pstmt.setDouble(3, Double.parseDouble((cellStoreArrayList.get(2)).toString()));
pstmt.setString(4, (cellStoreArrayList.get(3)).toString());
count=pstmt.executeUpdate();
}
catch (SQLException e)
{
e.printStackTrace();
}
System.out.print(((Cell)cellStoreArrayList.get(0)).toString()+" ");
System.out.print(((Cell)cellStoreArrayList.get(1)).toString()+" ");
System.out.print(Double.parseDouble(((Cell)cellStoreArrayList.get(2)).toString())+" ");
System.out.print(((Cell)cellStoreArrayList.get(3)).toString()+" ");
System.out.println();
}
}
public static void main(String[] args)
{
ExcelFile rf=new ExcelFile();
ExcelFile.Read();

}
}
