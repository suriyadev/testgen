package testgen;
import org.junit.Test;
import org.junit.Before;
import test.model.Module1;
public  class Module1Test {
private Module1 objModule1 = new Module1();
@Before
public  void setup() {
System.out.println(this.getClass().getSimpleName()+" => setup executing");
 objModule1 = new Module1();
}
@Test
public  void testmoduleFunction2() {
java.lang.String param0 = null;
 objModule1.moduleFunction2(param0);
System.out.println(this.getClass().getSimpleName()+" => moduleFunction2");
}
@Test
public  void testmoduleFunction1() {
 objModule1.moduleFunction1();
System.out.println(this.getClass().getSimpleName()+" => moduleFunction1");
}
}
