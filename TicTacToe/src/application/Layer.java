package application;
import java.util.ArrayList;

public class Layer {
	  public ArrayList<Neuron> neurons = new ArrayList<Neuron>();
	  public Layer() {
	  }
	  public Layer (int neuronCount, int neuronInputCount, int activationFunction) {
	    for (int i = 0; i<neuronCount; i++) {
	      neurons.add(new Neuron(activationFunction, neuronInputCount));
	    }
	  }

	  public double [] activate (double [] inputs) {
	    double [] output = new double [neurons.size()];
	    for (int i = 0; i<neurons.size(); i++) {
	      output[i] = neurons.get(i).activate(inputs);
	    }
	    return output;
	  }
	}