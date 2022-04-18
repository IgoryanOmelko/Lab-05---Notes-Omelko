package com.example.lab05_notesomelko;
// Created by Igor Omelko.
public class Note {
    private Integer ID;
    private String name;
    private String content;
//Set and get ID of note.
    public void SetID(Integer IDset) {
        ID = IDset;
    }
    public Integer GetID(){
        return ID;
    }
//set and get name of note.
    public void SetName(String nameset) {
        name = nameset;
    }
    public String GetName() {
        return name;
    }
//set and get content of note.
   public void SetContent(String contentset) {
        content = contentset;
    }
   public String GetContent() {
        return content;
    }
//Metods
    public String toString(){
        return name;
    }
}
