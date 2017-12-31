package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Offer {
	private String name;
	private List<HashMap<String, String>> data;
	private HashMap<String, Integer> areaCount;
	private HashMap<String, Integer> lowCount;
	private HashMap<String, Integer> highCount;
	private double lowAverage;
	private double highAverage;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<HashMap<String, String>> getData() {
		return data;
	}
	public void setData(List<HashMap<String, String>> data) {
		this.data = data;
	}
	public HashMap<String, Integer> getAreaCount() {
		return areaCount;
	}
	public void setAreaCount(HashMap<String, Integer> areaCount) {
		this.areaCount = areaCount;
	}
	public HashMap<String, Integer> getLowCount() {
		return lowCount;
	}
	public void setLowCount(HashMap<String, Integer> lowCount) {
		this.lowCount = lowCount;
	}
	public HashMap<String, Integer> getHighCount() {
		return highCount;
	}
	public void setHighCount(HashMap<String, Integer> highCount) {
		this.highCount = highCount;
	}
	public double getLowAverage() {
		return lowAverage;
	}
	public void setLowAverage(double lowAverage) {
		this.lowAverage = lowAverage;
	}
	public double getHighAverage() {
		return highAverage;
	}
	public void setHighAverage(double highAverage) {
		this.highAverage = highAverage;
	}
	
}
