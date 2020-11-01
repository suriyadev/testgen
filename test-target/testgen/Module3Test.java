package testgen;
import org.junit.Test;
import org.junit.Before;
import test.model.Module3;
public  class Module3Test {
private Module3 objModule3 = new Module3();
@Before
public  void setup() {
System.out.println(this.getClass().getSimpleName()+" => setup executing");
 objModule3 = new Module3();
}
@Test
public  void testmoduleFunctiodddn1() {
 objModule3.moduleFunctiodddn1();
System.out.println(this.getClass().getSimpleName()+" => moduleFunctiodddn1");
}
@Test
public  void testmoduleFunctiondd2() {
java.lang.String param0 = null;
 objModule3.moduleFunctiondd2(param0);
System.out.println(this.getClass().getSimpleName()+" => moduleFunctiondd2");
}
}
