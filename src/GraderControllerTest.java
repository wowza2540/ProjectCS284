import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class GraderControllerTest {
	GraderController g;

	@Before
	public void setUp() throws FileNotFoundException {
		int[] max = {20};
		int[] percent = {100};
		String[][] data = new String[2][4]; 
		data[0][0] = "5909610759";
		data[0][1] = "Supaporn";
		data[0][2] = "Thongsak";
		data[0][3] = "20";
		data[1][0] = "5909610059";
		data[1][1] = "Supitcha";
		data[1][2] = "Buabang";
		data[1][3] = "10";
		
		String course = "CS284";
		g = new GraderController(max,percent, data, course);
	}

	@After
	public void tearDown() throws Exception {
		g = null;
	}

	@Test
	public void testCalculateGrade() {
		
		assertEquals("C", g.calculateGrade(70.15));
		
	}

	@Test
	public void testSaveScoreFile() throws IOException {
		
		//correct file name
		g.saveScoreFile("CS284Grade.txt");
		assertEquals(g.getFlileName(),"CS284Grade.xls");
		
		//read File to check data in file is not Empty
		BufferedReader r = new BufferedReader(new FileReader(new File("CS284Grade.txt")));
		ArrayList<String> dataList = new ArrayList<String>();
		String a = "";
		while((a=r.readLine()) != null){
			dataList.add(a);
		}
		assertNotNull(dataList);
		
		
		
	}

}
