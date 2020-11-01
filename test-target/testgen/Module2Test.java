package testgen;
import org.junit.Test;
import org.junit.Before;
import test.model.Module2;
public  class Module2Test {
private Module2 objModule2 = new Module2();
@Before
public  void setup() {
System.out.println(this.getClass().getSimpleName()+" => setup executing");
 objModule2 = new Module2();
}
@Test
public  void testmoduleFuncdfgdfgdtion2() {
test.model.Pojo2 param0 = null;
 objModule2.moduleFuncdfgdfgdtion2(param0);
System.out.println(this.getClass().getSimpleName()+" => moduleFuncdfgdfgdtion2");
}
@Test
public  void testmoduleFunctifddfg() {
 objModule2.moduleFunctifddfg();
System.out.println(this.getClass().getSimpleName()+" => moduleFunctifddfg");
}
}
