package testgen;
 import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses( {
Module1Test.class,Module2Test.class,Module3Test.class,PojoTest.class,}
)
public  class JunitTestSuite {
}
