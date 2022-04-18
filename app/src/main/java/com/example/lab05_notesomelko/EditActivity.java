package com.example.lab05_notesomelko;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    EditText txtNamenote;
    EditText txtContentnote;
    int IDnote;
    Context t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        txtNamenote=findViewById(R.id.etName);
        txtContentnote=findViewById(R.id.etContent);
        Intent i = getIntent();
        IDnote = i.getIntExtra("IDnote",-1);
        txtNamenote.setText(i.getStringExtra("Namenote"));
        txtContentnote.setText(i.getStringExtra("Contentnote"));
        t=this;
    }
    public void SaveNote(View v){
        DialogWindowSave();
    }
    public void Cancel(View v){

        Intent i = new Intent();
        i.putExtra("IDnote",IDnote);
        finish();
    }
    public void DialogWindowSave(){ //created by Igor Omelko
        LayoutInflater myLayout = LayoutInflater.from(this);
        View dialogView = myLayout.inflate(R.layout.alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dlg = builder.create();
        dlg.setIcon(R.drawable.image2);
        dlg.show();
        Button btncancelald =dlg.findViewById(R.id.btn_cancel_ald);
        Button btnokald =dlg.findViewById(R.id.btn_exit_ald);
        btnokald.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("IDnote",IDnote);
                i.putExtra("Namenote", txtNamenote.getText().toString());
                i.putExtra("Contentnote",txtContentnote.getText().toString());
                setResult(RESULT_OK,i);
                Toast.makeText(t,"Saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btncancelald.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                dlg.dismiss ();
            }
        });
    }
}
