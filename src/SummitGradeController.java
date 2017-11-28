
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class SummitGradeController {
	private boolean isSummit;
	
	public SummitGradeController(File file){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			try {
				String line = reader.readLine();
				if(line.equalsIgnoreCase("true")){
					isSummit = true;
				}
				if(line.equalsIgnoreCase("false")){
					isSummit = false;
				}
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setSummit(boolean input){
		if(!input){
			JOptionPane.showMessageDialog(null, "Can't change from already summit to not summit grade.");
		}else{
			isSummit = input;		
			JOptionPane.showMessageDialog(null, "Success. You have summit grade, remind that you can't change scroe anymore from now on.");
		}
	}
	public boolean checkIsSummit(){
		return isSummit;
	}
}