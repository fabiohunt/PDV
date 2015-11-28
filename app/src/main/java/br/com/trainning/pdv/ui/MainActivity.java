package br.com.trainning.pdv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.trainning.pdv.R;
import butterknife.Bind;

public class MainActivity extends BaseActivity {

    //Com o uso do ButterKnife a antiga linha que era:
    //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    //se torna a de baixo com o ButterKnife
    @Bind(R.id.fab)
    FloatingActionButton fab;

    //Declarando intent para usar no IF do onOptionsItemSelected(MenuItem item)
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Removido pois uso agora o ButterKnife = FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_incluir) {
            //Intent para acessar a proxima tela (activity)
            intent = new Intent(this, IncluirNovoActivity.class);

        }

        if (id == R.id.action_editar) {
            intent = new Intent(this, EditarActivity.class);
           // return true;
        }

        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}
