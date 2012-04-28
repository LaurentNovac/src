
package ch.supermafia.processing.waves.lissajous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;

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
		frequCarrier = 1;
		margin = 0;
		factorX = width / 2 - margin;
		factorY = height / 2 - margin;
		phi = 0;
		pointCount = width / 4;
		jFrameLissajous = new JFrameLissajous(this);
		song = minim.loadFile("mySong.wav", 512);
		song.play();
		fft = new FFT(song.bufferSize(), song.sampleRate());
		listBand = new ArrayList<Float>();
		lissajousPoints = new Vec3D[pointCount];
		frequXFactor = 40.0f;
		frequYFactor = 40.0f;
		isDrawFFT = false;
		isAnim2 = false;
		strokeWeight(1.0f);
		}
	
	public void draw()
		{
		//background(255);
		fill(0, 0, 0, 40);
		noStroke();
		rect(0, 0, width, height);
		noFill();
		clearBandList();
		fft.forward(song.mix);
		sortFFTHistogram();
		if (isDrawFFT)
			{
			drawFFT();
			}
		
		translate(width / 2, height / 2);
		frequX = listBand.get(listBand.size() - 2) / frequXFactor;
		frequY = listBand.get(listBand.size() - 3) / frequYFactor;
		frequCarrier=listBand.get(listBand.size() - 4) / frequXFactor;
		calculateLissajous();
		drawLissajous();
		System.out.println(frameRate);
		}
	
	public void keyPressed()
		{
		switch(key)
			{
			case 'f':
				isDrawFFT = !isDrawFFT;
				break;
			case 'a':
				isAnim2 = !isAnim2;
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
		stroke(255);
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
	
	private void calculateLissajous()
		{
		for(int i = 0; i < pointCount; i++)
			{
			float angle = map(i, 0, pointCount, 0, TWO_PI);
			float x = factorX * sin(angle * frequX + radians(phi))*cos(angle*frequCarrier);
			float y = factorY * sin(angle * frequY)*cos(angle*frequCarrier);
			Vec3D v = new Vec3D(x, y);
			lissajousPoints[i] = v;
			}
		}
	
	private void drawLissajous()
		{
		float connectionRadius = 200;
		float d;
		float a;
		Vec3D v1;
		Vec3D v2;
		for(int i = 0; i < pointCount; i++)
			{
			for(int j = 0; j < i; j++)
				{
				v1 = lissajousPoints[i];
				v2 = lissajousPoints[j];
				if (isAnim2)
					{
					if (i % 2 == 0)
						{
						float tmp = v1.x();
						v1.setX(-v1.y());
						v1.setY(tmp);
						
						tmp = v2.x();
						v2.setX(-v2.y());
						v2.setY(tmp);
						}
					}
				
				d = v1.distanceTo(v2);
				a = (float)Math.pow(1 / (d / connectionRadius + 1), 6);
				
				if (d <= connectionRadius)
					{
					stroke(255, 255, 255, a * 255);
					line(v1.x(), v1.y(), v2.x(), v2.y());
					}
				}
			}
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
	private float frequCarrier;
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
	private Vec3D[] lissajousPoints;
	private boolean isAnim2;
	}
