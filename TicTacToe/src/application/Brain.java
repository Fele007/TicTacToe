package application;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Brain {
	private ArrayList<Layer> layers;
	private EvolvingPlayer player;
	private int inputNeuronCount, outputNeuronCount;
	private int decision;

	public Brain(EvolvingPlayer player, int layerCount, int inputNeuronCount, int outputNeuronCount,
			int hiddenLayerNeuronCount) {
		this.player = player;
		this.inputNeuronCount = inputNeuronCount;
		this.outputNeuronCount = outputNeuronCount;
		this.layers = new ArrayList<Layer>();
		layers.add(new Layer(hiddenLayerNeuronCount, inputNeuronCount, 4));
		for (int i = 0; i < layerCount - 2; i++) {
			layers.add(new Layer(hiddenLayerNeuronCount, hiddenLayerNeuronCount, 4));
		}
		layers.add(new Layer(outputNeuronCount, hiddenLayerNeuronCount, 4));
	}

	public int[] think(Game game) {
		double[] inputs = new double[inputNeuronCount];
		double[] outputs = new double[outputNeuronCount];
		// Empty
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j <= 2; j++) {
				for (int k = 0; k <= 2; k++) {
					if (game.board.fields[j][k].fieldState.getValue() == 3) {
						inputs[i] = 1;
					}
				}
			}
		}
		// X
		for (int i = 9; i < 18; i++) {
			for (int j = 0; j <= 2; j++) {
				for (int k = 0; k <= 2; k++) {
					if (game.board.fields[j][k].fieldState.getValue() == 1) {
						inputs[i] = 1;
					}
				}
			}
		}
		for (int i = 18; i < 27; i++) {
			for (int j = 0; j <= 2; j++) {
				for (int k = 0; k <= 2; k++) {
					if (game.board.fields[j][k].fieldState.getValue() == 2) {
						inputs[i] = 1;
					}
				}
			}
		}
		outputs = inputs;
		for (Layer layer : layers) {
			outputs = layer.activate(outputs);
		}
		decision = ThreadLocalRandom.current().nextInt(0, 9);
		double maxOutput = outputs[decision];
		for (int i = 1; i < outputs.length; i++) {
			if (outputs[i] > maxOutput) {
				maxOutput = outputs[i];
				decision = i;
			}
		}
		switch (decision) {
		case 0:
			return new int[] { 0, 0 };
		case 1:
			return new int[] { 0, 1 };
		case 2:
			return new int[] { 0, 2 };
		case 3:
			return new int[] { 1, 0 };
		case 4:
			return new int[] { 1, 1 };
		case 5:
			return new int[] { 1, 2 };
		case 6:
			return new int[] { 2, 0 };
		case 7:
			return new int[] { 2, 1 };
		case 8:
			return new int[] { 2, 2 };
		default:
			System.out.println("Switch Error");
			return new int[] { 0, 0 };
		}
	}

	public void reward(Game game, double score) {
		// Output Layer Weights
		double maxWeight = 0;
		int maxWeightIndex = 0;
		for (int i = 0; i < layers.get(layers.size() - 1).neurons.get(decision).weights.length - 1; i++) {
			if (maxWeight < layers.get(layers.size() - 1).neurons.get(decision).weights[i]) {
				maxWeight = layers.get(layers.size() - 1).neurons.get(decision).weights[i];
				maxWeightIndex = i;
			}
			layers.get(layers.size() - 1).neurons.get(decision).weights[i] += score;
		}
		// Other Weights
		int newMaxWeightIndex = 0;
		double newMaxWeight = 0;
		for (int i = layers.size() - 2; i > 0; i--) {
			for (int j = 0; j < layers.get(i).neurons.get(maxWeightIndex).weights.length - 1; j++) {
				if (newMaxWeight < layers.get(i).neurons.get(maxWeightIndex).weights[j]) {
					newMaxWeight = layers.get(i).neurons.get(maxWeightIndex).weights[j];
					newMaxWeightIndex = j;
				}
				layers.get(i).neurons.get(maxWeightIndex).weights[j] += score/5.0 * i;
			}
			maxWeight = newMaxWeight;
			maxWeightIndex = newMaxWeightIndex;
		}
	}

	public void punish(Game game) {
		// Output Layer Weights
		double maxWeight = 0;
		int maxWeightIndex = 0;
		for (int i = 0; i < layers.get(layers.size() - 1).neurons.get(decision).weights.length - 1; i++) {
			if (maxWeight < layers.get(layers.size() - 1).neurons.get(decision).weights[i]) {
				maxWeight = layers.get(layers.size() - 1).neurons.get(decision).weights[i];
				maxWeightIndex = i;
			}
			layers.get(layers.size() - 1).neurons.get(decision).weights[i] -= 0.01f;
		}
		// Other Weights
		int newMaxWeightIndex = 0;
		double newMaxWeight = 0;
		for (int i = layers.size() - 2; i > 0; i--) {
			for (int j = 0; j < layers.get(i).neurons.get(maxWeightIndex).weights.length - 1; j++) {
				if (newMaxWeight < layers.get(i).neurons.get(maxWeightIndex).weights[j]) {
					newMaxWeight = layers.get(i).neurons.get(maxWeightIndex).weights[j];
					newMaxWeightIndex = j;
				}
				layers.get(i).neurons.get(maxWeightIndex).weights[j] -= 0.001f * i;
			}
			maxWeight = newMaxWeight;
			maxWeightIndex = newMaxWeightIndex;
		}
		
		  /*System.out.println("ActivationLevels"); for (int i = 0;
		  i<layers.get(layers.size()-1).neurons.size(); i++) {
		  System.out.println(layers.get(layers.size()-1).neurons.get(i).activationLevel
		  ); } */
		  
		  /* for (int i = layers.size()-1; i>0; i--) { for (int j = 0;
		 * i<layers.get(i).neurons.size(); j++) { if
		 * (layers.get(i).neurons.get(j).activationLevel> } }
		 */
	}

}
