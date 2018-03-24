package hk.ust.cse.comp107x.cryptospinner;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by Dian on 24/03/2018.
 */;import java.util.Random;

public class SpinnerWheel {
    View view;
    int section;
    int old_degree, degree;

    Random r = new Random();

    public void SpinnerWheel(View view){
        this.view = view;
        section = 0;
    }

    public void spinWheel(){
        old_degree = degree%360;
        degree = r.nextInt(3600) + 720;
        RotateAnimation rotate = new RotateAnimation(old_degree, degree, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        
    }

    public int getSection(){
        return section;
    }
}
