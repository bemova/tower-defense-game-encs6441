package JUnitTestSuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import JUnitTests.MapLoading;
import JUnitTests.MapSaving;
import JUnitTests.Path;

@RunWith(Suite.class)
@SuiteClasses({ MapLoading.class, MapSaving.class, Path.class })
public class TestSuitMapValidation {

}
