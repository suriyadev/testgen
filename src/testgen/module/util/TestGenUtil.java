package testgen.module.util;

public class TestGenUtil {

	private TestGenUtil instance;
	
	private TestGenUtil() {
		instance = new TestGenUtil();
	}
	
	public TestGenUtil getInstance() {
		return instance;
	}
 
	
	
	
	
}
