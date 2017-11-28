

public class User {
	private String user;
	private String pass;
	private String name;
	private String lastname;
	
	public User(String user, String pass, String name, String lastname) {
		this.user = user;
		this.pass = pass;
		this.name = name;
		this.lastname = lastname;
	}
	public String getUser() {
		return user;
	}
	public String getPass() {
		return pass;
	}
	public String getName() {
		return name;
	}
	public String getLastname() {
		return lastname;
	}
}