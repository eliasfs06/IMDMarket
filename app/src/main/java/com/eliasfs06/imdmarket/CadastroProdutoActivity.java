package com.eliasfs06.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eliasfs06.imdmarket.db.DBHelper;
import com.eliasfs06.imdmarket.model.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {

    private EditText codigoText;
    private EditText nomeText;
    private EditText descricaoText;
    private EditText estoqueText;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        dbHelper = DBHelper.getInstance(this);

        codigoText = findViewById(R.id.codigoText);
        nomeText = findViewById(R.id.nomeText);
        descricaoText = findViewById(R.id.descricaoText);
        estoqueText = findViewById(R.id.estoqueText);

        Button btnSalvar = findViewById(R.id.salvarBtn);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = codigoText.getText().toString();
                String nome = nomeText.getText().toString();
                String descricao = descricaoText.getText().toString();
                int estoque = Integer.parseInt(estoqueText.getText().toString());

                salvarProduto(codigo, nome, descricao, estoque);
            }
        });
    }

    private void salvarProduto(String codigo, String nome, String descricao, int estoque) {
        dbHelper.addProduto(new Produto(codigo, nome, descricao, estoque));
    }

}