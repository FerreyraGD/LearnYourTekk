package com.gferreyra.herewegoagain;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Character extends RealmObject{
    @PrimaryKey
    @Required
    private String _characterid;

    @Required
    private String name;

    @Required
    private String difficulty;

    private RealmList<Command> commands;

    public String get_characterid() {
        return _characterid;
    }

    public void set_characterid(String _characterid) {
        this._characterid = _characterid;
    }

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

    public void setCommands(Command command) {
        this.commands.add(command);
    }
}
