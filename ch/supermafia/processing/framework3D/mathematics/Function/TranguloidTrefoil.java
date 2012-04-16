
package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.Vec3D;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.PI;

public class TranguloidTrefoil implements FunctionR2R3_I
	{
	
	@Override
	public Vec3D f(float u, float v)
		{
		float x = (float)(2 * sin(3 * u) / (2 + cos(v)));
		float y = (float)(2 * (sin(u) + 2 * sin(2 * u)) / (2 + cos(v + 2 * PI / 3)));
		float z = (float)((cos(u) - 2 * cos(2 * u)) * (2 + cos(v)) * (2 + cos(v + 2 * PI / 3)) / 4);
		return new Vec3D(x, y, z);
		}

	@Override
	public float getUmin()
		{
		// TODO Auto-generated method stub
		return 0;
		}

	@Override
	public float getUMax()
		{
		// TODO Auto-generated method stub
		return 0;
		}

	@Override
	public float getVmin()
		{
		// TODO Auto-generated method stub
		return 0;
		}

	@Override
	public float getVMax()
		{
		// TODO Auto-generated method stub
		return 0;
		}
	}
