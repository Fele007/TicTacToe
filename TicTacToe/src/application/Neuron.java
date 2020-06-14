package application;

import java.util.concurrent.ThreadLocalRandom;

public class Neuron {
	public double activationLevel;
	public double[] weights;
	private int activationFunction, noOfInputs;

	public Neuron(int activationFunction, int noOfinputs) {
		this.activationFunction = activationFunction;
		this.noOfInputs = noOfinputs;
		weights = new double[noOfInputs];
		for (int i = 0; i < noOfInputs; i++) {
			weights[i] = ThreadLocalRandom.current().nextDouble(0.0, 1.0);
		}
	}

	public double activate(double[] inputs) {
		// Propagate Inputs
		double netInput = 0;
		for (int i = 0; i < inputs.length; i++) {
			//System.out.println(inputs.length);
			netInput += inputs[i] * weights[i];
		}
		switch (activationFunction) {
		case 1: {
			activationLevel = netInput;
			break;
		}
		case 2: {
			// Calculate Activation Level
			if (netInput < 0.2) {
				activationLevel = 0;
			} else if (netInput > 0.8) {
				activationLevel = 1;
			} else
				activationLevel = netInput;
			break;
		}
		case 3: {
			if (netInput < 0.66)
				activationLevel = 0;
			else if (netInput > 1.33)
				activationLevel = 2;
			else
				activationLevel = 1;
		}
		case 4: {
			activationLevel = (1 / (1 + Math.pow(Math.E, (-1 * netInput))));
		}
		}
		return activationLevel;
	}

}