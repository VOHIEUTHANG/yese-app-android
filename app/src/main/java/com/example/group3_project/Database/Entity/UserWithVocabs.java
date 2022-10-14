package com.example.group3_project.Database.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithVocabs {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "username",
            entityColumn = "rk_username"
    )
    public List<Vocab> playlists;
}
