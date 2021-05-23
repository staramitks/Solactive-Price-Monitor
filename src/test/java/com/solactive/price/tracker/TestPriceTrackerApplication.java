package com.solactive.price.tracker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PriceTrackerApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = { "test" })
public class TestPriceTrackerApplication {

	@LocalServerPort
	int randomServerPort;

	@Test
	public void sampleTest() {
		// To load context and check that all dependencies are fine.
	}
}