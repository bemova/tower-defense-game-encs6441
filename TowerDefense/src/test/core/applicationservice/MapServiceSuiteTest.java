package test.core.applicationservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.core.applicationservice.mapservices.ConnectivityTest;
import test.core.applicationservice.mapservices.MapUtilityTest;
import test.core.applicationservice.mapservices.PathFinderTest;
import test.core.applicationservice.mapservices.StartEndCheckerTest;

@RunWith(Suite.class)
@SuiteClasses({ ConnectivityTest.class, MapUtilityTest.class,
		StartEndCheckerTest.class, PathFinderTest.class })
public class MapServiceSuiteTest {

}
