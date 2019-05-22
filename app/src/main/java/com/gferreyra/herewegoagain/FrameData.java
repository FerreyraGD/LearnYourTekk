package com.gferreyra.herewegoagain;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class FrameData extends RealmObject {
    @PrimaryKey
    @Required
    private String _frameid;

    @Required
    private String hitlevel;

    @Required
    private String damage;

    @Required
    private String startFrame;

    @Required
    private String blockFrame;

    @Required
    private String hitFrame;

    @Required
    private String chFrame;

    private RealmList<Effect> effects;

    public String get_frameid() {
        return _frameid;
    }

    public void set_frameid(String _frameid) {
        this._frameid = _frameid;
    }

    public String getHitlevel() {
        return hitlevel;
    }

    public void setHitlevel(String hitlevel) {
        this.hitlevel = hitlevel;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getStartFrame() {
        return startFrame;
    }

    public void setStartFrame(String startFrame) {
        this.startFrame = startFrame;
    }

    public String getBlockFrame() {
        return blockFrame;
    }

    public void setBlockFrame(String blockFrame) {
        this.blockFrame = blockFrame;
    }

    public String getHitFrame() {
        return hitFrame;
    }

    public void setHitFrame(String hitFrame) {
        this.hitFrame = hitFrame;
    }

    public String getChFrame() {
        return chFrame;
    }

    public void setChFrame(String chFrame) {
        this.chFrame = chFrame;
    }

    public RealmList<Effect> getEffects() {
        return effects;
    }

    public void setEffects(Effect effects) {
        this.effects.add(effects);
    }
}
