package damisbachtiar.web.id.hitungnilai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edNama,edNim,edTugas,edHasilTugas,edPersentaseTugas,edUTS,edHasilUTS,edPersentaseUTS,edUAS,edHasilUAS,edPersentaseUAS,edNAAngka,edNAHuruf,edPredikat;
    Button btnHitung;
    int nilaiTugas,nilaiUTS,nilaiUAS=0;
    float hasilTugas,hasilUTS,hasilUAS,NAAngka;
    String NAHuruf,Predikat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindInput();
        this.bindListener();
    }

    void bindInput(){
        edNama= (EditText) findViewById(R.id.edNama);
        edNim= (EditText) findViewById(R.id.edNim);
        edTugas= (EditText) findViewById(R.id.edTugas);
        edHasilTugas= (EditText) findViewById(R.id.edHasilTugas);
        edPersentaseTugas= (EditText) findViewById(R.id.edPersentaseTugas);
        edUTS= (EditText) findViewById(R.id.edUTS);
        edHasilUTS= (EditText) findViewById(R.id.edHasilUTS);
        edPersentaseUTS= (EditText) findViewById(R.id.edPersentaseUTS);
        edUAS= (EditText) findViewById(R.id.edUAS);
        edHasilUAS= (EditText) findViewById(R.id.edHasilUAS);
        edPersentaseUAS= (EditText) findViewById(R.id.edPersentaseUAS);
        edNAAngka= (EditText) findViewById(R.id.edNAAngka);
        edNAHuruf= (EditText) findViewById(R.id.edNAHuruf);
        edPredikat= (EditText) findViewById(R.id.edPredikat);
        btnHitung=(Button) findViewById(R.id.btnHitung);
    }

    void bindListener(){
        edPersentaseTugas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                hitungNilaiTugas();
            }
        });
        edPersentaseUTS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                hitungNilaiUTS();
            }
        });
        edPersentaseUAS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                hitungNilaiUAS();
                hitungNA();
            }
        });
        btnHitung.setOnClickListener(btnHitungListener);



    }
    private View.OnClickListener btnHitungListener=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            hitungNA();
        }
    };

    void hitungNilaiTugas(){
        if(edTugas.getText().toString().matches("")){
            showMessageTugas();
        }else{
            nilaiTugas=Integer.parseInt(edTugas.getText().toString());
            int persentase=Integer.parseInt(edPersentaseTugas.getText().toString());
            hasilTugas=(nilaiTugas*persentase)/100;
            edHasilTugas.setText(String.valueOf(hasilTugas));
        }
    }
    void showMessageTugas(){
        Toast.makeText(this, "Masukan Nilai Tugas Terlebih dahulu", Toast.LENGTH_SHORT).show();
        return;
    }

    void hitungNilaiUTS(){
        if(edUTS.getText().toString().matches("")){
            showMessageUTS();
        }else{
            nilaiUTS=Integer.parseInt(edUTS.getText().toString());
            int persentase=Integer.parseInt(edPersentaseUTS.getText().toString());
            hasilUTS=(nilaiUTS*persentase)/100;
            edHasilUTS.setText(String.valueOf(hasilUTS));
        }
    }
    void showMessageUTS(){
        Toast.makeText(this, "Masukan Nilai UTS Terlebih dahulu", Toast.LENGTH_SHORT).show();
        return;
    }

    void hitungNilaiUAS(){
        if(edUAS.getText().toString().matches("")){
            showMessageUAS();
        }else{
            nilaiUAS=Integer.parseInt(edUAS.getText().toString());
            int persentase=Integer.parseInt(edPersentaseUAS.getText().toString());
            hasilUAS=(nilaiUAS*persentase)/100;
            edHasilUAS.setText(String.valueOf(hasilUAS));
        }
    }
    void showMessageUAS(){
        Toast.makeText(this, "Masukan Nilai UAS Terlebih dahulu", Toast.LENGTH_SHORT).show();
        return;
    }

    void hitungNA(){
        hitungNilaiTugas();
        hitungNilaiUTS();
        hitungNilaiUAS();
        NAAngka=hasilTugas+hasilUTS+hasilUAS;
        NAHuruf=getNH(NAAngka);
        Predikat = getPredikat(NAHuruf);
        edNAAngka.setText(String.valueOf(NAAngka));
        edNAHuruf.setText(NAHuruf);
        edPredikat.setText(Predikat);

    }

    String getNH(float na) {
        String nHuruf;
        if(na>=85)
        {
            nHuruf="A";
        }
        else if(na>=70)
        {
            nHuruf="B";
        }
        else if(na>=60)
        {
            nHuruf="C";
        }
        else if(na>=40)
        {
            nHuruf="D";
        }
        else
        {
            nHuruf="E";
        }
        return nHuruf;
    }

    String getPredikat(String huruf) {
        String predikat;
        if(huruf.equalsIgnoreCase("A"))
        {
            predikat="Apik";
        }
        else if(huruf.equalsIgnoreCase("B"))
        {
            predikat="Baik";
        }
        else if(huruf.equalsIgnoreCase("C"))
        {
            predikat="Cukup";
        }
        else if(huruf.equalsIgnoreCase("D"))
        {
            predikat="Dableq";
        }
        else
        {
            predikat="Elek";
        }
        return predikat;
    }

}
