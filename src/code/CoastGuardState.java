package code;

import code.datastructure.Pair;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

class Ship implements Cloneable{
    public Pair<Integer, Integer> pos;
    public int passengerCount;
    public int blackBoxLive;

    public Ship(Pair<Integer, Integer> pos, int passengerCount, int blackBoxLive){
        this.pos = pos;
        this.passengerCount = passengerCount;
        this.blackBoxLive = blackBoxLive;
    }

    // returns true if the ship is wreck
    public int update(){
        int lost = 0;
        if(passengerCount == 0) {
            blackBoxLive = Math.max(0, blackBoxLive - 1);
            if(blackBoxLive == 0)
                    lost ++;
        }
        if(passengerCount != 0) lost++;
        passengerCount = Math.max(0, passengerCount - 1);
        return lost;
    }

    public boolean isWreck(){
        return passengerCount == 0 && blackBoxLive == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return passengerCount == ship.passengerCount && blackBoxLive == ship.blackBoxLive && Objects.equals(pos, ship.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, passengerCount, blackBoxLive);
    }

    @Override
    public Ship clone() {
        try {
            Ship clone = (Ship) super.clone();
            clone.pos = this.pos.clone();
            clone.passengerCount = this.passengerCount;
            clone.blackBoxLive = this.blackBoxLive;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
public class CoastGuardState implements Cloneable {
    // TODO DS needs to be modified
    // ArrayList has bad PROF
    public static int gridW, gridH;
    public static TreeSet<Pair<Integer, Integer>> stations;

    public Pair<Integer, Integer> pos;
    public int passengerOnBoard;
    public TreeMap<Pair<Integer, Integer>, Ship> ships;


    @Override
    public CoastGuardState clone() {
        try {
            CoastGuardState clone = (CoastGuardState) super.clone();
            clone.pos = this.pos.clone();
            clone.passengerOnBoard = this.passengerOnBoard;
            clone.ships = new TreeMap<>();
            // deep clone
            for(var entry : this.ships.entrySet())
                clone.ships.put(entry.getKey(), entry.getValue().clone());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoastGuardState that = (CoastGuardState) o;
        // Compare ships
        boolean similarShips = true;
        if(that.ships.size() != this.ships.size())
            similarShips = false;
        else {
            TreeSet<Pair<Integer, Integer>> hs = new TreeSet<>();
            for (var entry : that.ships.entrySet())
                hs.add(entry.getKey());
            for (var entry : this.ships.entrySet())
                if(!hs.contains(entry.getKey()) || !that.ships.get(entry.getKey()).equals(entry.getValue()))
                    similarShips = false;
        }
        return passengerOnBoard == that.passengerOnBoard && Objects.equals(pos, that.pos) && similarShips;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, passengerOnBoard, ships);
    }

    @Override
    public String toString(){
        return ""+this.pos.toString()+"_"+this.passengerOnBoard;
    }
}