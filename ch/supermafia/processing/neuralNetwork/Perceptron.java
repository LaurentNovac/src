package ch.supermafia.processing.neuralNetwork;

import java.util.Arrays;

import processing.core.PApplet;


public class Perceptron
	{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Perceptron(int n,float c,PApplet parent)
		{
		this.c = c;
		weights=new float[n];
		for(int i = 1; i <= weights.length; i++)
			{
			weights[i-1]=parent.random(-1,1);
			}
		}
	
	public Perceptron(Perceptron src)
		{
		this(src.weights.length,src.c,src.parent);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public String toString()
		{
		return "Perceptron [weights=" + Arrays.toString(weights) + ", c=" + c + ", parent=" + parent + "]";
		}

	public int guess(float[] vals)
		{
			float sum=0;
			for(int i = 1; i <= vals.length; i++)
				{
				sum+=vals[i-1]*weights[i-1];
				}
			return (sum>=0) ? 1:-1;
		}
	
	public void train(float[] vals, int desired)
		{
		int result = guess(vals);
	    // Compute the factor for changing the weight based on the error
	    // Error = desired output - guessed output
	    // Note this can only be 0, -2, or 2
	    // Multiply by learning constant
		float weightChange=c*(desired-result);
		// Adjust weights based on weightChange * input
		for(int i = 1; i <= weights.length; i++)
			{
			weights[i-1]+=weightChange*vals[i-1];
			}
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//tools
	private float[] weights;
	
	//input
	private float c;//learning constant
	private PApplet parent;

	}
