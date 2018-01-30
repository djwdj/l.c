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

public class c extends Activity implements OnTouchListener,Runnable
{
	float x1,x2;
	ScrollView sc;
	LinearLayout l,l0,ll,l1,l2;
	LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1);
	float d,z;
	int w,h,p,m;
	Animation a,aa;
	Button b;
	View v;
	int ic=0x7f020000;
	String s1="尝试执行",s2="请ROOT后再试!";
	String ss="%s\nam force-stop %s\n";
	String[][] s=
	{
		{"极速重启","reboot"},
		{"极速关机","reboot -p"},
		{"极速卡刷","reboot recovery"},
		{"极速线刷","reboot bootloader"},
		{"界面重启","busybox pkill com.android.systemui"},
		{"核心重启","setprop ctl.restart zygote"},
		{"极速锁屏","input keyevent 26"},
		{"系统重启","svc power reboot"},
		{"系统关机","svc power shutdown"},
		{"恢复模式","svc power reboot recovery"},
		{"引导模式","svc power reboot bootloader"},
		{"安全模式","setprop persist.sys.safemode 1 \n svc power reboot"},
		{"急救模式","setprop persist.sys.safemode 1 \n reboot"},
		{"救砖模式","reboot edl"}
	};

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

		int e=getIntent().getIntExtra("", -1);
		if (e != -1)
		{
			s(s[e][1]);
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
		
		a=new TranslateAnimation(0, 0, h, 0);
		a.setDuration(500);
		l.startAnimation(a);

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
			.setShortLabel(s[6][0])
			.setIcon(Icon.createWithResource(this, ic))
			.setIntent(new Intent(Intent.ACTION_MAIN).setClass(this, c.class).putExtra("", 6))
			.build(),
			s3 = new ShortcutInfo.Builder(this, "id3")
			.setShortLabel(s[2][0])
			.setIcon(Icon.createWithResource(this, ic))
			.setIntent(new Intent(Intent.ACTION_MAIN).setClass(this, c.class).putExtra("", 2))
			.build(),
			s2 = new ShortcutInfo.Builder(this, "id2")
			.setShortLabel(s[1][0])
			.setIcon(Icon.createWithResource(this, ic))
			.setIntent(new Intent(Intent.ACTION_MAIN).setClass(this, c.class).putExtra("", 1))
			.build(),
			s1 = new ShortcutInfo.Builder(this, "id1")
			.setShortLabel(s[0][0])
			.setIcon(Icon.createWithResource(this, ic))
			.setIntent(new Intent(Intent.ACTION_MAIN).setClass(this, c.class).putExtra("", 0))
			.build();
		getSystemService(ShortcutManager.class).setDynamicShortcuts(Arrays.asList(s4,s3,s2,s1));
		
	}
	
	void s(String n,Intent I)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
			getSystemService(ShortcutManager.class)
				.requestPinShortcut(new ShortcutInfo.Builder(this, n)
									.setIcon(Icon.createWithResource(this, ic))
									.setShortLabel(n)
									.setIntent(I)
									.build(), null);
		else sendBroadcast(new Intent("com.android.launcher.action.INSTALL_SHORTCUT")
						   .putExtra(Intent.EXTRA_SHORTCUT_NAME, n)
						   .putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
									 Intent.ShortcutIconResource.fromContext(this, ic))
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
		b.setText(s[i][0]);
		b.setTextColor(0xffffffff);
		b.setTextSize(z);
		b.setBackgroundDrawable(d(0xffff5556));
		b.setPadding(p,p,p,p);
		b.setLayoutParams(lp);
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
	
	void f(){super.finish();}

	@Override
	public boolean onTouch(View v, MotionEvent e)
	{
		if (e.getAction() == MotionEvent.ACTION_DOWN)x1 = e.getX();
		if (e.getAction() == MotionEvent.ACTION_UP)
		{
			x2 = e.getX();
			if (Math.abs(x1 - x2) > 50)
			{
				a=new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				a.setDuration(1000);
				aa=new AlphaAnimation(1, 0);
				aa.setDuration(1000);

				if(x1-x2>w/3&&v instanceof Button)
					s(s[v.getId()][0],new Intent(Intent.ACTION_MAIN).setClass(this, c.class).putExtra("", v.getId()));
				else
				if (l1.isShown())
				{
					l1.setVisibility(View.GONE);
					l2.setVisibility(View.VISIBLE);
					l1.startAnimation(aa);
					l2.startAnimation(a);
				}
				else
				{
					l2.setVisibility(View.GONE);
					l1.setVisibility(View.VISIBLE);
					l2.startAnimation(aa);
					l1.startAnimation(a);
				}
			}
			if (Math.abs(x1-x2)<10)
			{
				if(v instanceof Button)
				{
					s(s[v.getId()][1]);
					((Button)v).setText(s1);
					aa=new AlphaAnimation(1, 0);
					aa.setDuration(1000);
					v.startAnimation(aa);
					this.v=v;
					new Handler().postDelayed(this, w-100);
				}else finish();
			}
		}
		return true;
	}
	
	@Override
	public void finish()
	{
		a = new TranslateAnimation(0, 0, 0, -h);
		a.setDuration(w);
		l.startAnimation(a);
		v=l;
		new Handler().postDelayed(this, w-100);
	}
	
	@Override
	protected void onStop()
	{
		f();
		super.onStop();
	}
	
}

