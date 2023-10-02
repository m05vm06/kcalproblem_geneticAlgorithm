package GeneticAlgorithm;

class foodData {
	public String name;
	public int price;
	public double calories;
	public double carbohydrate;
	public double protein;
	public double fat;
	public String type;
	
	public foodData(String name, int price, double calories, double carbohydrate, double protein, double fat, String type) {
		this.name = name;
		this.price = price;
		this.calories = calories;
		this.carbohydrate = carbohydrate;
		this.protein = protein;
		this.fat = fat;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public double getCalories() {
		return calories;
	}
	
	public double getCarbohydrate() {
		return carbohydrate;
	}
	
	public double getProtein() {
		return protein;
	}
	
	public double getFat() {
		return fat;
	}
	
	public String getType() {
		return type;
	}
}