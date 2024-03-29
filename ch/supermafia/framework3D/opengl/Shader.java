
package ch.supermafia.framework3D.opengl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.opengl.GL2;

import ch.supermafia.framework3D.geometry.matrix.Matrix4x4;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.geometry.vector.Vec4D;

//The shader control class.
//loads and starts/stops shaders.
public class Shader
	{
	
	// this will attach the shaders
	public void init(GL2 gl)
		{
		try
			{
			attachShaders(gl);
			}
		catch (Exception e)
			{
			e.printStackTrace();
			}
		}
	
	// loads the shaders
	// in this example we assume that the shader is a file located in the applications JAR file.
	//
	public String[] loadShader(String name)
		{
		StringBuilder sb = new StringBuilder();
		try
			{
			BufferedReader br = new BufferedReader(new FileReader(name));
			String line;
			while((line = br.readLine()) != null)
				{
				sb.append(line);
				sb.append('\n');
				}
			}
		catch (Exception e)
			{
			e.printStackTrace();
			}
		System.out.println("Shader is " + sb.toString());
		return new String[] { sb.toString() };
		}
	
	// This compiles and loads the shader to the video card.
	// if there is a problem with the source the program will exit at this point.
	//
	private void attachShaders(GL2 gl) throws Exception
		{
		vertexShaderProgram = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
		fragmentShaderProgram = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);
		gl.glShaderSource(vertexShaderProgram, 1, vsrc, null, 0);
		gl.glCompileShader(vertexShaderProgram);
		gl.glShaderSource(fragmentShaderProgram, 1, fsrc, null, 0);
		gl.glCompileShader(fragmentShaderProgram);
		shaderprogram = gl.glCreateProgram();
		//
		gl.glAttachShader(shaderprogram, vertexShaderProgram);
		gl.glAttachShader(shaderprogram, fragmentShaderProgram);
		gl.glLinkProgram(shaderprogram);
		gl.glValidateProgram(shaderprogram);
		IntBuffer intBuffer = IntBuffer.allocate(1);
		gl.glGetProgramiv(shaderprogram, GL2.GL_LINK_STATUS, intBuffer);
		
		if (intBuffer.get(0) != 1)
			{
			gl.glGetProgramiv(shaderprogram, GL2.GL_INFO_LOG_LENGTH, intBuffer);
			int size = intBuffer.get(0);
			System.err.println("Program link error: ");
			if (size > 0)
				{
				ByteBuffer byteBuffer = ByteBuffer.allocate(size);
				gl.glGetProgramInfoLog(shaderprogram, size, intBuffer, byteBuffer);
				for(byte b:byteBuffer.array())
					{
					System.err.print((char)b);
					}
				}
			else
				{
				System.out.println("Unknown");
				}
			System.exit(1);
			}
		}
	
	// this function is called when you want to activate the shader.
	// Once activated, it will be applied to anything that you draw from here on
	// until you call the dontUseShader(GL) function.
	public int useShader(GL2 gl)
		{
		gl.glUseProgram(shaderprogram);
		return shaderprogram;
		}
	
	// when you have finished drawing everything that you want, using the shaders, 
	// call this to stop further shader interactions.
	public void dontUseShader(GL2 gl)
		{
		gl.glUseProgram(0);
		}
	
	//assigns uniform variable in the program
	public void writeUniform(GL2 gl, String name, float value)
		{
		int loc = gl.glGetUniformLocation(shaderprogram, name);
		if (loc == -1)
			{
			Logger.getLogger("GLSL uniform").log(Level.SEVERE, "uniform variable could not be assigned");
			System.exit(-1);
			}
		gl.glUniform1f(loc, value);
		}
	
	public void writeUniform(GL2 gl, String name, int value)
		{
		int loc = gl.glGetUniformLocation(shaderprogram, name);
		if (loc == -1)
			{
			Logger.getLogger("GLSL uniform").log(Level.SEVERE, "uniform variable could not be assigned");
			System.exit(-1);
			}
		gl.glUniform1i(loc, value);
		}
	
	public void writeUniform(GL2 gl, String name, Vec3D vec)
		{
		int loc = gl.glGetUniformLocation(shaderprogram, name);
		if (loc == -1)
			{
			Logger.getLogger("GLSL uniform").log(Level.SEVERE, "uniform variable could not be assigned");
			System.exit(-1);
			}
		gl.glUniform3f(loc, vec.x(), vec.y(), vec.z());
		}
	
	public void writeUniform(GL2 gl, String name, Vec4D vec)
		{
		int loc = gl.glGetUniformLocation(shaderprogram, name);
		if (loc == -1)
			{
			Logger.getLogger("GLSL uniform").log(Level.SEVERE, "uniform variable could not be assigned");
			System.exit(-1);
			}
		gl.glUniform4f(loc, vec.x(), vec.y(), vec.z(), vec.w());
		}
	
	public void writeUniform(GL2 gl, String name, Matrix4x4 mat)
		{
		int loc = gl.glGetUniformLocation(shaderprogram, name);
		if (loc == -1)
			{
			Logger.getLogger("GLSL uniform").log(Level.SEVERE, "uniform variable could not be assigned");
			System.exit(-1);
			}
		
		gl.glUniformMatrix4fvARB(loc, 1, true, mat.getData(), 0);
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public String[] getVsrc()
		{
		return vsrc;
		}
	
	public String[] getFsrc()
		{
		return fsrc;
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	public void setVsrc(String[] vsrc)
		{
		this.vsrc = vsrc;
		}
	
	public void setFsrc(String[] fsrc)
		{
		this.fsrc = fsrc;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private int vertexShaderProgram;
	private int fragmentShaderProgram;
	private int shaderprogram;
	
	private String[] vsrc;
	private String[] fsrc;
	
	}
