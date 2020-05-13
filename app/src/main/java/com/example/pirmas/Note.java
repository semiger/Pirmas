package com.example.pirmas;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Note implements Serializable {
    public String Title;
    public String Content;
    public Note (String title, String content)
    {
        Title = title;
        Content = content;
    }

    @Override
    public String toString() {
        return Title + ": " + Content;
    }
}
