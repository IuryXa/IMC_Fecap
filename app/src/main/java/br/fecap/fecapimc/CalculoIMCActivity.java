package br.fecap.fecapimc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculoIMCActivity extends AppCompatActivity {

    private Button btnCalculoIMC;
    private Button btnLimpar;
    private Button btnFechar;
    private EditText pesoText;
    private EditText alturaText;
    private float altura;
    private float peso;
    private float IMC;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calculoimcactivity);

        btnCalculoIMC = findViewById(R.id.btnCalcularIMC);
        btnCalculoIMC.setOnClickListener(view ->{
            CalcularImc();
            ClassificaoIMC();
            intent.putExtra("peso", peso);
            intent.putExtra("altura", altura);
            intent.putExtra("imc", IMC);
            startActivity(intent);
            finish();
        });

        btnLimpar = findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(view ->{
            LimparText();
        });

        btnFechar = findViewById(R.id.btnFechar);
        btnFechar.setOnClickListener(view ->{
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void CalcularImc(){
        pesoText = findViewById(R.id.textPeso);
        alturaText = findViewById(R.id.textAltura);

         altura = Float.parseFloat(alturaText.getText().toString());
         peso = Float.parseFloat(pesoText.getText().toString());
         IMC = peso/(altura*altura);
    }

    private void LimparText(){
        pesoText = findViewById(R.id.textPeso);
        alturaText = findViewById(R.id.textAltura);

        pesoText.setText("");
        alturaText.setText("");
    }

    private void ClassificaoIMC(){
        if(IMC < 18.5){
            intent = new Intent(this, AbaixoDoPesoActivity.class);
        }else if(IMC>=18.5 && IMC < 25){
            intent = new Intent(this, PesoNormalActivity.class);
        }else if(IMC >=25 && IMC <30){
            intent = new Intent(this, SobrepesoActivity.class);
        }else if (IMC >=30 && IMC < 35) {
            intent = new Intent(this, Obesidade1Activity.class);
        } else if (IMC >=35 && IMC <40) {
            intent = new Intent(this, Obesidade2Activity.class);
        } else if (IMC >=40) {
            intent = new Intent(this, Obesidade3Activity.class);
        }
    }
}