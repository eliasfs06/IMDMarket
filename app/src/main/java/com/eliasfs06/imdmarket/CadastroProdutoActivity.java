package com.eliasfs06.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Button btnLimpar = findViewById(R.id.limparBtn);
        Button voltarBtn = findViewById(R.id.voltarBtnCadastro);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarProduto();
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

    private void salvarProduto(String codigo, String nome, String descricao, int estoque) {
        dbHelper.addProduto(new Produto(codigo, nome, descricao, estoque));
        showToast("Produto salvo com sucesso!");
        limparInput();
    }

    private void limparInput(){
        codigoText.setText("");
        nomeText.setText("");
        descricaoText.setText("");
        estoqueText.setText("");
    }

    public void showToast(String mensagem){
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
    }

    public void validarProduto(){
        String codigo = codigoText.getText().toString();
        String nome = nomeText.getText().toString();
        String descricao = descricaoText.getText().toString();
        String estoque = estoqueText.getText().toString();

        if(!codigo.isEmpty() && !nome.isEmpty() && !descricao.isEmpty() && !estoque.isEmpty()){
            int estoqueInt = Integer.parseInt(estoque);
            salvarProduto(codigo, nome, descricao, estoqueInt);
        } else {
            showToast("Preencha todos os campos.");
        }
    }

    private void irParaMenuActivity() {
        Intent intent = new Intent(CadastroProdutoActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

}