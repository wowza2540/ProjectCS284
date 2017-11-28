
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
	private ArrayList<User> list;
	
	public LoginController() throws IOException {
		list = new ArrayList<>();
		
		String line = "";
		String[] str;
		BufferedReader bf = new BufferedReader(new FileReader(new File("Password.txt")));
		while((line = bf.readLine()) != null){
			str = line.split(",");
			loadUser(str[0], str[1], str[2], str[3]);
		}	
	}
	public void loadUser(String user, String pass, String name, String lastname){
		list.add(new User(user, pass, name, lastname));		
	}
	public boolean checkID(String user, String pass){
		for(int i=0; i<list.size(); i++){
			if(user.equals(list.get(i).getUser())){
				if(pass.equals(list.get(i).getPass())){
					return true;
				}
			}
		}
		return false;
	}
	public User getUser(String user){
		for(int i=0; i<list.size(); i++){
			if(user.equals(list.get(i).getUser())){
				return list.get(i);
			}
		}
		return null;
	}
}