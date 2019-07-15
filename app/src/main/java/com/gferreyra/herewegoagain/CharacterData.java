package com.gferreyra.herewegoagain;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

//Object class to represent a character in the game of Tekken 7
public class CharacterData extends RealmObject {

    @PrimaryKey
    private String name;

    private String difficulty;

    private RealmList<BasicMoves> basicmoves;

    //GET / SET METHODS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public RealmList<BasicMoves> getBasicmoves() {
        return basicmoves;
    }

    public void setBasicmoves(RealmList<BasicMoves> basicmoves) {
        this.basicmoves = basicmoves;
    }
}
