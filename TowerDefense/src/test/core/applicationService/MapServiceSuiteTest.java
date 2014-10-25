package test.core.applicationService;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.core.applicationService.mapServices.ConnectivityTest;
import test.core.applicationService.mapServices.MapUtilityTest;
import test.core.applicationService.mapServices.StartEndCheckerTest;


@RunWith(Suite.class)
@SuiteClasses({ ConnectivityTest.class, MapUtilityTest.class, StartEndCheckerTest.class })
public class MapServiceSuiteTest {

}
