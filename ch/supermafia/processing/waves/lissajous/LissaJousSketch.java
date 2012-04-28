
package ch.supermafia.processing.waves.lissajous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class LissaJousSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(1024, 768, P3D);
		smooth();
		initMinim();
		frequX = 1;
		frequY = 1;
		margin = 0;
		factorX = width / 2 - margin;
		factorY = height / 2 - margin;
		phi = 0;
		pointCount = width * 4;
		jFrameLissajous = new JFrameLissajous(this);
		song = minim.loadFile("mySong.wav", 512);
		song.play();
		fft = new FFT(song.bufferSize(), song.sampleRate());
		listBand = new ArrayList<Float>();
		frequXFactor = 40.0f;
		frequYFactor = 40.0f;
		isDrawFFT = false;
		}
	
	public void draw()
		{
		//background(255);
		fill(255, 255, 255, 40);
		noStroke();
		rect(0, 0, width, height);
		noFill();
		clearBandList();
		fft.forward(song.mix);
		sortFFTHistogram();
		stroke(0);
		strokeWeight(random(1.0f, 5.0f));
		if (isDrawFFT)
			{
			drawFFT();
			}
		
		translate(width / 2, height / 2);
		frequX = listBand.get(listBand.size() - 2) / frequXFactor;
		frequY = listBand.get(listBand.size() - 3) / frequYFactor;
		beginShape();
		for(int i = 0; i < pointCount; i++)
			{
			float angle = map(i, 0, pointCount, 0, TWO_PI);
			float x = factorX * sin(angle * frequX + radians(phi));
			float y = factorY * sin(angle * frequY);
			vertex(x, y);
			}
		endShape();
		}
	
	public void keyPressed()
		{
		switch(key)
			{
			case 'f':
				isDrawFFT = !isDrawFFT;
				break;
			
			default:
				break;
			}
		}
	
	public void stop()
		{
		minim.stop();
		song.close();
		super.stop();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void initMinim()
		{
		minim = new Minim(this);
		}
	
	private void drawFFT()
		{
		for(int i = 0; i < fft.specSize(); i++)
			{
			line(i, height, i, height - fft.getBand(i) * 4);
			}
		}
	
	private void sortFFTHistogram()
		{
		for(int i = 0; i < fft.specSize(); i++)
			{
			listBand.add(fft.getBand(i));
			}
		Collections.sort(listBand);
		}
	
	private void clearBandList()
		{
		listBand.clear();
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public float getFrequX()
		{
		return frequX;
		}
	
	public float getFrequY()
		{
		return frequY;
		}
	
	public float getPhi()
		{
		return phi;
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	public void setFrequX(float frequX)
		{
		this.frequX = frequX;
		}
	
	public void setFrequY(float frequY)
		{
		this.frequY = frequY;
		}
	
	public void setPhi(int phi)
		{
		this.phi = phi;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private int pointCount;
	private float frequX;
	private float frequY;
	private int phi;
	private int margin;
	private int factorX;
	private int factorY;
	private Float frequXFactor;
	private Float frequYFactor;
	
	@SuppressWarnings("unused")
	private JFrameLissajous jFrameLissajous;
	
	private Minim minim;
	private AudioPlayer song;
	private FFT fft;
	private List<Float> listBand;
	private boolean isDrawFFT;
	}
