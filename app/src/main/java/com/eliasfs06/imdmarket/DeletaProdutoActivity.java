package com.eliasfs06.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eliasfs06.imdmarket.R;
import com.eliasfs06.imdmarket.db.DBHelper;

public class DeletaProdutoActivity extends AppCompatActivity {

    private EditText codigoText;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleta_produto);

        dbHelper = DBHelper.getInstance(this);
        codigoText = findViewById(R.id.codigoText);

        Button btnDeletar = findViewById(R.id.deletarBtn);
        Button btnLimpar = findViewById(R.id.limparBtn);
        Button voltarBtn = findViewById(R.id.voltarBtnCadastro);
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarProduto();
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparInput();
            }
        });

        voltarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaMenuActivity();
            }
        });
    }

    private void deletarProduto(){
        String codigo = codigoText.getText().toString();

        if(codigo.isEmpty()){
            showToast("Digite o código do produto");
        } else {
            if(dbHelper.getProduto(codigo) == null){
                showToast("Não há nenhum produto cadastrado com esse código.");
            } else {
                dbHelper.deleteProduto(Integer.parseInt(codigo));
                showToast("Produto deletado com sucesso.");
            }
        }

    }

    private void limparInput(){
        codigoText.setText("");
    }

    private void irParaMenuActivity() {
        Intent intent = new Intent(DeletaProdutoActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void showToast(String mensagem){
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
    }

}