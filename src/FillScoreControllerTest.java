import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FillScoreControllerTest {
	private FillScoreController fill;

	@Test
	public void testFillScoreController() throws Exception{
		
		fill = new FillScoreController("CS284Score.txt","CS284" );
		String d[][] = fill.getData();

		assertNotNull(d);
	}
	

	@Test
	public void testReadFileGradeGrilienia() {

		String str = "CS284Score.txt";
		String strtest = "CS284Sicore.txt";

		fill = new FillScoreController(str,"CS284" );
		FillScoreController fill2 = new FillScoreController(strtest,"CS284" );

		String d[][] = fill.getData();
		assertNotEquals(fill, fill2);
		assertNotNull(d);
	}

	@Test
	public void testSaveScoreFile() {
		fill = new FillScoreController("CS284Score.txt","CS284" );
		//check file submit complete
		boolean status = fill.saveScoreFile("CS284Score.txt");
		if(status){
			assertTrue(status);
		}else{
			assertFalse(status);
		}
		
	}
	
	@Test
	public void testSubmitScoreFile() {
		fill = new FillScoreController("CS284Score.txt","CS284" );
		//check file submit complete
		boolean status = fill.submitScoreFile("CS284Score.txt");
		if(status){
			assertTrue(status);
		}else{
			assertFalse(status);
		}
	}

	@Test
	public void testAddScore() {
			fill = new FillScoreController("CS284Score.txt","CS284" );
		
			String input2 = "4";
			Boolean added1 = fill.addScore(input2,0,4);
			assertTrue("ERROR",added1);
			
			
			String input1 = "hi";
			Boolean added2 = fill.addScore(input1,0,4);
			assertFalse("ERROR",added2);
			
			String input3 = "-2";
			Boolean added3 = fill.addScore(input3,0,4);
			assertFalse("ERROR",added3);
			
			String input4 = "* / +";
			Boolean added4 = fill.addScore(input4,0,4);
			assertFalse("ERROR",added4);
			
			String input5 = null;
			Boolean added5 = fill.addScore(input5,0,4);
			assertFalse("ERROR",added5);
			
			String input6 = "8";
			Boolean added6 = fill.addScore(input4,-1,4);
			assertFalse("ERROR",added6);
		
	}

}
