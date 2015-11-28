package br.com.trainning.pdv.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.trainning.pdv.R;
import br.com.trainning.pdv.domain.model.Produto;
import butterknife.Bind;
import se.emilsjolander.sprinkles.CursorList;
import se.emilsjolander.sprinkles.Query;

public class EditarActivity extends BaseActivity {

    @Bind(R.id.editText_descricao)
    EditText descricao;

    @Bind(R.id.editText_unidade) EditText unidade;

    @Bind(R.id.editText_codigo_barras) EditText codigoBarras;

    @Bind(R.id.editText_preco) EditText preco;

    @Bind(R.id.imageButton_camera)
    ImageButton botaoCamera;

    @Bind(R.id.imageButton_galeria) ImageButton botaoGaleria;

    @Bind(R.id.imageView_foto)
    ImageView foto;

    @Bind(R.id.fab) FloatingActionButton fab;

    @Bind(R.id.spinner)
    Spinner spinner;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Nao precisa pelo ButterKnife - spinner = (Spinner) findViewById(R.id.spinner);
        List<String> barcodeList = new ArrayList<>();
        List<Produto> produtoList;


        CursorList cursor = Query.many(Produto.class, "select * from produto where ativo = 1").get();
        produtoList = cursor.asList();

        for(Produto produto: produtoList){
            barcodeList.add(produto.getCodigoBarras());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,barcodeList);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        // Não precisa mais - FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */

                Produto produto = new Produto();

                produto.setId(0L);
                produto.setDescricao(descricao.getText().toString());
                produto.setUnidade(unidade.getText().toString());
                produto.setCodigoBarras(codigoBarras.getText().toString());
                produto.setPreco(Double.parseDouble(preco.getText().toString()));
                produto.setFoto("Teste");
                produto.setAtivo(1);

                // Log.d("INCLUIRNOVO", produto.toString());

                boolean sucesso = produto.save();



                // com o .many informo query - CursorList cursor = Query.many(Produto.class, "select * from produto").get();

                //Para todos uso o .all
                CursorList cursor = Query.all(Produto.class).get();

                List<Produto> lista = cursor.asList();

                for (Produto p3: lista){
                    Log.d("INCLUIRNOVO", "" + p3.toString());
                }



                if (sucesso==true) {
                    Snackbar.make(view, "Salvo com sucesso", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }

                SystemClock.sleep(5000);
                finish();

            }
        });
    }

}
