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

public class EditarProdutoActivity extends AppCompatActivity {

    private EditText codigoText;
    private EditText nomeText;
    private EditText descricaoText;
    private EditText estoqueText;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

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

    private void atualizarProduto(String codigo, String nome, String descricao, int estoque) {
        Produto produto = dbHelper.getProduto(codigo);
        if(produto == null){
            showToast("Não há nenhum produto cadastrado com esse código.");
        } else {
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setEstoque(estoque);
            dbHelper.updateProduto(produto);
            showToast("Produto salvo com sucesso!");
            limparInput();
        }

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
            atualizarProduto(codigo, nome, descricao, estoqueInt);
        } else {
            showToast("Preencha todos os campos.");
        }

    }

    private void irParaMenuActivity() {
        Intent intent = new Intent(EditarProdutoActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}