package GeneticAlgorithm;

import java.util.Random;
import java.util.Scanner;

public class KcalProblem implements Problem<foodData>{
	public static double limitCalories = 0.0;
	Random rand = null;
	readData RD = new readData();
	foodData[] FD = RD.importFile(); 
	public static int budget = 300;
//	public static void main(String[] args) {
//		  for(int i = 0; i < 55; i++)
//		  System.out.print(FD.foodName[i]+"\t"+ FD.price[i]+"\t"+FD.calories[i]+"\t"+ FD.carbohydrate[i]+"\t"+ FD.protein[i]+"\t"+ FD.fat[i]+"\t"+ FD.type[i]+"\n"); 
//	}
	public KcalProblem(Random rand) {
		this.rand = rand;
		inputUserData();
	}
	public static void inputUserData() {
		// TODO Auto-generated method stub
				int height = 175, weight = 55;
//				Scanner sc = new Scanner(System.in);
//				System.out.print("請輸入身高(公分):");
//				height = sc.nextInt();
//				System.out.print("請輸入體重:");
//				weight = sc.nextInt();
//				System.out.print("請輸入預算:");
//				budget = sc.nextInt();
				double BMI = weight/Math.pow(height/100.0, 2);
				System.out.println(BMI);
				if(BMI<18.5)limitCalories = 40.0* weight;//System.out.println("體重過重");
				else if(BMI>=18.5 && BMI < 24)limitCalories= 35.0* weight;//System.out.println("體重適中");
				else limitCalories = 30.0* weight;//System.out.println("體重過重");
	}
	@Override
	public chromosome<foodData>[] generateChromosomes(int size) {
		// TODO Auto-generated method stub
		Random random = new Random();
		foodData[] genes = new foodData[/*random.nextInt(55)*/8];
		chromosome<foodData>[] chrs = new chromosome[size];
		for(int i = 0; i < chrs.length; i++) {
			for(int j = 0; j < genes.length; j++) {
				genes[j] = FD[rand.nextInt(50)];
			}
			chrs[i] = new chromosome<foodData>();
			chrs[i].setGenes(genes);
		}
		return chrs;
	}

	@Override
	public foodData generateGene(int index) {
		// TODO Auto-generated method stub
		return FD[rand.nextInt(50)];
	}

	@Override
	public void calulateFitness(chromosome<foodData>[] chr) {
		// TODO Auto-generated method stub
		double totalCalories = 0.0d;					//Carbohydrate,Protein, Fat, type, Price
		double totalCarbohydrate = 0.0d;
		double totalProtein = 0.0d;
		double totalFat = 0.0d;
		int totalPrice = 0;
		for(int i = 0; i < chr.length; i++) {
			totalCalories = 0.0d;					
			totalCarbohydrate = 0.0d;
			totalProtein = 0.0d;
			totalFat = 0.0d;
			totalPrice = 0;
			for(int j = 0; j < chr[i].getChromosome().length; j++) {
				if(totalPrice<=budget) {
//					System.out.println("P:"+chr[0].getChromosome()[j].getPrice());
//					System.out.println("T:"+totalPrice);
					totalCalories += chr[i].getChromosome()[j].getCalories();
					totalPrice += chr[i].getChromosome()[j].getPrice();
					totalCarbohydrate += chr[i].getChromosome()[j].getCarbohydrate();
					totalProtein += chr[i].getChromosome()[j].getProtein();
					totalFat += chr[i].getChromosome()[j].getFat();
				}
				if(totalPrice>budget) {
					totalCalories = 0;
					break;
				}
				else if(totalCalories > limitCalories) {
					totalCalories = 0;
					break;
				}
				else if(totalCarbohydrate>310.0) {
					totalCalories = 0;
					break;
				}
				else if(totalProtein>120) {
					totalCalories = 0;
					break;
				}
				else if(totalFat>90) {
					totalCalories = 0;
					break;
				}

				
			}
			//System.out.println(totalPrice+" "+totalCalories+" "+totalCarbohydrate+" "+totalProtein+" "+totalFat);
			chr[i].setFitnessValue(totalCalories);
			
		}
	
	}
}
