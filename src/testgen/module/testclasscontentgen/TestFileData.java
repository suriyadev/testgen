package testgen.module.testclasscontentgen;

public class TestFileData {

	private String testClassName;
	private String testClassContent;

	public TestFileData() {
		super();

	}

	@Override
	public String toString() {
		return "TestFileData [testClassName=" + testClassName + ", testClassContent=" + testClassContent + "]";
	}

	public TestFileData(String testClassName, String testClassContent) {
		super();
		this.testClassName = testClassName;
		this.testClassContent = testClassContent;
	}

	public String getTestClassName() {
		return testClassName;
	}

	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}

	public String getTestClassContent() {
		return testClassContent;
	}

	public void setTestClassContent(String testClassContent) {
		this.testClassContent = testClassContent;
	}

}
