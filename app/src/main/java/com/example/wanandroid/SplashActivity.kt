package com.example.wanandroid

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.wanandroid.databinding.ActivityMainBinding
import com.example.wanandroid.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        window.statusBarColor = Color.parseColor("#03A9F4")
        val animator = ValueAnimator.ofFloat(0f,1f)
        animator.duration =1600
        animator.addUpdateListener {
            binding.animationView.progress = it.animatedFraction
        }
        animator.start()

        binding.animationView.addAnimatorListener(object :Animator.AnimatorListener{
            override fun onAnimationCancel(animation: Animator?) {
                val intent = Intent(this@SplashActivity,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationEnd(animation: Animator?) {
                val intent = Intent(this@SplashActivity,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {

            }
        })
    }
}
