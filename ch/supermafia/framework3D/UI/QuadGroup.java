
package ch.supermafia.framework3D.UI;

import java.util.ArrayList;
import java.util.List;

import ch.supermafia.framework3D.UI.view.WidgetView_I;
import ch.supermafia.framework3D.UI.widgets.view.QuadViewProcessing;

public class QuadGroup extends WidgetGroup
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public QuadGroup()
		{
		quads = new ArrayList<QuadViewProcessing>();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void select()
		{
		for(WidgetView_I widget:quads)
			{
			QuadViewProcessing q = (QuadViewProcessing)widget;
			q.getQuad().setSelected(true);
			}
		}
	
	public void deSelect()
		{
		for(WidgetView_I widget:quads)
			{
			QuadViewProcessing q = (QuadViewProcessing)widget;
			q.getQuad().setSelected(false);
			}
		}
	
	public void add(QuadViewProcessing widget)
		{
		quads.add(widget);
		}
	
	public void remove(QuadViewProcessing widget)
		{
		quads.remove(widget);
		}
	
	@Override
	public void draw()
		{
		for(QuadViewProcessing quad:quads)
			{
			quad.draw();
			}
		}
	
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("QuadGroup [quads=");
		builder.append(quads);
		builder.append("]");
		return builder.toString();
		}
	
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private List<QuadViewProcessing> quads;
	}
