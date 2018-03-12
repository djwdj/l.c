package l;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.WindowManager.*;
import android.view.animation.*;
import android.widget.*;
import java.io.*;
import java.util.*;

public class l extends Activity implements OnTouchListener,Runnable,OnLongClickListener
{
	float x1,x2,y1;
	ScrollView sc;
	LinearLayout l,l0,ll,l1,l2;
	LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1);
	float d,z;
	int w,h,p,m;
	Animation sa,aa;
	AnimationSet as;
	Button b;
	View v;
	String s1="尝试执行",s2="请ROOT后再试!";
	String ss="%s\nam force-stop %s\n";
	String[] s=
	{
		"reboot",
		"reboot -p",
		"reboot recovery",
		"reboot bootloader",
		"busybox pkill com.android.systemui",
		"setprop ctl.restart zygote",
		"input keyevent KEYCODE_POWER",
		"svc power reboot",
		"svc power shutdown",
		"svc power reboot recovery",
		"svc power reboot bootloader",
		"setprop persist.sys.safemode 1 \n svc power reboot",
		"setprop persist.sys.safemode 1 \n reboot",
		"reboot edl"
	};

	private long t;

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

		int e=getIntent().getIntExtra("", -1);
		if (e != -1)
		{
			s(s[e]);
			f();
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setNavigationBarColor(0);
			getWindow().setStatusBarColor(0);
		}else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
			getWindow().addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS |
								 LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		else getWindow().addFlags(LayoutParams.FLAG_FULLSCREEN);
		
		DisplayMetrics dm = getResources().getDisplayMetrics();
		d = dm.density;
		if (dm.widthPixels < dm.heightPixels)
		{
			w = dm.widthPixels;
			h = dm.heightPixels;
		}else{
			h = dm.widthPixels;
			w = dm.heightPixels;
		}
		p=w/36;
		m=w/60;
		z=w/d/20;
		
		lp.setMargins(m,m,m,m);

		l=new LinearLayout(this);
		l.setPadding(p,p,p,p);
		l.setGravity(Gravity.CENTER);
		l.setOnTouchListener(this);
		l.setClickable(true);
		
		aa=new AlphaAnimation(0.1f, 1);
		aa.setDuration(500);
		l.startAnimation(aa);

		l0=new LinearLayout(this);
		l0.setOrientation(LinearLayout.VERTICAL);
		l0.setBackgroundDrawable(d(0xbbcccccc));
		l0.setLayoutParams(lp);
		l.addView(l0);

		sc=new ScrollView(this);
		sc.setVerticalScrollBarEnabled(false);
		l0.addView(sc);

		ll=new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		sc.addView(ll);

		l1=new LinearLayout(this);
		l1.setLayoutParams(lp);
		l1.setOrientation(LinearLayout.VERTICAL);
		ll.addView(l1);

		l2=new LinearLayout(this);
		l2.setLayoutParams(lp);
		l2.setVisibility(View.GONE);
		l2.setOrientation(LinearLayout.VERTICAL);
		ll.addView(l2);

		for(int i=0;i<s.length;i++)b(i);
		
		l.setFitsSystemWindows(true);
		addContentView(l, new LayoutParams());
		
		if (Build.VERSION.SDK_INT >= 25)s();
        
    }
	
	void s()
	{
		ShortcutInfo 
			s4 = new ShortcutInfo.Builder(this, "id4")
			.setShortLabel(getResources().getString(0x7f030006))
			.setIcon(Icon.createWithResource(this, android.R.drawable.ic_lock_lock))
			.setIntent(new Intent(Intent.ACTION_MAIN).setClass(this, l.class).putExtra("", 6))
			.build(),
			s3 = new ShortcutInfo.Builder(this, "id3")
			.setShortLabel(getResources().getString(0x7f030002))
			.setIcon(Icon.createWithResource(this, android.R.drawable.stat_notify_sync))
			.setIntent(new Intent(Intent.ACTION_MAIN).setClass(this, l.class).putExtra("", 2))
			.build(),
			s2 = new ShortcutInfo.Builder(this, "id2")
			.setShortLabel(getResources().getString(0x7f030001))
			.setIcon(Icon.createWithResource(this, android.R.drawable.ic_lock_power_off))
			.setIntent(new Intent(Intent.ACTION_MAIN).setClass(this, l.class).putExtra("", 1))
			.build(),
			s1 = new ShortcutInfo.Builder(this, "id1")
			.setShortLabel(getResources().getString(0x7f030000))
			.setIcon(Icon.createWithResource(this, android.R.drawable.stat_notify_sync))
			.setIntent(new Intent(Intent.ACTION_MAIN).setClass(this, l.class).putExtra("", 0))
			.build();
		getSystemService(ShortcutManager.class).setDynamicShortcuts(Arrays.asList(s4,s3,s2,s1));
		
	}
	
	void s(String n,Intent I)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
			getSystemService(ShortcutManager.class)
				.requestPinShortcut(new ShortcutInfo.Builder(this, n)
									.setIcon(Icon.createWithResource(this, android.R.drawable.ic_lock_power_off))
									.setShortLabel(n)
									.setIntent(I)
									.build(), null);
		else sendBroadcast(new Intent("com.android.launcher.action.INSTALL_SHORTCUT")
						   .putExtra(Intent.EXTRA_SHORTCUT_NAME, n)
						   .putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
									 Intent.ShortcutIconResource.fromContext(this, 0x7f020000))
						   .putExtra(Intent.EXTRA_SHORTCUT_INTENT, I));
	}

	@Override
	public void run()
	{
		if(v instanceof Button)
			((Button)v).setText(s2);
		else f();
	}

	void b(int i)
	{
		b=new Button(this);
		b.setId(i);
		b.setText(getResources().getString(0x7f030000+i));
		b.setTextColor(0xffffffff);
		b.setTextSize(z);
		b.setBackgroundDrawable(d(0xffff5556));
		b.setPadding(p,p,p,p);
		b.setLayoutParams(lp);
		b.setOnLongClickListener(this);
		b.setOnTouchListener(this);
		if(i<7)l1.addView(b);
		else l2.addView(b);
	}
	
	Drawable d(int c)
	{
		GradientDrawable d=new GradientDrawable();
		d.setColor(c);
		d.setCornerRadius(w/10);
		d.setStroke(2, 0xffeeeeee);
		return d;
	}

	void s(String s)
	{
		try
		{
			OutputStream o=Runtime.getRuntime().exec("su").getOutputStream();
			o.write(String.format(ss,s,getPackageName()).getBytes());
			o.flush();
		}
		catch (Exception e)
		{}
	}
	
	void f()
	{super.finish();}

	@Override
	public boolean onLongClick(View v)
	{
		s(getResources().getString(0x7f030000+v.getId()),new Intent(Intent.ACTION_MAIN).setClass(this, l.class).putExtra("", v.getId()));
		return false;
	}


	@Override
	public boolean onTouch(View v, MotionEvent e)
	{
		if (e.getAction() == MotionEvent.ACTION_DOWN)
		{
			x1 = e.getX();
			y1=e.getY();
			t=System.currentTimeMillis();
		}
		if (e.getAction() == MotionEvent.ACTION_UP)
		{
			x2 = e.getX();
			if (Math.abs(x1 - x2) > 50)
			{
				as=new AnimationSet(true);
				sa=new ScaleAnimation(1, 0, 1, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				aa=new AlphaAnimation(1, 0.1f);
				sa.setRepeatCount(1);
				sa.setRepeatMode(2);
				aa.setRepeatCount(1);
				aa.setRepeatMode(2);
				as.addAnimation(sa);
				as.addAnimation(aa);
				as.setDuration(500);
				l0.startAnimation(as);
				
				if (l1.isShown())
				{
					l1.setVisibility(View.GONE);
					l2.setVisibility(View.VISIBLE);
				}
				else
				{
					l2.setVisibility(View.GONE);
					l1.setVisibility(View.VISIBLE);
				}
			}
			if (Math.abs(x1-x2)<10&&System.currentTimeMillis()-t<300)
			{
				if (v instanceof Button)
				{
					s(s[v.getId()]);
					((Button)v).setText(s1);
					as = new AnimationSet(true);
					sa = new ScaleAnimation(01, 0.0f, 01, 0.0f, Animation.RELATIVE_TO_SELF, x1 / v.getWidth(), Animation.RELATIVE_TO_SELF, y1 / v.getHeight());
					aa = new AlphaAnimation(01, 0.1f);
					as.addAnimation(sa);
					as.addAnimation(aa);
					as.setDuration(800);
					v.startAnimation(as);
					this.v = v;
					new Handler().postDelayed(this, 1000);
				}
				else finish();
			}
		}
		return false;
	}
	
	@Override
	public void finish()
	{
		aa = new AlphaAnimation(1,0);
		aa.setDuration(500);
		l.startAnimation(aa);
		v=l;
		new Handler().postDelayed(this, 500);
	}
	
	@Override
	protected void onStop()
	{
		f();
		super.onStop();
	}
	
}

