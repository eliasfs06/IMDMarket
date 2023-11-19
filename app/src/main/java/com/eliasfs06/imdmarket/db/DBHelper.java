package com.eliasfs06.imdmarket.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eliasfs06.imdmarket.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper dbHelperInstance;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ProdutosDB";
    private static final String TABLE_PRODUTOS = "produtos";
    private static final String KEY_CODIGO = "codigo";
    private static final String KEY_NOME = "nome";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String KEY_ESTOQUE = "estoque";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (dbHelperInstance == null) {
            dbHelperInstance = new DBHelper(context.getApplicationContext());
        }
        return dbHelperInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUTOS_TABLE = "CREATE TABLE " + TABLE_PRODUTOS + "("
                + KEY_CODIGO + " TEXT PRIMARY KEY," + KEY_NOME + " TEXT,"
                + KEY_DESCRICAO + " TEXT," + KEY_ESTOQUE + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTOS);
        onCreate(db);
    }

    public void addProduto(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CODIGO, produto.getCodigo());
        values.put(KEY_NOME, produto.getNome());
        values.put(KEY_DESCRICAO, produto.getDescricao());
        values.put(KEY_ESTOQUE, produto.getEstoque());

        db.insert(TABLE_PRODUTOS, null, values);
        db.close();
    }

    public Produto getProduto(String codigo) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUTOS, new String[]{KEY_CODIGO, KEY_NOME, KEY_DESCRICAO, KEY_ESTOQUE},
                KEY_CODIGO + "=?", new String[]{codigo}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Produto produto = new Produto(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));

        cursor.close();
        return produto;
    }

    public List<Produto> getAllProdutos() {
        List<Produto> produtosList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUTOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String codigo = cursor.getString(cursor.getColumnIndex(KEY_CODIGO));
                String nome = cursor.getString(cursor.getColumnIndex(KEY_NOME));
                String descricao = cursor.getString(cursor.getColumnIndex(KEY_DESCRICAO));
                int estoque = cursor.getInt(cursor.getColumnIndex(KEY_ESTOQUE));

                Produto produto = new Produto(codigo, nome, descricao, estoque);
                produtosList.add(produto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return produtosList;
    }

}
