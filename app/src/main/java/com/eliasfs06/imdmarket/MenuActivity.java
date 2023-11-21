package com.eliasfs06.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnCadastraProduto = findViewById(R.id.cadastrarProdButton);
        Button btnListarProdutos = findViewById(R.id.listarProdButton);
        Button btnAlterarProdutos = findViewById(R.id.alterarProdButton);

        btnCadastraProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaCadastrarActivity();
            }
        });

        btnListarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaListarActivity();
            }
        });

        btnAlterarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaAlterarActivity();
            }
        });
    }

    private void irParaCadastrarActivity() {
        Intent intent = new Intent(MenuActivity.this, CadastroProdutoActivity.class);
        startActivity(intent);
        finish();
    }

    private void irParaListarActivity() {
        Intent intent = new Intent(MenuActivity.this, ListaProdutosActivity.class);
        startActivity(intent);
        finish();
    }

    private void irParaAlterarActivity() {
        Intent intent = new Intent(MenuActivity.this, EditarProdutoActivity.class);
        startActivity(intent);
        finish();
    }
}