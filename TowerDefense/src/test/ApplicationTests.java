package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.core.applicationService.MapServiceSuiteTest;
import test.core.domain.warriors.TowersSuiteTest;

@RunWith(Suite.class)
@SuiteClasses({TowersSuiteTest.class, MapServiceSuiteTest.class})
public class ApplicationTests {
	
}
