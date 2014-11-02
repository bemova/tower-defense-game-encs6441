package test.core.applicationservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.core.applicationservice.mapServices.ConnectivityTest;
import test.core.applicationservice.mapServices.MapUtilityTest;
import test.core.applicationservice.mapServices.StartEndCheckerTest;


@RunWith(Suite.class)
@SuiteClasses({ ConnectivityTest.class, MapUtilityTest.class, StartEndCheckerTest.class })
public class MapServiceSuiteTest {

}
