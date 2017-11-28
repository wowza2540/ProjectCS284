import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class SelectCourseController {
	private String name;
	private String lastName;
	private File file;
	private BufferedReader reader;
	private ArrayList<String> courseId;
	
	public SelectCourseController(String name){
		this.name = name;
		file = new File("course.txt");
		courseId = new ArrayList<>();
	}
	public void find(){
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = "";
			try {
				while((line = reader.readLine()) != null){
					if(line.contains(name)){
						StringTokenizer data = new StringTokenizer(line, ",");
						name = data.nextToken();
						lastName = data.nextToken();
						while(data.hasMoreTokens()){
							courseId.add(data.nextToken());
						}
						break;
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File not found.");
		}
		
	}
	public String getName(){
		return name;
	}
	public String getLastName(){
		return lastName;
	}
	public ArrayList<String> getAllCourseId(){
		return courseId;
	}
}