package com.uit.unit4_animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton btnFadeInXml;
    private AppCompatButton btnFadeInCode;
    private AppCompatButton btnFadeOutXml;
    private AppCompatButton btnFadeOutCode;
    private AppCompatButton btnBlinkXml;
    private AppCompatButton btnBlinkCode;
    private AppCompatButton btnZoomInXml;
//    private AppCompatButton btnZoomInCode;
    private AppCompatButton btnZoomOutXml;
//    private AppCompatButton btnZoomOutCode;
    private AppCompatButton btnRotateXml;
//    private AppCompatButton btnRotateCode;
    private AppCompatButton btnMoveXml;
//    private AppCompatButton btnMoveCode;
    private AppCompatButton btnSlideUpXml;
//    private AppCompatButton btnSlideUpCode;
    private AppCompatButton btnBounceXml;
//    private AppCompatButton btnBounceCode;
    private AppCompatButton btnCombineXml;
//    private AppCompatButton btnCombineCode;
    private AppCompatImageView ivUitLogo;
    private Animation.AnimationListener animationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsByIds();
        initVariables();

        handleClickAnimationXml(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml(btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml(btnMoveXml, R.anim.anim_move);
        handleClickAnimationXml(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml(btnCombineXml, R.anim.anim_combine);

        handleClickAnimationCode(btnFadeInCode, initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, initBlinkAnimation());

        ivUitLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSecondActivity();
            }
        });
    }

    private void startSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    private void handleClickAnimationXml(AppCompatButton btn, final int anim) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(initAnimation(anim));
            }
        });
    }

    private Animation initAnimation(int anim){
        Animation mAnimation;
        mAnimation = AnimationUtils.loadAnimation(this, anim);
        mAnimation.setAnimationListener(animationListener);
        return mAnimation;
    }

    private void handleClickAnimationCode(AppCompatButton btn, final Animation animation) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }

    private Animation initFadeInAnimation(){
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initFadeOutAnimation(){
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setFillAfter(true);
        animation.setDuration(500);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initBlinkAnimation(){
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(500);
        animation.setRepeatMode(AlphaAnimation.REVERSE);
        animation.setRepeatCount(3);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private void initVariables() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }


    private void findViewsByIds() {
        ivUitLogo = findViewById(R.id.iv_uit_logo);
        btnFadeInXml = findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = findViewById(R.id.btn_fade_in_code);
        btnFadeOutXml = findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = findViewById(R.id.btn_fade_out_code);
        btnBlinkXml = findViewById(R.id.btn_blink_xml);
        btnBlinkCode = findViewById(R.id.btn_blink_code);
        btnZoomInXml = findViewById(R.id.btn_zoom_in_xml);
//        btnZoomInCode = findViewById(R.id.btn_zoom_in_code);
        btnZoomOutXml = findViewById(R.id.btn_zoom_out_xml);
//        btnZoomOutCode = findViewById(R.id.btn_zoom_out_code);
        btnRotateXml = findViewById(R.id.btn_rotate_xml);
//        btnRotateCode = findViewById(R.id.btn_rotate_code);
        btnMoveXml = findViewById(R.id.btn_move_xml);
//        btnMoveCode = findViewById(R.id.btn_move_code);
        btnSlideUpXml = findViewById(R.id.btn_slide_up_xml);
//        btnSlideUpCode = findViewById(R.id.btn_slide_up_code);
        btnBounceXml = findViewById(R.id.btn_bounce_xml);
//        btnBounceCode = findViewById(R.id.btn_bounce_code);
        btnCombineXml = findViewById(R.id.btn_combine_xml);
//        btnCombineCode = findViewById(R.id.btn_combine_code);
    }
}
