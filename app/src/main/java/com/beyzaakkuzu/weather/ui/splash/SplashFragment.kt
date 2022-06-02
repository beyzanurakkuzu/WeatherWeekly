package com.beyzaakkuzu.weather.ui.splash

import android.graphics.Color
import androidx.navigation.fragment.findNavController
import com.beyzaakkuzu.weather.R
import com.beyzaakkuzu.weather.core.BaseFragment
import com.beyzaakkuzu.weather.databinding.FragmentSplashBinding
import com.beyzaakkuzu.weather.other.Constants
import com.beyzaakkuzu.weather.utils.extensions.hide
import com.beyzaakkuzu.weather.utils.extensions.show
import com.mikhaellopez.rxanimation.*
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel,FragmentSplashBinding>(
    R.layout.fragment_splash,SplashViewModel::class.java
) {
    var disposable = CompositeDisposable()

    override fun init(){
       super.init()

        if (binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON,
           "").isNullOrEmpty()){

            binding.button.show()
            binding.viewModel?.navigate =false
        }else{
            binding.button.hide()
            binding.viewModel?.navigate =true
        }
        binding.viewModel?.navigate?.let { startSplashAnimation(it) }
        binding.button.setOnClickListener{
            binding.viewModel?.navigate?.let {
                item ->endSplashAnimation(item)
            }
        }
        binding.rootView.setOnClickListener {
            binding.viewModel?.navigate?.let { it1-> endSplashAnimation(it1) }
        }
    }



    private fun startSplashAnimation(navigate: Boolean) {
        disposable.add(
            RxAnimation.sequentially(
                RxAnimation.together(
                    binding.imageViewBottomDrawable.translationY(500f),
                    binding.imageViewEllipse.fadeOut(0L),
                    binding.imageViewBottomDrawable.fadeOut(0L),
                    binding.imageViewBigCloud.translationX(-500F, 0L),
                    binding.imageViewSmallCloud.translationX(500f, 0L),
                    binding.imageViewBottomDrawableShadow.translationY(500f),
                    binding.imageViewMainCloud.fadeOut(0L),
                    binding.button.fadeOut(0L),
                    binding.imageViewBottomDrawableShadow.fadeOut(0L)
                ),

                RxAnimation.together(
                    binding.imageViewBottomDrawable.fadeIn(1000L),
                    binding.imageViewBottomDrawable.translationY(-1f),
                    binding.imageViewBottomDrawableShadow.fadeIn(1250L),
                    binding.imageViewBottomDrawableShadow.translationY(-1f)
                ),

                RxAnimation.together(
                    binding.imageViewEllipse.fadeIn(1000L),
                    binding.imageViewEllipse.translationY(-50F, 1000L)
                ),

                RxAnimation.together(
                    binding.imageViewBigCloud.translationX(-15f, 1000L),
                    binding.imageViewSmallCloud.translationX(25f, 1000L)
                ),

                binding.imageViewMainCloud.fadeIn(500L),
                binding.button.fadeIn(1000L)
            ).doOnTerminate {
                findNavController().graph.setStartDestination(R.id.dashboardFragment)
                if (navigate) {
                    endSplashAnimation(navigate)
                }
            }
                .subscribe()
        )
    }

    private fun endSplashAnimation(navigateToDashboard: Boolean) {
        disposable.add(
            RxAnimation.sequentially(
                RxAnimation.together(
                    binding.imageViewBottomDrawable.fadeOut(300L),
                    binding.imageViewBottomDrawable.translationY(100f),
                    binding.imageViewBottomDrawableShadow.fadeOut(300L),
                    binding.imageViewBottomDrawableShadow.translationY(100f)
                ),

                RxAnimation.together(
                    binding.imageViewEllipse.fadeOut(300L),
                    binding.imageViewEllipse.translationY(500F, 300L)
                ),

                RxAnimation.together(
                    binding.imageViewBigCloud.translationX(500f, 300L),
                    binding.imageViewSmallCloud.translationX(-500f, 300L)
                ),

                binding.imageViewMainCloud.fadeOut(300L),
                binding.button.fadeOut(300L),
                binding.rootView.backgroundColor(
                    Color.parseColor("#5D50FE"),
                    Color.parseColor("#FFFFFF"),
                    duration = 750L
                )
            )
                .doOnTerminate {
                    findNavController().graph.setStartDestination(R.id.dashboardFragment) // Little bit tricky solution :)
                    if (navigateToDashboard) {
                        navigate(R.id.action_splashFragment_to_dashboardFragment)
                    } else {
                        navigate(R.id.action_splashFragment_to_searchFragment)
                    }
                }
                .subscribe()

        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}


