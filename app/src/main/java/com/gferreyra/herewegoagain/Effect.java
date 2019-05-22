package com.gferreyra.herewegoagain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Effect extends RealmObject {
    @PrimaryKey
    @Required
    private String _effectid;

    @Required
    private String effect;

    public String get_effectid() {
        return _effectid;
    }

    public void set_effectid(String _effectid) {
        this._effectid = _effectid;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
