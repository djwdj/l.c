package l;
import android.service.quicksettings.*;
import android.content.*;
import java.lang.reflect.*;
import android.os.*;

public class t
{
	static void c(Context c)
	{
		try
		{
			Object o = c.getSystemService("statusbar");
			Method m = o.getClass().getMethod("collapsePanels");
			m.invoke(o);
		}
		catch (Exception e){}
	}
	public static class l extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class));
			super.onClick();
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
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 0));
			super.onClick();
		}
		
	}
	public static class l01 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 1));
			super.onClick();
		}
		
	}
	public static class l02 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 2));
			super.onClick();
		}
		
	}
	public static class l03 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 3));
			super.onClick();
		}
		
	}
	public static class l04 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 4));
			super.onClick();
		}
		
	}
	public static class l05 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 5));
			super.onClick();
		}
		
	}
	public static class l06 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 6));
			super.onClick();
		}
		
	}
	public static class l07 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 7));
			super.onClick();
		}
		
	}
	public static class l08 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 8));
			super.onClick();
		}
		
	}
	public static class l09 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 9));
			super.onClick();
		}
		
	}
	public static class l10 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 10));
			super.onClick();
		}
		
	}
	public static class l11 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 11));
			super.onClick();
		}
		
	}
	public static class l12 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 12));
			super.onClick();
		}
		
	}
	public static class l13 extends TileService
	{

		@Override
		public void onClick()
		{
			c(this);
			startActivity(new Intent(this,l.class).putExtra("", 13));
			super.onClick();
		}
		
	}
}
