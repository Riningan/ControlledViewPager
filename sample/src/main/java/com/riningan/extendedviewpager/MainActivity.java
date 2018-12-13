package com.riningan.extendedviewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.riningan.widget.ExtendedViewPager;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ExtendedViewPager cvp = findViewById(R.id.cvp);
        cvp.setAdapter(new Adapter());
        cvp.setOffscreenPageLimit(3);

        findViewById(R.id.btnLockLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cvp.setAllowedSwipeDirection(ExtendedViewPager.SwipeDirection.RIGHT);
            }
        });
        findViewById(R.id.btnLockAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cvp.setAllowedSwipeDirection(ExtendedViewPager.SwipeDirection.NONE);
            }
        });
        findViewById(R.id.btnUnlock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cvp.setAllowedSwipeDirection(ExtendedViewPager.SwipeDirection.All);
            }
        });
        findViewById(R.id.btnLockRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cvp.setAllowedSwipeDirection(ExtendedViewPager.SwipeDirection.LEFT);
            }
        });
    }


    class Adapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 4;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            switch (position) {
                case 0:
                    return findViewById(R.id.view0);
                case 1:
                    return findViewById(R.id.view1);
                case 2:
                    return findViewById(R.id.view2);
                default:
                    return findViewById(R.id.view3);
            }
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }
}
