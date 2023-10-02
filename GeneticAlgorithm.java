package GeneticAlgorithm;

import java.util.*;
public class GeneticAlgorithm<T> {
	
	Random rand = new Random();
	Problem<T> problem = (Problem<T>) new KcalProblem(rand);
	
	private chromosome<T>[] chromosome = null;
	private int chromosomeSize = 10;
	
	private chromosome<T>[] springs = new chromosome[4];
	private double mutationRate = 0.1;
	private double crossoverRate = 0.75;
	private int generationSize = 100;
	
	public GeneticAlgorithm() {
		rand.setSeed(4);
		int numberOfGeneration = 1;
		
		generateChromosome();  //step 1
		int i = 1;
		/*for(chromosome chr : chromosome) {
			System.out.print(i + "條染色體: " + Arrays.toString(chr.getChromosome()));
			System.out.println("\t" + chr.getFitnessValue());
			i++;
		}*/
	
		
		while(numberOfGeneration <= generationSize) {
			calFitnessValue();  //step 2
			selection(); //step 3
			crossover(); //step4
		
		
		
		/*System.out.println("-----------Crossover-----------");
		for(chromosome spr : springs) {
			System.out.println(Arrays.toString(spr.getChromosome()));
		}*/
		mutation();
		
		/*System.out.println("----------mutation-----------");
		for(chromosome spr : springs) {
			System.out.println(Arrays.toString(spr.getChromosome()));
		}*/
		
		calSpringsFitnessValue();
		
		updatingPopulation();

		/*int i = 1;
		for(chromosome spr : springs) {
			System.out.print(i + "孫子代" + Arrays.toString(spr.getChromosome()));
			System.out.println("\t" + spr.getFitnessValue());
			i++;
		}*/
		
		i = 1;
//		for(chromosome chr : chromosome) {
//			System.out.print(i + "條染色體: " + Arrays.toString(chr.getChromosome()));
//			System.out.println("\t" + chr.getFitnessValue());
			System.out.print("第" + numberOfGeneration + "次迭代， 最佳解為");
			for(Object food :chromosome[0].getChromosome()) {	
				i++;
				printFood(food);
			}
			System.out.println("\t "+chromosome[0].getFitnessValue());
//			System.out.println(i);
//		}
//		System.out.println("第" + numberOfGeneration + "次迭代， 最佳解為" + Arrays.toString(chromosome[0].getChromosome()) + "\tFitness = " + chromosome[0].getFitnessValue());
		numberOfGeneration++;
		}
	}
	private void crossover() {
		for(int i = 0; i < springs.length; i+=2) {
			if(rand.nextDouble() < crossoverRate) {
				twoPointChange(springs[i], springs[i + 1]);
			}
		}
	}
	
	private void updatingPopulation() {
		ArrayList<chromosome> pop = new ArrayList<chromosome>();
		for(chromosome chr : chromosome) {
			pop.add(chr);
		}
		for(chromosome chr : springs) {
			pop.add(chr);
		}
		int i = 1;
		/*for(chromosome chr : pop) {
			System.out.println(i++ + ":\t" + Arrays.toString(chr.getChromosome()) + "\t" + chr.getFitnessValue());
		}*/
		
		Collections.sort(pop, new Comparator<chromosome>() {
			
			@Override
			public int compare(chromosome arg0, chromosome arg1) {
				// TODO Auto-generated method stub
				if(arg1.getFitnessValue() > arg0.getFitnessValue()) {return 1;}
				else if(arg1.getFitnessValue() < arg0.getFitnessValue()) {return -1;}
				else {return 0;}
			}
		});
		/*System.out.println("pop sort");
		i = 1;
		for(chromosome chr : pop) {
			System.out.println(i++ + ":\t" + Arrays.toString(chr.getChromosome()) + "\t" + chr.getFitnessValue());
		}*/
		chromosome = new chromosome[chromosomeSize];
		for(int j = 0; j < chromosome.length; j++) {
			chromosome[j] = new chromosome();
			chromosome c = pop.remove(0);
			chromosome[j].setGenes((T[])c.getChromosome());
			chromosome[j].setFitnessValue(c.getFitnessValue());
		}
		/*System.out.println("------------------------");
		for(chromosome chr : chromosome) {
			System.out.println(Arrays.toString(chr.getChromosome()) + "\t" + chr.getFitnessValue());
		}*/
	}
	
	private void mutation() {
		for(chromosome spr : springs) {
			for(int i = 0; i < spr.getChromosome().length; i++) {
				if(rand.nextDouble() < mutationRate) {
					spr.getChromosome()[i] = problem.generateGene(i);
				}
			}
		}
	}
	
	private void twoPointChange(chromosome chr1, chromosome chr2) {
		int first = rand.nextInt(chr1.getChromosome().length);
		int second = rand.nextInt(chr1.getChromosome().length);
		if(first > second) {
			int temp = first;
			first = second;
			second = temp;
		}
		for(int i = first; i <= second; i++) {
			Object temp =  chr1.getChromosome()[i];
			chr1.getChromosome()[i] = chr2.getChromosome()[i];
			chr2.getChromosome()[i] = temp;
		}
	}
	
	private void selection() {
		LinkedList<chromosome> population = new LinkedList<chromosome>();
		for(chromosome chr:chromosome) {
			population.add(chr);
		}
		/*for(chromosome chr: population) {
			System.out.println(Arrays.toString(chr.getChromosome()));
		}*/
		for(int i = 0; i < springs.length; i++) {
			chromosome chr = population.remove(rand.nextInt(population.size()));
			springs[i] = new chromosome();
			springs[i].setGenes((T[])chr.getChromosome());
			springs[i].setFitnessValue(chr.getFitnessValue());
			
		}
		/*for(chromosome spr:springs) {
			System.out.println(Arrays.toString(spr.getChromosome()));
		}*/
	}
	/*private void generateChromosome() {
		double[] genes = new double[2];
		for(int i = 0; i < chromosome.length; i++) {
			for(int j = 0; j < genes.length; j++) {
				genes[j] = rand.nextDouble() * 10 - 5.0;
			}
			chromosome[i] = new chromosome();
			chromosome[i].setGenes(genes);
		}
	}*/
	
	private void generateChromosome() {
		chromosome = problem.generateChromosomes(chromosomeSize);
	}
	
	private void calSpringsFitnessValue() {
		/*for(int i = 0; i < chromosome.length; i++) {
			chromosome[i].setFitnessValue(
					testFunction(chromosome[i].getChromosome()));
		}*/
		problem.calulateFitness(springs);
	}
	
	private void calFitnessValue() {
		/*for(int i = 0; i < chromosome.length; i++) {
			chromosome[i].setFitnessValue(
					testFunction(chromosome[i].getChromosome()));
		}*/
		problem.calulateFitness(chromosome);
	}

	/*private double testFunction(double[] genes) {
		double value = 0.0;
		double x = genes[0];
		double y = genes[1];
		value = Math.pow(Math.pow(x, 2) + y -11, 2) + Math.pow(x + Math.pow(y,  2) - 7, 2);
		return value;
	}*/
	
	public void printFood(Object food) {
		foodData data = (foodData)food;
		System.out.print(data.getName()+"："+data.getCalories()+",");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GeneticAlgorithm<foodData>();
	}
}
