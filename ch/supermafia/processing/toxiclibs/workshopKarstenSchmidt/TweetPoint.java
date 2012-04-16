
package ch.supermafia.processing.toxiclibs.workshopKarstenSchmidt;

import toxi.data.feeds.AtomEntry;
import toxi.geom.Vec2D;

public class TweetPoint
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public TweetPoint(Vec2D gridPos, AtomEntry tweet)
		{
		this.gridPos = gridPos;
		this.tweet = tweet;
		}
	
	public Vec2D getGridPos()
		{
		return gridPos;
		}
	
	public void setGridPos(Vec2D gridPos)
		{
		this.gridPos = gridPos;
		}
	
	public AtomEntry getTweet()
		{
		return tweet;
		}
	
	public void setTweet(AtomEntry tweet)
		{
		this.tweet = tweet;
		}
		/*------------------------------------------------------------------*\
		|*							Attributs Private						*|
		\*------------------------------------------------------------------*/
		private Vec2D gridPos;
		private AtomEntry tweet;
		
		
	
	}
