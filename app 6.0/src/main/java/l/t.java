package l;

import android.service.quicksettings.*;
import android.content.*;
import java.lang.reflect.*;

public class t
{
	
	static void o(Context c,int i)
	{
		c.startActivity(new Intent().setClassName("l.c","l.l").putExtra("", i));
		try
		{
			Object o = c.getSystemService("statusbar");
			Method m = o.getClass().getMethod("collapsePanels");
			m.invoke(o);
		}
		catch (Exception e){}
	}
	
	
	public static class lc extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,-1);
		}
		@Override
		public void onStartListening()
		{
			getQsTile().setState(Tile.STATE_INACTIVE);
            getQsTile().updateTile();
			super.onStartListening();
		}
	}
	public static class l00 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,0);
		}
		
	}
	public static class l01 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,1);
		}
		
	}
	public static class l02 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,2);
		}
		
	}
	public static class l03 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,3);
		}
		
	}
	public static class l04 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,4);
		}
		
	}
	public static class l05 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,5);
		}
		
	}
	public static class l06 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,6);
		}
		
	}
	public static class l07 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,7);
		}
		
	}
	public static class l08 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,8);
		}
		
	}
	public static class l09 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,9);
		}
		
	}
	public static class l10 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,10);
		}
		
	}
	public static class l11 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,11);
		}
		
	}
	public static class l12 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,12);
		}
		
	}
	public static class l13 extends TileService
	{

		@Override
		public void onClick()
		{
			o(this,13);
		}
		
	}
}
