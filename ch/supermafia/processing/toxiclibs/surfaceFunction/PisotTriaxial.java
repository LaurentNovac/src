/**
 * Based on Pisot Triaxial by Paul Bourke
 * http://paulbourke.net/geometry/pisot/
 */
package ch.supermafia.processing.toxiclibs.surfaceFunction;

import toxi.geom.Vec3D;
import toxi.geom.mesh.SurfaceFunction;
import toxi.math.MathUtils;


public class PisotTriaxial implements SurfaceFunction
	{

	@Override
	public Vec3D computeVertexFor(Vec3D p, float phi, float theta)
		{
		p.x=0.655866f*MathUtils.cos(1.03002f+phi)*(2+MathUtils.cos(theta));
		p.y=0.754878f*MathUtils.cos(1.40772f-phi)*(2+0.868837f*MathUtils.cos(2.43773f+theta));
		p.z=0.868837f*MathUtils.cos(2.43773f+phi)*(2+0.495098f*MathUtils.cos(0.377696f-theta));
		return p;
		}

	@Override
	public float getPhiRange()
		{
		return MathUtils.TWO_PI;
		}

	@Override
	public int getPhiResolutionLimit(int res)
		{
		return res;
		}

	@Override
	public float getThetaRange()
		{
		return MathUtils.TWO_PI;
		}

	@Override
	public int getThetaResolutionLimit(int res)
		{
		return res;
		}
	
	}
