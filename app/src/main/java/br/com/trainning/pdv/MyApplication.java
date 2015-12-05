package br.com.trainning.pdv;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;

/**
 * Created by fabiogomes on 11/28/15.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Sprinkles sprinkles = Sprinkles.init(getApplicationContext());

        sprinkles.addMigration(new Migration() {
            @Override
            protected void onPreMigrate() {
                // do nothing
            }

            @Override
            protected void doMigration(SQLiteDatabase db) {
                db.execSQL(
                        "CREATE TABLE Produto (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                "descricao TEXT,"+
                                "unidade TEXT,"+
                                "codigo_barras TEXT,"+
                                "preco REAL,"+
                                "foto TEXT,"+
                                "ativo INTEGER"+
                                ")"
                );

                db.execSQL(
                        "CREATE TABLE Carrinho (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                "id_compra TEXT,"+
                                "encerrada INTEGER,"+
                                "enviada INTEGER"+
                                ")"
                );

                db.execSQL(
                        "CREATE TABLE Item (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                "id_produto INTEGER,"+
                                "id_compra INTEGER,"+
                                "quantidade INTEGER"+
                                ")"
                );



            }

            @Override
            protected void onPostMigrate() {
                // do nothing
            }
        });
    }



}
