package com.gferreyra.herewegoagain;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Command extends RealmObject {

    @PrimaryKey
    @Required
    private String _commandid;

    @Required
    private String input;

    private RealmList<FrameData> framedata;

    public String get_commandid() {
        return _commandid;
    }

    public void set_commandid(String _commandid) {
        this._commandid = _commandid;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public RealmList<FrameData> getFramedata() {
        return framedata;
    }

    public void setFramedata(FrameData framedata) {
        this.framedata.add(framedata);
    }
}
