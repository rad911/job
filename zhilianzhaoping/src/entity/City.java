package entity;

import java.util.HashMap;
import java.util.List;
public class City{
	private String Name;
	private double averageLowCityPrice;
	private double averageHighCityPrice;
	private HashMap<String,List<HashMap<String,String>>> data;
	private HashMap<String,Double> averageLowPrice;
	private HashMap<String,Double> averageHighPrice;
	public double getAverageLowCityPrice() {
		return averageLowCityPrice;
	}
	public void setAverageLowCityPrice(double averageLowCityPrice) {
		this.averageLowCityPrice = averageLowCityPrice;
	}
	public double getAverageHighCityPrice() {
		return averageHighCityPrice;
	}
	public void setAverageHighCityPrice(double averageHighCityPrice) {
		this.averageHighCityPrice = averageHighCityPrice;
	}
	public HashMap<String,List<HashMap<String, String>>> getData() {
		return data;
	}
	public void setData(HashMap<String,List<HashMap<String,String>>> data) {
		this.data = data;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public HashMap<String, Double> getAverageLowPrice() {
		return averageLowPrice;
	}
	public void setAverageLowPrice(HashMap<String, Double> averageLowPrice) {
		this.averageLowPrice = averageLowPrice;
	}
	public HashMap<String, Double> getAverageHighPrice() {
		return averageHighPrice;
	}
	public void setAverageHighPrice(HashMap<String, Double> averageHighPrice) {
		this.averageHighPrice = averageHighPrice;
	}
}
