package ushal.naidoo.destino.creaturekeeper;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CreatureHome extends Activity {

  private ImageView CreatureView;
  private RelativeLayout linLay;
  private Creatures CreatureType = Creatures.Furball;
  private Handler mHandler = new Handler();
  private boolean startup = false;
  private TimerTask timerStep;
  private Timer timer;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.creaturehome);
    this.linLay = (RelativeLayout) findViewById(R.id.RelativeLayout1);
    this.CreatureView = (ImageView) this.findViewById(R.id.creatureview);

    linLay.setBackgroundResource(R.drawable.background_snow);
    this.CreatureView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(CreatureHome.this, R.anim.bounce);
        CreatureView.startAnimation(hyperspaceJumpAnimation);
      }

    });


    startTimerAnimation();
    timer = new Timer();
    timer.schedule(timerStep, 0, 10000);
  }

  private void startTimerAnimation() {
    timerStep = new TimerTask() {
      @Override
      public void run() {
        mHandler.post(new Runnable() {
          @Override
          public void run() {
            if (!startup) {
              switch (CreatureType) {
                case Furball:
                  CreatureView.setImageResource(R.drawable.furball_lookingaround_gif);
                  break;
              }
              startup = true;
            } else {
                Random RandomPercentLookingAround = new Random();
                int checkLookingAround = RandomPercentLookingAround.nextInt(100) + 1;
                if (checkLookingAround < 30) {
                  switch (CreatureType) {
                    case Furball:
                      CreatureView.setImageResource(R.drawable.furball_lookingaround_gif);
                      break;
                  }
                } else if (checkLookingAround < 50) {
                  switch (CreatureType) {
                    case Furball:
                      CreatureView.setImageResource(R.drawable.furball_lookingaround_1_gif);
                      break;
                  }
                }else if (checkLookingAround < 70) {
                  switch (CreatureType) {
                    case Furball:
                      CreatureView.setImageResource(R.drawable.furball_thoughts_eat_gif);
                      break;
                  }
                }else if (checkLookingAround < 100) {
                  switch (CreatureType) {
                    case Furball:
                      CreatureView.setImageResource(R.drawable.furball_thoughts_sleep_gif);
                      break;
                  }
                }
              }
              final AnimationDrawable frameAnimation = (AnimationDrawable) CreatureView.getDrawable();
              if (frameAnimation != null) {
                frameAnimation.stop();
                frameAnimation.start();
              }
          }
        });
      }
    };
  }

  private enum Creatures {
    Cupid, Furball, Duck, Panda, Tank, Sep;
  }

}
