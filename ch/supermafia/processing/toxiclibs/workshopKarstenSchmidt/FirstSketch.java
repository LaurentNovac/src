
package ch.supermafia.processing.toxiclibs.workshopKarstenSchmidt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import processing.core.PApplet;
import toxi.color.TColor;
import toxi.data.feeds.AtomEntry;
import toxi.data.feeds.AtomFeed;
import toxi.geom.AABB;
import toxi.geom.Sphere;
import toxi.geom.Vec2D;
import toxi.geom.Vec3D;
import toxi.math.MathUtils;
import toxi.physics3d.VerletParticle3D;
import toxi.physics3d.VerletPhysics3D;
import toxi.physics3d.VerletSpring3D;
import toxi.processing.ToxiclibsSupport;

public class FirstSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 800, P3D);
		
		smooth();
		fill(0);
		gfx = new ToxiclibsSupport(this);
		physics = new VerletPhysics3D();
		scaleX = (float)width / (RESX - 1);
		scaleY = (float)height / (RESY - 1);
		
		createParticles();
		
		isOnline = false;
		query = "resonate_io";
		tweets = new ArrayList<TweetPoint>();
		initTwitter();
		tweetId = 0;
		}
	
	public void draw()
		{
		hint(ENABLE_DEPTH_TEST);
		background(0);
		pushMatrix();
		translate(width / 2, height / 2, 0);
		rotateX(radians(60));
		
		handleMouseMoved();
		lockParticleAngles();
		progress++;
		if (progress == TWEET_DELAY)
			{
			progress = 0;
			if (tweetId < tweets.size() - 1)
				{
				tweetId++;
				}
			jitter(tweets.get(tweetId).getGridPos());
			}
		stroke(255);
		fill(255);
		physics.update();
		//drawParticles();
		drawSprings();
		//drawTweets();
		popMatrix();
		hint(DISABLE_DEPTH_TEST);
		if (selection != null)
			{
			String text = selection.getTweet().title;
			fill(0, 200);
			float h = (float)(textWidth(text) / 200.0 * 14);
			rect(mouseX - 10, mouseY - 20, 220, h);
			fill(255);
			text(text, mouseX, mouseY, 200, 1000);
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void createParticles()
		{
		for(int y = 0; y < RESY; y++)
			{
			for(int x = 0; x < RESX; x++)
				{
				VerletParticle3D p = new VerletParticle3D(new Vec3D(x - RESX / 2, y - RESY / 2, 0).scaleSelf(scaleX, scaleY, 1));
				physics.addParticle(p);
				createParticleConnection(y, x, p);
				}
			}
		}
	
	private void createParticleConnection(int y, int x, VerletParticle3D p)
		{
		if (x > 0)
			{
			//create horizontal connection			
			VerletParticle3D q = physics.particles.get(index(x - 1, y));
			VerletSpring3D s = new VerletSpring3D(p, q, p.distanceTo(q), 0.1f);//last parameter is the strength between 0 and 1
			physics.addSpring(s);
			}
		if (y > 0)
			{
			//create vertical connection
			VerletParticle3D q = physics.particles.get(index(x, y - 1));
			VerletSpring3D s = new VerletSpring3D(p, q, p.distanceTo(q), 0.1f);//last parameter is the strength between 0 and 1
			physics.addSpring(s);
			}
		}
	
	private void lockParticleAngles()
		{
		physics.particles.get(index(0, 0)).lock();
		physics.particles.get(index(RESX - 1, 0)).lock();
		physics.particles.get(index(RESX - 1, RESY - 1)).lock();
		physics.particles.get(index(0, RESY - 1)).lock();
		}
	
	private void jitterRand()
		{
		if (MathUtils.randomChance(0.5f))
			{
			physics.particles.get((int)random(RESX * RESY)).jitter(50);
			}
		}
	
	private void jitter(Vec2D gp)
		{
		int idx = index((int)gp.x + RESX / 2, (int)gp.y + RESY / 2);
		physics.particles.get(idx).jitter(50);
		}
	
	private void initTwitter()//FIXME static in Twitter class
		{
		AtomFeed feed;
		uniqueAuthors = new HashMap<String, Integer>();
		if (!isOnline)
			{
			feed = AtomFeed.newFromStream(openStream("/Users/vacpics/Documents/dev./processing sketch/eclipseWorkspace_/processingSketches/search.atom"));
			}
		else
			{
			feed = AtomFeed.newFromURL("http://search.twitter.com/search.atom?q=" + query);
			}
		for(AtomEntry e:feed.entries)
			{
			String name = e.author.name;
			if (uniqueAuthors.containsKey(name))
				{
				int frequ = uniqueAuthors.get(name);
				frequ++;
				uniqueAuthors.put(name, frequ);
				System.out.println(name + " " + frequ);
				System.out.println(e.timePublished);
				}
			else
				{
				System.out.println("new name");
				uniqueAuthors.put(name, 1);
				System.out.println(name + " " + uniqueAuthors.get(name));
				}
			}
		sortFeeds(feed);
		//map entries on grid
		List<String> names = new ArrayList<String>(uniqueAuthors.keySet());
		for(AtomEntry e:feed.entries)
			{
			float x = map(names.indexOf(e.author.name), 0, uniqueAuthors.size() - 1, -RESX / 2, RESX / 2 - 1);
			
			float t = e.timePublished.toGregorianCalendar().getTimeInMillis();
			long tMin = feed.entries.get(0).timePublished.toGregorianCalendar().getTimeInMillis();
			long tMax = feed.entries.get(feed.entries.size() - 1).timePublished.toGregorianCalendar().getTimeInMillis();
			float y = map(t, tMin, tMax, RESY / 2 - 1, -RESY / 2);//newest tweet on top
			TweetPoint tp = new TweetPoint(new Vec2D(x, y), e);
			System.out.println(tp.getGridPos());
			tweets.add(tp);
			}
		}
	
	private void sortFeeds(AtomFeed feed)
		{
		Collections.sort(feed.entries, new Comparator<AtomEntry>()
			{
				
				@Override
				public int compare(AtomEntry a, AtomEntry b)
					{
					long timeA = a.timePublished.toGregorianCalendar().getTimeInMillis();
					long timeB = b.timePublished.toGregorianCalendar().getTimeInMillis();
					return (int)(timeB - timeA);
					}
				
			});
		}
	
	private void handleMouseMoved()
		{
		selection = null;
		for(int i = 0; i <= tweetId; i++)
			{
			TweetPoint tp = tweets.get(i);
			Vec2D gp = tp.getGridPos();
			int idx = index((int)gp.x + RESX / 2, (int)gp.y + RESY / 2);
			Vec3D pos = physics.particles.get(idx).add(0, 0, 10);//we add a little offset on z
			
			//draw as sphere
			gfx.stroke(TColor.newHSV(1.0f, 1.0f, 1.0f));
			gfx.sphere(new Sphere(pos, 10), 10);
			float sx = screenX(pos.x, pos.y, pos.z);
			float sy = screenY(pos.x, pos.y, pos.z);
			Vec2D sPos = new Vec2D(sx, sy);
			Vec2D mPos = new Vec2D(mouseX, mouseY);
			if (sPos.distanceTo(mPos) < 20)
				{
				selection = tp;
				break;
				}
			}
		}
	
	private void drawTweets()
		{
		for(int i = 0; i <= tweetId; i++)
			{
			TweetPoint tp = tweets.get(i);
			Vec2D gp = tp.getGridPos();
			int idx = index((int)gp.x + RESX / 2, (int)gp.y + RESY / 2);
			Vec3D pos = physics.particles.get(idx).add(0, 0, 10);//we add a little offset on z
			if (selection == tp)
				{
				gfx.fill(TColor.YELLOW);
				}
			else
				{
				gfx.fill(TColor.RED);
				}
			//draw as sphere
			gfx.sphere(new Sphere(pos, 10), 30);
			}
		}
	
	private void drawParticles()
		{
		noStroke();
//		try
//			{
//			sT1 = new Thread(new springThread(0, physics.particles.size() / 2, gfx, this, physics));
//			sT2 = new Thread(new springThread(physics.particles.size()/2, physics.particles.size()-1, gfx, this, physics));
//			sT1.start();
//			sT2.start();
//			sT1.join();
//			sT2.join();
//			}
//		catch (InterruptedException e)
//			{
//			e.printStackTrace();
//			}

		for(VerletParticle3D p:physics.particles)
			{
			gfx.fill(TColor.newHSV(map(p.z, -50, 50, 0, 1f), 1.0f, 1.0f));
			gfx.box(new AABB(p, 1));
			}
		}
	
	private void drawSprings()
		{
		for(VerletSpring3D s:physics.springs)
			{
			gfx.stroke(TColor.WHITE);
			gfx.line(s.a, s.b);
			}
		}
	
	private int index(int x, int y)
		{
		return y * RESX + x;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private final int RESX = 50;
	private final int RESY = 50;
	private final int TWEET_DELAY = 10;
	private VerletPhysics3D physics;
	private ToxiclibsSupport gfx;
	private boolean isOnline;
	private String query;
	private HashMap<String, Integer> uniqueAuthors;
	private List<TweetPoint> tweets;
	private int tweetId;
	private int progress;
	private float scaleX;
	private float scaleY;
	private TweetPoint selection;
	private Thread sT1;
	private Thread sT2;
	
	}
