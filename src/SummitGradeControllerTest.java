import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SummitGradeControllerTest {
	private SummitGradeController summit;


	@Test
	public void testSetSummit() {
		String name1 = "CS284Score.txt";
		summit = new SummitGradeController(new File(name1));
		summit.setSummit(false);
		assertTrue(summit.checkIsSummit() != true);
		
		summit.setSummit(true);
		assertTrue(summit.checkIsSummit() == true);
		
	}

	@Test
	public void testCheckIsSummit() {
		String name1 = "CS284Score.txt";
		summit = new SummitGradeController(new File(name1));
		assertFalse(summit.checkIsSummit() == true);
		
		String name2 = "CS280Score.txt";
		SummitGradeController summit = new SummitGradeController(new File(name2));
		assertFalse(summit.checkIsSummit() == true);
		
	}

}
