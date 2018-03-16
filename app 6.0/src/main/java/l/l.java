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
import android.content.res.*;
import android.net.*;

public class l extends Activity implements OnTouchListener,Runnable,OnLongClickListener,OnFocusChangeListener
{
	float x1,x2,y1;
	ScrollView sc;
	LinearLayout l,l0,ll,l1,l2,l3;
	LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1);
	float d,z;
	int w,h,p,m;
	Animation sa,aa;
	AnimationSet as;
	Button b;
	View v;
	OutputStream o;
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
		l.setOnLongClickListener(this);
		l.setOnTouchListener(this);
		l.setClickable(true);
		l.setFocusable(false);
		
		a();

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

		l3=new LinearLayout(this);
		l3.setLayoutParams(lp);
		l3.setVisibility(View.GONE);
		l3.setOrientation(LinearLayout.VERTICAL);
		ll.addView(l3);
		
		b(21,"☆更新☆",l3);
		b(22,"☆交流☆",l3);
		b(23,"☆捐赠☆",l3);
		
		for(int i=0;i<s.length;i++)b(i);
		
		if(Build.VERSION.SDK_INT>=14)
		l.setFitsSystemWindows(true);
		addContentView(l, new LayoutParams());
		
		if (Build.VERSION.SDK_INT >= 25)i();
		
        new Handler().postDelayed(this, 500);
    }

	void a()
	{
		aa = new AlphaAnimation(0.1f, 1);
		aa.setDuration(500);
		l.startAnimation(aa);
	}
	
	void i()
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
		if (v == null)s();
		else
		if (v instanceof Button)
			((Button)v).setText(s2);
		else f();
	}

	void t(String s)
	{
		Toast.makeText(this,s,50).show();
	}
	void b(int i)
	{
		b(i,getResources().getString(0x7f030000+i),i<7?l1:l2);
	}
	void b(int i,String s,LinearLayout l)
	{
		b=new Button(this);
		b.setId(i);
		b.setText(s);
		b.setTextColor(0xffffffff);
		b.setTextSize(z);
		b.setBackgroundDrawable(d(0xffff5556));
		b.setPadding(p,p,p,p);
		b.setLayoutParams(lp);
		b.setFocusable(true);
		b.setOnFocusChangeListener(this);
		b.setOnLongClickListener(this);
		b.setOnTouchListener(this);
		l.addView(b);
	}
	Drawable d(int c)
	{
		GradientDrawable d=new GradientDrawable();
		d.setColor(c);
		d.setCornerRadius(w/10);
		d.setStroke(2, 0xffeeeeee);
		return d;
	}
	
	void s()
	{
		try
		{
			o=Runtime.getRuntime().exec("su").getOutputStream();
		}
		catch (IOException e)
		{}
	}
	void s(String s)
	{
		try
		{
			if(o==null)s();
			o.write(String.format(ss,s,getPackageName()).getBytes());
			o.flush();
		}
		catch (Exception e)
		{}
	}
	void cool(String s)
	{
		Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(String.format("https://www.coolapk.com/apk/%s",s)));
		try
		{
			startActivity(i.setPackage("com.coolapk.market"));
		}
		catch (Exception e)
		{
			startActivity(i.setPackage(null));
		}
	}
	void f()
	{super.finish();}

	@Override
	public boolean onLongClick(View v)
	{
		if(v instanceof Button&&v.getId()<s.length)
		s(getResources().getString(0x7f030000+v.getId()),new Intent(Intent.ACTION_MAIN).setClass(this, l.class).putExtra("", v.getId()));
		else
		onCreateOptionsMenu(null);
		return false;
	}

	@Override
	public void onFocusChange(View v, boolean b)
	{
		if(b)v.setBackgroundDrawable(d(0xff555555));
		else v.setBackgroundDrawable(d(0xffff5556));
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

				if (l3.isShown())
					l3.setVisibility(View.GONE);
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
				if(v.getId()>20)switch(v.getId()%20)
				{
					case 1:
						cool(getPackageName());
						
						break;
					case 2:
						startActivity(new Intent(null,Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3DKY9473G7xhujBXgcUAjgizWzIS-GqFz2")));
						t("群昵称请加上[砖家]");
						break;
					case 3:
						try
						{
							String s;
							//s="alipays://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https://qr.alipay.com/tsx03791nki4qabwu92vi97";
							s="YWxpcGF5czovL3BsYXRmb3JtYXBpL3N0YXJ0YXBwP3NhSWQ9MTAwMDAwMDcmY2xpZW50VmVyc2lvbj0zLjcuMC4wNzE4JnFyY29kZT1odHRwczovL3FyLmFsaXBheS5jb20vdHN4MDM3OTFua2k0cWFid3U5MnZpOTc";
							s=new String(android.util.Base64.decode(s,android.util.Base64.DEFAULT));
							startActivity(new Intent(null,Uri.parse(s)));
							t("捐赠请留言，并加上重启砖家！");
						}
						catch(Exception ex)
						{
							t("打开支付宝失败！");
						}
						break;
				}
				else
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
	public boolean onCreateOptionsMenu(Menu menu)
	{
		if (l1.isShown())
			l1.setVisibility(View.GONE);
		else l2.setVisibility(View.GONE);
		l3.setVisibility(View.VISIBLE);
		a();
		return false;
	}
	
	@Override
	public void finish()
	{
		if (l3.isShown())
		{
			l3.setVisibility(View.GONE);
			l1.setVisibility(View.VISIBLE);
			a();
		}
		else
		{
			aa = new AlphaAnimation(1,0);
			aa.setDuration(500);
			l.startAnimation(aa);
			v=l;
			new Handler().postDelayed(this, 500);
			
		}
	}

	@Override
	protected void onRestart()
	{
		l.setBackgroundDrawable(getWallpaper());
		super.onRestart();
	}
	
	
	
}

