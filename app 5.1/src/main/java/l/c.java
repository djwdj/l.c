package l;

import android.app.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.LinearLayout.*;
import java.io.*;

public class c extends Activity 
{
	float x1 = 0,x2 = 0;
	ScrollView sc;
	LinearLayout l,ll,l0,l1,l2;
	LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1);
	float d,z;
	int w,h,p,m;
	Animation a,aa;
	Button b;
	String s1="执行中……",s2="请ROOT后再试试!";
	String[][] s=
	{
		{"极速重启","reboot \n"},
		{"极速关机","reboot -p \n"},
		{"极速卡刷","reboot recovery \n"},
		{"极速线刷","reboot bootloader \n"},
		{"界面重启","busybox pkill com.android.systemui \n"},
		{"核心重启","setprop ctl.restart zygote \n"},
		{"极速锁屏","input keyevent 26 \n"},
		{"系统重启","svc power reboot \n"},
		{"系统关机","svc power shutdown \n"},
		{"安全模式","setprop persist.sys.safemode 1 \n svc power reboot \n"},
		{"恢复模式","svc power reboot recovery \n"},
		{"引导模式","svc power reboot bootloader \n"},
		{"急救模式","setprop persist.sys.safemode 1 \n reboot \n"},
		{"救砖模式","reboot edl \n"}
	};


	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

		//setRequestedOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR);
		
		if(Build.VERSION.SDK_INT==Build.VERSION_CODES.KITKAT)
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setStatusBarColor(0x00000000);
		}

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		d = dm.density;
		if(dm.widthPixels<dm.heightPixels)
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

		a=new TranslateAnimation(0, 0, h, 0);
		a.setDuration(500);
		l.startAnimation(a);

		ll=new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setBackgroundDrawable(r(w/10,0xbbcccccc));
		ll.setLayoutParams(lp);
		l.addView(ll);

		sc=new ScrollView(this);
		sc.setVerticalScrollBarEnabled(false);
		ll.addView(sc);

		l0=new LinearLayout(this);
		l0.setOrientation(LinearLayout.HORIZONTAL);
		sc.addView(l0);

		l1=new LinearLayout(this);
		l1.setLayoutParams(lp);
		l1.setOrientation(LinearLayout.VERTICAL);
		l0.addView(l1);

		l2=new LinearLayout(this);
		l2.setLayoutParams(lp);
		l2.setVisibility(View.GONE);
		l2.setOrientation(LinearLayout.VERTICAL);
		l0.addView(l2);

		for(int i=0;i<s.length;i++)b(i);
		
		o(l);
		addContentView(l, new WindowManager.LayoutParams());

    }

	@Override
	public void finish()
	{
		a = new TranslateAnimation(0, 0, 0, -h);
		a.setDuration(w);
		l.startAnimation(a);
		
		h(l);
	}

	void h(final View v)
	{
		new Handler().postDelayed(new Runnable(){  
				public void run()
				{
					if(v instanceof Button)((Button)v).setText(s1);
					else f();
				}  
			}, w);
	}
	
	void f()
	{
		super.finish();
	}

	void b(int i)
	{
		b=new Button(this);
		b.setId(i);
		b.setText(s[i][0]);
		b.setTextColor(0xffffffff);
		b.setTextSize(z);
		b.setBackgroundDrawable(r(w/16,0xffff5556));
		b.setPadding(p,p,p,p);
		b.setLayoutParams(lp);

		o(b);

		if(i<7)l1.addView(b);
		else l2.addView(b);
		
	}

	void o(final View v)
	{
		v.setOnTouchListener(new View.OnTouchListener(){

				@Override
				public boolean onTouch(View p1, MotionEvent event)
				{
					if (event.getAction() == MotionEvent.ACTION_DOWN)
					{
						x1 = event.getX();
					}
					if (event.getAction() == MotionEvent.ACTION_UP)
					{
						x2 = event.getX();
						if (Math.abs(x1 - x2) > 50)
						{
							a=new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
							a.setDuration(1000);
							aa=new AlphaAnimation(1, 0);
							aa.setDuration(1000);

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
						if (x1 - x2 == 0)
						{
							if(v instanceof Button)
							{
								cmd(s[v.getId()][1]);cmd("am force-stop l.c \n");
								
								((Button)v).setText(s1);

								a = new TranslateAnimation(0, -w, 0, 0);
								a.setDuration(w);
								v.startAnimation(a);
								
								h(v);
							}else finish();
						}
					}
					return true;
				}


			});
	}


	Drawable r(int r,int c)
	{
		GradientDrawable o=new GradientDrawable();
		o.setColor(c);
		o.setCornerRadius(r);
		o.setStroke(2, 0xffeeeeee);
		return o;
	}

	java.lang.Process su;
	void cmd(String cmd)
	{
		//String s="";
		try
		{
			if(su==null)
				su=Runtime.getRuntime().exec("su");
			OutputStream o=su.getOutputStream();
			o.write(cmd.getBytes());
			o.flush();
//			InputStream is=su.getInputStream();
//			byte[] b = new byte[1024];
//			s=new String(b,0,is.read(b)).trim();
		}
		catch (IOException e)
		{}

		//return s;
	}
}

