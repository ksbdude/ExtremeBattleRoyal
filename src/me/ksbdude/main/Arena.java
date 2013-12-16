package me.ksbdude.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class Arena{
	 
    //you want some info about the arena stored here
    public int id = 0;//the arena id
    Location spawn = null;//spawn location for the arena
    List<String> players = new ArrayList<String>();//list of players
 
    //now let's make a few getters/setters, and a constructor
    public Arena(Location loc, int id){
        this.spawn = loc;
        this.id = id;
    }
 
    public int getId(){
        return this.id;
    }
 
    public List<String> getPlayers(){
        return this.players;
    }
}