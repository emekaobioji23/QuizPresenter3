/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quizpresenter3;

import javafx.beans.property.*;

public class Second {

    private StringProperty strP = new SimpleStringProperty();

    Second(String s)
    {
        setStrP(s);//set to zero
    }
//Get Property

    public StringProperty getProperty()
    {
        return strP;
    }

//Get String
    public String getStrP()
    {
        return strP.get();
    }

//Changes StringProperty every 0.25s
    public void setStrP(String i)
    {
        this.strP.set(i);
    }
}