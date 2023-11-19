package com.eliasfs06.imdmarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.eliasfs06.imdmarket.db.DBHelper;
import com.eliasfs06.imdmarket.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ListaProdutosActivity extends AppCompatActivity {

    ListView produtosListView;

    Button voltarBtn;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        dbHelper = DBHelper.getInstance(this);

        voltarBtn = findViewById(R.id.voltarBtn);
        voltarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaMenuActivity();
            }
        });

        produtosListView = findViewById(R.id.produtosList);

        List<Produto> produtos = dbHelper.getAllProdutos();
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this, R.layout.list_item_produto, produtos) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item_produto, parent, false);
                }

                TextView nomeProduto = convertView.findViewById(R.id.nome_produto);
                TextView descricaoProduto = convertView.findViewById(R.id.descricao_produto);

                Produto produto = getItem(position);

                if (produto != null) {
                    nomeProduto.setText(produto.getCodigo() + " - " + produto.getNome());
                    descricaoProduto.setText(produto.getDescricao());
                }

                return convertView;
            }
        };

        produtosListView.setAdapter(adapter);
    }

    private void irParaMenuActivity() {
        Intent intent = new Intent(ListaProdutosActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}