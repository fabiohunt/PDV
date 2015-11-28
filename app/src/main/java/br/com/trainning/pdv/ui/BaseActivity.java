package br.com.trainning.pdv.ui;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by fabiogomes on 11/28/15.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        ButterKnife.bind(this);
    }

}










