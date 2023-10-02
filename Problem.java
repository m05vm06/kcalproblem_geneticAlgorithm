package GeneticAlgorithm;

public interface Problem<T> {
	public chromosome<T>[] generateChromosomes(int size);
	public T generateGene(int index);
	public void calulateFitness(chromosome<T>[] chr);
}
