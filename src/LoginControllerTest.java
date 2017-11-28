import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class LoginControllerTest {
	LoginController g;
	@Before
	public void setUp() throws Exception {
		g = new LoginController();
	}

	@After
	public void tearDown() throws Exception {
		
	}
		
	@Test
	public void testAddUser() throws IOException {
		String line = "";
		String[] str;
		BufferedReader bf = new BufferedReader(new FileReader(new File("Password.txt")));
		while((line = bf.readLine()) != null){
			str = line.split(",");
			g.loadUser(str[0], str[1], str[2], str[3]);
		}
		assertNotNull(bf);
		
		String uu = "aumm";
		g.getUser(uu);
		assertTrue("Error!!!",g.getUser(uu)==null);
		
		String uu1 = "doon";
		g.getUser(uu1);
		assertFalse("Error!!!",g.getUser(uu1)==null);
		
		}

	@Test
	public void testCheckID() throws IOException {
		String u = "aum";
		String p = "a1";
		Boolean check = g.checkID(u, p);
		assertTrue("Error!!!",check);
		
		String u1 = "+";
		String p1 = "***";
		Boolean check1 = g.checkID(u1, p1);
		assertFalse("Error!!!",check1);
		
		String u2 = "449";
		String p2 = "-89";
		Boolean check2 = g.checkID(u2, p2);
		assertFalse("Error!!!",check2);
		
		String u3 = "AA";
		String p3 = "BB";
		Boolean check3 = g.checkID(u1, p1);
		assertFalse("Error!!!",check1);
		
		String u5 = "mint";
		String p5 = "mm";
		Boolean check5 = g.checkID(u5, p5);
		assertFalse("Error!!!",check5);
		
		String u6 = "doonlab";
		String p6 = "d3";
		Boolean check6 = g.checkID(u6, p6);
		assertFalse("Error!!!",check6);
		
		String u4 = "§§§§";
		String p4 = "¡¡¡";
		Boolean check4 = g.checkID(u1, p1);
		assertFalse("Error!!!",check1);
	}

}
