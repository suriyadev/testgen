package testgen.module.srcdataprovider;

import java.util.ArrayList;
import java.util.List;

public class SrcFilesConfig {

	private List<String> fullyQualifedSrcClassNames;
	private String targetLocation;
	public final static String DEFAULT = "default";

	public SrcFilesConfig() {
		super();

	}

	public SrcFilesConfig(String config) {
		if (config.equals(DEFAULT)) {
			this.targetLocation = "";
			this.fullyQualifedSrcClassNames = new ArrayList<String>();
			this.fullyQualifedSrcClassNames.add("java.util.ArrayList");
		}

	}

	public SrcFilesConfig(List<String> fullyQualifedSrcClassNames, String targetLocation) {
		super();
		this.fullyQualifedSrcClassNames = fullyQualifedSrcClassNames;
		this.targetLocation = targetLocation;
	}

	public List<String> getFullyQualifedSrcClassNames() {
		return fullyQualifedSrcClassNames;
	}

	public void setFullyQualifedSrcClassNames(List<String> fullyQualifedSrcClassNames) {
		this.fullyQualifedSrcClassNames = fullyQualifedSrcClassNames;
	}

	public String getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(String targetLocation) {
		this.targetLocation = targetLocation;
	}

}
