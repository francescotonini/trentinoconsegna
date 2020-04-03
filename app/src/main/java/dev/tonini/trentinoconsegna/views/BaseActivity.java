package dev.tonini.trentinoconsegna.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import dev.tonini.trentinoconsegna.Logger;
import dev.tonini.trentinoconsegna.viewmodels.BaseViewModel;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getLayoutId();

    protected abstract BaseViewModel getViewModel();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Why? An Activity may not have a layout. If that is the case, layoutId is zero
        if (getLayoutId() == 0) {
            Logger.w(BaseActivity.class.getSimpleName(), "Layout id is zero");

            return;
        }
    }
}
