package com.example.aplicativoteste;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public void adicionarLivro(String titulo, String autor) {
        Log.d("dev-log-test", "Adicionando livro");
        LinearLayout containerLivros = findViewById(R.id.containerLivros);

        TextView txtTitulo = new TextView(this);
        txtTitulo.setText(titulo);
        txtTitulo.setTextSize(20);
        txtTitulo.setPadding(0, 20, 0, 0);
        txtTitulo.setTextAlignment(TEXT_ALIGNMENT_CENTER);

        TextView txtAutor = new TextView(this);
        txtAutor.setText(autor);
        txtAutor.setTextSize(16);
        txtAutor.setTextAlignment(TEXT_ALIGNMENT_CENTER);

        SwitchCompat switchLeitura = new SwitchCompat(this);
        switchLeitura.setChecked(false);
        switchLeitura.setText("Leitura Pendente");
        switchLeitura.setTextAlignment(TEXT_ALIGNMENT_CENTER);

        switchLeitura.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchLeitura.setText("Leitura Concluída");
            } else {
                switchLeitura.setText("Leitura Pendente");
            }
        });

        Button deleteButton = new Button(this);
        deleteButton.setText("Excluir");
        deleteButton.setOnClickListener(v -> {
            containerLivros.removeView(txtTitulo);
            containerLivros.removeView(txtAutor);
            containerLivros.removeView(switchLeitura);
            containerLivros.removeView(deleteButton);
        });

        containerLivros.addView(txtTitulo);
        containerLivros.addView(txtAutor);
        containerLivros.addView(switchLeitura);
        containerLivros.addView(deleteButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("dev-log-test", "MainActivity - on create");

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText tituloLivro = findViewById(R.id.editTitle);
        EditText autorLivro = findViewById(R.id.editAuthor);
        Button confirmButton = findViewById(R.id.btnConfirmAdd);
        TextView avisoForms = findViewById(R.id.avisoForms);

        confirmButton.setOnClickListener(v -> {
            String titulo = tituloLivro.getText().toString();
            String autor = autorLivro.getText().toString();

            if (titulo.isEmpty() || autor.isEmpty()) {
                avisoForms.setText("Preencha todos os campos!");
                avisoForms.setVisibility(TextView.VISIBLE);
                return;
            }

            avisoForms.setVisibility(TextView.INVISIBLE);

            tituloLivro.setText("");
            autorLivro.setText("");
            adicionarLivro(titulo, autor);
        });
    }
}