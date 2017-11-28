
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FillScoreController {
	private String[][] data;
	private String fileName;
	private int row,column;
	private int gradeGrilienia;
	private String[] g;
	private int[] maxScore;
	private String status;
	private String course;
	private int[] grader;
	private boolean check;
	
	public FillScoreController(String fileName, String courseName) {
		BufferedReader bf;
		String line = "";
		String[] str;
		this.fileName = fileName;
		course = courseName;
		
		try {
			status = "false";
			bf = new BufferedReader(new FileReader(new File(fileName)));
			status = bf.readLine();
			while((line = bf.readLine()) != null){
				str = line.split("/");
				column = str.length;
				row++;
			}
			readFileGradeGrilienia(courseName + "GradeGrilienia.txt");
			readFile(this.fileName);
			setCheck(true);
			bf.close();
		} catch (FileNotFoundException e) {
			try {
				this.fileName = courseName + "StudentList.txt";
				bf = new BufferedReader(new FileReader(new File(this.fileName)));
				while((line = bf.readLine()) != null){
					str = line.split("/");
					column = str.length;
					row++;
				}
				bf.close();
				readFileGradeGrilienia(courseName + "GradeGrilienia.txt");
				column += gradeGrilienia;
				readFile(this.fileName);
				setCheck(false);
				saveScoreFile(courseName + "Score.txt");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getRow(){
		return row;
	}
	public String[][] getData(){
		return data;
	}
	public String getStatus(){
		return status;
	}
	public int[] getMaxScore(){
		return maxScore;
	}
	public void setStatus(String s){
		status = s;
	}
	public int[] getGrader(){
		return grader;
	}
	public String getFileName(){
		return fileName;
	}
	public String[] getGrilienia(){
		return g;
	}
	public void setCheck(boolean c){
		check = c;
	}
	public boolean getCheck(){
		return check;
	}
	
	public void readFileGradeGrilienia(String fileName){
		BufferedReader bf;
		String line = "";
		int i=0;
		try {
			bf = new BufferedReader(new FileReader(new File(fileName)));
			line = bf.readLine();
			gradeGrilienia = Integer.parseInt(line);
			g = new String[gradeGrilienia];
			String[] str = null;
			
			int sum = 0;
			maxScore = new int[gradeGrilienia];
			grader = new int[gradeGrilienia];
			while((line = bf.readLine()) != null){
				g[i] = line;
				str = g[i].split(",");
				maxScore[i] = Integer.parseInt(str[1]);
				grader[i] = Integer.parseInt(str[2]);	
				sum += grader[i];
				i++;
			}
			if(sum > 100) throw new NumberFormatException();
			bf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Invalid file");
			System.exit(0);
		}
	}
	public void readFile(String fileName){
		data = new String[row][column];
		BufferedReader bf;
		String line = "";
		String[] str;
		int row = 0;

		try {
			bf = new BufferedReader(new FileReader(new File(fileName)));
			if(fileName.equals(course+ "Score.txt")) status = bf.readLine();
			while((line = bf.readLine()) != null){
				str = line.split("/");
				for(int i=0; i<str.length; i++){
					data[row][i] = str[i];
				} row++;
			}
			bf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean saveScoreFile(String filename){
		PrintWriter out;
		try {
			out = new PrintWriter(filename);
			out.println(status);
			for(int i=0; i<row; i++){
				for(int j=0; j<column; j++){
					if(data[i][j] == null){
						if(j == column-1) out.print(" ");
						else out.print(" /");
					}
					else {
						if(j == (column)-1) out.print(data[i][j]);
						else out.print(data[i][j] + "/");
					}
				}
				out.println();
			}
			out.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		
		
		//Save to excel file to sent to student
		if(check){
			String sheetName = "Sheet1";

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(sheetName) ;

			for (int r=0; r<data.length; r++){
				HSSFRow row = sheet.createRow(r);
		
				for (int c=0; c<data[0].length; c++){
					HSSFCell cell = row.createCell(c);
					
					cell.setCellValue(data[r][c]);
				}
			}
			
			FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream(course + "Score.xls");
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
				 new SendMailSSL(course + "Score.xls");
			} catch (IOException e) {
				return false;
			}
		}
		return true;
		
	}
	public boolean submitScoreFile(String filename){
		saveScoreFile(course + "Score.txt");
		PrintWriter out;
		try {
			out = new PrintWriter(filename);
			out.println(status);
			for(int i=0; i<row; i++){
				for(int j=0; j<column; j++){
					if(data[i][j].equals(" ")){
						if(j == column-1) out.print("0");
						else out.print("0/");
					}
					else {
						if(j == (column)-1) out.print(data[i][j]);
						else out.print(data[i][j] + "/");
					}
				}
				out.println();
			}
			out.close();
	
			readFileGradeGrilienia(course + "GradeGrilienia.txt");
			readFile(fileName);
			new GraderController(getMaxScore(), getGrader(), getData(), course);
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}
	public boolean addScore(String score, int x, int y){
		try{
			if(((Double.parseDouble(score) <= maxScore[y-3]) || (Integer.parseInt(score) <= maxScore[y-3])) && ((Double.parseDouble(score) >= 0) || (Integer.parseInt(score) >= 0))){
				data[x][y] = score;
				return true;
			}
			else
				throw new NumberFormatException();
		}catch(NumberFormatException e){
			return false;
		}catch(NullPointerException e){
			return false;
		}
	}
}