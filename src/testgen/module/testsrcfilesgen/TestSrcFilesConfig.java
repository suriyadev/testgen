package testgen.module.testsrcfilesgen;

public class TestSrcFilesConfig {

	private String targetLocation;
	private String rootPackage;
	
	public String getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(String rootPackage) {
		this.rootPackage = rootPackage;
	}

	public final static String DEFAULT = "default";

	public TestSrcFilesConfig() {
		super();

	}

	public TestSrcFilesConfig(String config) {
		if (config.equals(DEFAULT)) {
			this.rootPackage = "testgen";
			this.targetLocation = "test-src";
		}
	}

	public String getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(String targetLocation) {
		this.targetLocation = targetLocation;
	}

	public static String getDefault() {
		return DEFAULT;
	}

}
