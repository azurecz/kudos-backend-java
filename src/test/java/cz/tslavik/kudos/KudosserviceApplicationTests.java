package cz.tslavik.kudos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KudosserviceApplicationTests {

	private static String TEST_TEXT = "test";

	@Test
	public void contextLoads() {
		assertEquals(TEST_TEXT, "test");
	}

}

