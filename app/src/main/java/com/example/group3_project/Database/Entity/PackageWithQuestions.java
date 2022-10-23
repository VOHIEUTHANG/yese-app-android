package com.example.group3_project.Database.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PackageWithQuestions {
    @Embedded
    public QuestionPackage apackage;
    @Relation(
            parentColumn = "id",
            entityColumn = "rk_packageID"
    )
    public List<Question> questions;
}
