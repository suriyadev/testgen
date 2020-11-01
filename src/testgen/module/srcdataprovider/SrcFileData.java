package testgen.module.srcdataprovider;

import java.lang.reflect.Method;
import java.util.List;

public class SrcFileData {

	private Class classRef;
	private String packageName;
	private String srcFileName;
	private String srcFileClassName;
	private int noOfMethods;
	private List<Method> methodList;
	private List<String> methodNamesList;

	
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<String> getMethodNamesList() {
		return methodNamesList;
	}

	public void setMethodNamesList(List<String> methodNamesList) {
		this.methodNamesList = methodNamesList;
	}

	public SrcFileData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SrcFileData [classRef=" + classRef + ", srcFileName=" + srcFileName + ", srcFileClassName="
				+ srcFileClassName + ", noOfMethods=" + noOfMethods + ", methodList=" + methodList + "]";
	}

	public SrcFileData(Class classRef, String srcFileName, String srcFileClassName, int noOfMethods,
			List<Method> methodList) {
		super();
		this.classRef = classRef;
		this.srcFileName = srcFileName;
		this.srcFileClassName = srcFileClassName;
		this.noOfMethods = noOfMethods;
		this.methodList = methodList;
	}

	public Class getClassRef() {
		return classRef;
	}

	public void setClassRef(Class classRef) {
		this.classRef = classRef;
	}

	public String getSrcFileName() {
		return srcFileName;
	}

	public void setSrcFileName(String srcFileName) {
		this.srcFileName = srcFileName;
	}

	public String getSrcFileClassName() {
		return srcFileClassName;
	}

	public void setSrcFileClassName(String srcFileClassName) {
		this.srcFileClassName = srcFileClassName;
	}

	public int getNoOfMethods() {
		return noOfMethods;
	}

	public void setNoOfMethods(int noOfMethods) {
		this.noOfMethods = noOfMethods;
	}

	public List<Method> getMethodList() {
		return methodList;
	}

	public void setMethodList(List<Method> methodList) {
		this.methodList = methodList;
	}

}
