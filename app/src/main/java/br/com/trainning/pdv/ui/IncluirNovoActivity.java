package br.com.trainning.pdv.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import br.com.trainning.pdv.R;
import br.com.trainning.pdv.domain.image.Base64Util;
import br.com.trainning.pdv.domain.image.ImageInputHelper;
import br.com.trainning.pdv.domain.model.Produto;
import butterknife.Bind;
import se.emilsjolander.sprinkles.CursorList;
import se.emilsjolander.sprinkles.Query;

public class IncluirNovoActivity extends BaseActivity implements ImageInputHelper.ImageActionListener{

    @Bind(R.id.editText_descricao) EditText descricao;

    @Bind(R.id.editText_unidade) EditText unidade;

    @Bind(R.id.editText_codigo_barras) EditText codigoBarras;

    @Bind(R.id.editText_preco) EditText preco;

    @Bind(R.id.imageButton_camera)
    ImageButton botaoCamera;

    @Bind(R.id.imageButton_galeria)
    ImageButton botaoGaleria;

    @Bind(R.id.imageView_foto) ImageView foto;

    @Bind(R.id.fab) FloatingActionButton fab;

//Codigo da classe do gist
    private ImageInputHelper imageInputHelper;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir_novo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Adicionado do gist para obter fotos
        imageInputHelper = new ImageInputHelper(this);
        imageInputHelper.setImageActionListener(this);

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

                Bitmap imagem = ((BitmapDrawable)foto.getDrawable()).getBitmap();

                produto.setFoto(Base64Util.encodeTobase64(imagem));
                produto.setAtivo(1);

               // Log.d("INCLUIRNOVO", produto.toString());

                boolean sucesso = produto.save();



                // com o .many informo query - CursorList cursor = Query.many(Produto.class, "select * from produto").get();

                //Para todos uso o .all
                CursorList cursor = Query.all(Produto.class).get();

                List<Produto> lista = cursor.asList();

                for (Produto p3: lista){
                    Log.d("INCLUIRNOVO","" + p3.toString());
                }



                if (sucesso==true) {
                    Snackbar.make(view, "Salvo com sucesso", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    
                }

                SystemClock.sleep(5000);
                finish();

            }
        });

        //Inserir os listeners para os cliques dos botoes (camera e galeria)

        botaoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mandar uma intent para a camera, apresentando um CROP para selecionar qual parte da imagem que iremos usar
                // para isso usar 2 classes

                imageInputHelper.takePhotoWithCamera();

            }
        });


        botaoGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageInputHelper.selectImageFromGallery();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageInputHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onImageSelectedFromGallery(Uri uri, File imageFile) {
        // cropping the selected image. crop intent will have aspect ratio 16/9 and result image
        // will have size 800x450
        //imageInputHelper.requestCropImage(uri, 800, 450, 16, 9);

        //Modificado para 100x100
        imageInputHelper.requestCropImage(uri, 100, 100, 1, 1);
    }

    @Override
    public void onImageTakenFromCamera(Uri uri, File imageFile) {
        // cropping the taken photo. crop intent will have aspect ratio 16/9 and result image
        // will have size 800x450
        //imageInputHelper.requestCropImage(uri, 800, 450, 16, 9);

        //Modificado para 100x100
        imageInputHelper.requestCropImage(uri, 100, 100, 1, 1);
    }

    @Override
    public void onImageCropped(Uri uri, File imageFile) {
        try {
            // getting bitmap from uri
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);


            //Comentado pois trata-se do exemplo da classe original do gist
            // showing bitmap in image view
            //((ImageView) findViewById(R.id.image)).setImageBitmap(bitmap);

           //Adequado para o nosso projeto
           // Quando tira-se uma foto usando a intent, ela fica no media storage do aparelho,
           // a foto que está no cadastro também vai para a galeria. Se não quiser que vá pra galeria, deve-se usar outro recurso
           // aqui estamos usando nativo.
            foto.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
