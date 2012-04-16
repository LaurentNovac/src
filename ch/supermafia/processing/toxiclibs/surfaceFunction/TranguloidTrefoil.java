/**
 * Based on Tranguloid Trefoil by Paul Bourke
 * http://paulbourke.net/geometry/tranguloid/
 */
package ch.supermafia.processing.toxiclibs.surfaceFunction;

import toxi.geom.Vec3D;
import toxi.geom.mesh.SurfaceFunction;
import toxi.math.MathUtils;

public class TranguloidTrefoil implements SurfaceFunction
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D computeVertexFor(Vec3D p, float phi, float theta)
		{
		p.x=2*MathUtils.sin(3*phi)/(2+MathUtils.cos(theta)) ;													//x = 2 sin(3 u) / (2 + cos(v))
		p.y=2*(MathUtils.sin(phi)+2*MathUtils.sin(2*phi))/(2+MathUtils.cos(theta+2*MathUtils.PI/3)); 			//y = 2 (sin(u) + 2 sin(2 u)) / (2 + cos(v + 2 pi / 3))
		p.z=(MathUtils.cos(phi)-2*MathUtils.cos(2*phi))*(2+MathUtils.cos(theta))*(2+MathUtils.cos(theta+2*MathUtils.PI/3))/4;//z = (cos(u) - 2 cos(2 u)) (2 + cos(v)) (2 + cos(v + 2 pi / 3)) / 4
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
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	}
