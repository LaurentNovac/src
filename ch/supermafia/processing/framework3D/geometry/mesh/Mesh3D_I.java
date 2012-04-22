
package ch.supermafia.processing.framework3D.geometry.mesh;

import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;

public interface Mesh3D_I
	{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public Mesh3D_I applyIdentity();
	public Mesh3D_I translate(Vec3D transVec);
	public Mesh3D_I scale(float s);

	}
