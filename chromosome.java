package GeneticAlgorithm;
import java.util.*;
public class chromosome<T> {
	private T[] genes = null;
	private double fitnessValue = 0.0;
	Random rand = new Random();
	public chromosome() {
		
	}
	
	public void setGenes(T[] value) {
		genes = value.clone();
	}
	
	public T[] getChromosome() {
		return genes;
	}
	
	public void setFitnessValue(double value) {
		fitnessValue = value;
	}
	
	public double getFitnessValue() {
		return fitnessValue;
	}
}
