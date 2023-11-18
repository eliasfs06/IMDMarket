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
        btnCadastraProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaCadastrarActivity();
            }
        });
    }

    private void irParaCadastrarActivity() {
        Intent intent = new Intent(MenuActivity.this, CadastroProdutoActivity.class);
        startActivity(intent);
        finish();
    }
}