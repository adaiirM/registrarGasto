package com.example.registrargasto;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.example.registrargasto.adapter.AdapterFragment;
import com.example.registrargasto.util.ZoomOutPageTransformer;
import com.example.registrargasto.view.fragment.AdeudoFragment;
import com.example.registrargasto.view.fragment.GastoFragment;
import com.example.registrargasto.view.fragment.InicioFragment;
import com.example.registrargasto.view.fragment.PresupuestoFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private ViewPager2 mViewPager2;
    private FragmentStateAdapter mPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate (Bundle saveIndtance) {

        super.onCreate(saveIndtance);
        setContentView(R.layout.activity_main);

        mViewPager2=findViewById(R.id.pager);
        mTabLayout =  findViewById(R.id.tab_layout);


        mPagerAdapter = new AdapterFragment(getSupportFragmentManager(),getLifecycle(),loadFragment());
        mViewPager2.setAdapter(mPagerAdapter);
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.teal_700));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTabLayout,mViewPager2,new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch(position){
                    case 0:
                        tab.setText("Inicio");
                        break;
                    case 1:
                        tab.setText("Gastos");
                        break;
                    case 2:
                        tab.setText("Pendientes");
                        break;
                    case 3:
                        tab.setText("Presupuesto");
                        break;
                    default:
                        break;
                }
            }
        } );
        tabLayoutMediator.attach();
        mViewPager2.setPageTransformer(new ZoomOutPageTransformer());
    }
    @Override
    public void onBackPressed(){
        if(mViewPager2.getCurrentItem()==0){
            super.onBackPressed();
        }else{
            mViewPager2.setCurrentItem(mViewPager2.getCurrentItem()-1);
        }
    }

    private ArrayList<Fragment>  loadFragment(){
        ArrayList<Fragment> mListadapter = new ArrayList<>();
        mListadapter.add(new InicioFragment());
        mListadapter.add(new GastoFragment());
        mListadapter.add(new AdeudoFragment());
        mListadapter.add(new PresupuestoFragment());

        return mListadapter;
    }

}