package com.example.lab05_notesomelko;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter <Note> Adp;
    Intent i;
    Integer ID;
    ListView LVnotes;
    int sel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Adp = new <Note> ArrayAdapter(this, android.R.layout.simple_list_item_1);//set adapter for connect to listview
        i=new Intent(this,EditActivity.class);
        ID=-1;
        LVnotes = findViewById(R.id.lv_notes);//set listview for notes
        LVnotes.setAdapter(Adp);
        LVnotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sel = position;//selected note
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){//created by Igor Omelko
        if (data!=null&&(requestCode==111||requestCode==222||requestCode==333)){
            int pos = data.getIntExtra("IDnote", -1);
            String title = data.getStringExtra("Namenote");
            String content = data.getStringExtra("Contentnote");
            Note note = Adp.getItem(pos);
            note.SetName(title);
            note.SetContent(content);
            Adp.notifyDataSetChanged();
        }
        if (data == null && requestCode==111){
            Adp.remove(Adp.getItem(Adp.getCount()-1));
            Adp.notifyDataSetChanged();
        }
        else{

        }
        super.onActivityResult(requestCode,resultCode,data);
    }
    public void AddNote(View v)
    {
       /* if (ID>Integer.MAX_VALUE) {
            return;
        }*/
        i= new Intent(this, EditActivity.class);
        Note Newnote = new Note();
        Newnote.SetName("New note");
        Newnote.SetContent("Is empty");
        Adp.add(Newnote);
        int id = Adp.getPosition(Newnote);
        //Newnote.SetID(id+1);
        i.putExtra("IDnote",id);
        i.putExtra("Namenote", Newnote.GetName());
        i.putExtra("Contentnote",Newnote.GetContent());
        startActivityForResult(i,111);
    }
    public void DelNote(View v)
    {
        DialogWindowDelete();
    }
    public void EditNote(View v)
    {
        if(sel!=-1){
            Note Newnote = Adp.getItem(sel);
            i= new Intent(this, EditActivity.class);
            //Note Newnote = new Note();
            //Newnote.SetName("New note");
            //Newnote.SetContent("Is empty");
            //Adp.add(Newnote);
            int id = Adp.getPosition(Newnote);
            //Newnote.SetID(id+1);
            i.putExtra("IDnote",id);
            i.putExtra("Namenote", Newnote.GetName());
            i.putExtra("Contentnote",Newnote.GetContent());
            startActivityForResult(i,222);
            sel=-1;
        }
    }
    public void DialogWindowExit(){ //created by Igor Omelko
        LayoutInflater myLayout = LayoutInflater.from(this);
        View dialogView = myLayout.inflate(R.layout.alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dlg = builder.create();
        dlg.setIcon(R.drawable.image2);
        dlg.show();
        Button btncancelald =dlg.findViewById(R.id.btn_cancel_ald);
        Button btnexitald =dlg.findViewById(R.id.btn_exit_ald);
        btnexitald.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                System.exit (0);
            }
        });
        btncancelald.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                dlg.dismiss ();
            }
        });
    }
    public void DialogWindowDelete(){ //created by Igor Omelko
        LayoutInflater myLayout = LayoutInflater.from(this);
        View dialogView = myLayout.inflate(R.layout.alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dlg = builder.create();
        dlg.setIcon(R.drawable.image2);
        dlg.show();
        Button btncancelald =dlg.findViewById(R.id.btn_cancel_ald);
        Button btnexitald =dlg.findViewById(R.id.btn_exit_ald);
        btnexitald.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(sel!=-1){
                    Note Newnote = Adp.getItem(sel);
                    Adp.remove(Newnote);
                    sel=-1;
                }
                dlg.dismiss ();
            }});
        btncancelald.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                dlg.dismiss ();
            }}); }
    public void onBackPressed() { DialogWindowExit(); }
}