
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class CalculateScoreControllerTest {
	CalculateScoreController c;

	@Before
	public void setUp() throws Exception {
		int[] max = {20,30,50};
		int[] percent = {25,25,50};
		String[][] data = new String[2][6]; 
		data[0][0] = "5909610759";
		data[0][1] = "Supaporn";
		data[0][2] = "Thongsak";
		data[0][3] = "20.52";
		data[0][4] = "15";
		data[0][5] = "40";
		data[1][0] = "5909650059";
		data[1][1] = "Supitcha";
		data[1][2] = "Buabang";
		data[1][3] = "0";
		data[1][4] = "15.14";
		data[1][5] = "400";
		c = new CalculateScoreController(max,percent, data);
	}

	@After
	public void tearDown() throws Exception {
		c = null;
	}

	@Test
	public void testCalculateSumScore() {
		assertTrue(c.calculateSumScore() != null);
		
		
		//check data in array that follow format
		double expected[] = c.calculateSumScore();
		String pattern  = "[0-9]+.[0-9]*";
		for(int i=0; i<expected.length; i++){
			boolean temp = String.valueOf(expected[i]).matches(pattern);
			if(temp){
				assertTrue(temp);//true
			}else{
				assertFalse(!temp);//false
			}
		}
		
	}

	@Test
	public void testCalculateMean() {
		//method can calculate Mean and result != null
		c.calculateMean(c.calculateSumScore());
		assertNotNull(c.getMean());
		
		//check format
		String pattern  = "[0-9]+.[0-9]*";
		boolean temp = String.valueOf(c.getMean()).matches(pattern);
		assertTrue(temp);
		
		// mean >= 0
		assertTrue(c.getMean() >= 0);
	}

	@Test
	public void testCalculateSD() {
		//method can calculate Mean and result != null
		c.calculateSD(c.calculateSumScore());
		assertNotNull(c.getSD());
		
		//check format
		String pattern  = "[0-9]+.[0-9]*";
		boolean temp = String.valueOf(c.getMean()).matches(pattern);
		assertTrue(c.getMean() >= 0);
	}

}
