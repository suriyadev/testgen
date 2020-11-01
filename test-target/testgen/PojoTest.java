package testgen;
import org.junit.Test;
import org.junit.Before;
import test.model.Pojo;
public  class PojoTest {
private Pojo objPojo = new Pojo();
@Before
public  void setup() {
System.out.println(this.getClass().getSimpleName()+" => setup executing");
 objPojo = new Pojo();
}
}
