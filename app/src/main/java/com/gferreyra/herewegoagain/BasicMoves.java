package com.gferreyra.herewegoagain;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;

public class BasicMoves extends RealmObject {

    private String notation;

    private String hit_level;

    private String damage;

    private String speed;

    private String on_block;

    private String on_hit;

    private String on_ch;

    private String notes;

    private String rage_art;

    private String rage_drive;

    private String tail_spin;

    private String homing;

    private String power_crush;

    private String wall_bounce;

    private String wall_break;

    @LinkingObjects("basicmoves")
    private final RealmResults<CharacterData> character = null;

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getHit_level() {
        return hit_level;
    }

    public void setHit_level(String hit_level) {
        this.hit_level = hit_level;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getOn_block() {
        return on_block;
    }

    public void setOn_block(String on_block) {
        this.on_block = on_block;
    }

    public String getOn_hit() {
        return on_hit;
    }

    public void setOn_hit(String on_hit) {
        this.on_hit = on_hit;
    }

    public String getOn_ch() {
        return on_ch;
    }

    public void setOn_ch(String on_ch) {
        this.on_ch = on_ch;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRage_art() {
        return rage_art;
    }

    public void setRage_art(String rage_art) {
        this.rage_art = rage_art;
    }

    public String getRage_drive() {
        return rage_drive;
    }

    public void setRage_drive(String rage_drive) {
        this.rage_drive = rage_drive;
    }

    public String getTail_spin() {
        return tail_spin;
    }

    public void setTail_spin(String tail_spin) {
        this.tail_spin = tail_spin;
    }

    public String getHoming() {
        return homing;
    }

    public void setHoming(String homing) {
        this.homing = homing;
    }

    public String getPower_crush() {
        return power_crush;
    }

    public void setPower_crush(String power_crush) {
        this.power_crush = power_crush;
    }

    public String getWall_bounce() {
        return wall_bounce;
    }

    public void setWall_bounce(String wall_bounce) {
        this.wall_bounce = wall_bounce;
    }

    public String getWall_break() {
        return wall_break;
    }

    public void setWall_break(String wall_break) {
        this.wall_break = wall_break;
    }

    public RealmResults<CharacterData> getCharacter() {
        return character;
    }
}
