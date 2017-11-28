
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class GraderController {
	private ArrayList<Grade> grader;
	private String filename;
	private String[][] data;
	private CalculateScoreController csc;
	private double mean;
	private double sd;
	
	public GraderController(int[] max,int[] percent, String[][] data, String course) throws FileNotFoundException {
		this.data = data;
		filename = course + "Grade.xls";
		
		csc = new CalculateScoreController(max, percent, data);
		mean = csc.getMean();
		sd = csc.getSD();
		
		saveScore();
		saveScoreFile(filename);
	}
	public String calculateGrade(double score){
		if(score < mean-(1.5*sd)) return "F";
		else if(score >= mean-(1.5*sd) && score < mean-sd) return "D";
		else if(score >= mean-sd && score < mean-(0.5*sd)) return "D+";
		else if(score >= mean-(0.5*sd) && score < mean) return "C";
		else if(score >= mean && score < mean+(0.5*sd)) return "C+";
		else if(score >= mean+(0.5*sd) && score < mean+sd) return "B";
		else if(score >= mean+sd && score < mean+(1.5*sd)) return "B+";
		else return "A";
	}
	public void saveScore(){
		grader = new ArrayList<>();
		for(int i=0; i<data.length; i++){
			grader.add(new Grade(data[i][0], calculateGrade(csc.calculateSumScore()[i])));
		}
	}
	public boolean saveScoreFile(String filename){
		String sheetName = "Sheet1";

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName) ;

		for (int r=0; r<data.length; r++){
			HSSFRow row = sheet.createRow(r);
	
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(grader.get(r).getId());
			
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(grader.get(r).getGrade());
		}
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(filename);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			return true;
		}  catch (IOException e) {
			return false;
		}	
	}
	public String getFlileName(){
		return filename;
	}
}