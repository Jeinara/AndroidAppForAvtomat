package com.example.jj.androidappforavtomat.contactsAPI;

import android.content.ContentResolver;
import android.content.Context;
import android.widget.ArrayAdapter;

public interface IContactsFragmentActivity {

    public Context getApplicationContext();

    void setAdapterList (ArrayAdapter<Contact> adapter);

    public ContentResolver getContentResolver();

    }
