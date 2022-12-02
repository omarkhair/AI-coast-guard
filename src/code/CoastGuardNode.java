package code;

import code.datastructure.Pair;

import java.util.ArrayList;

public class CoastGuardNode extends Node<CoastGuardState>{
    public CoastGuardNode(CoastGuardState state){
        super();
        this.state = state;
    }

    public int update(){
        // TODO NEED to calc Cost and pass it up
        // update the ships
        int lost = 0;
        ArrayList<Pair<Integer, Integer>> toBeDeleted = new ArrayList<>();
        for(var entry : state.ships.entrySet()){
            Ship s = entry.getValue();
            lost += s.update();
            if(s.isWreck())
                toBeDeleted.add(entry.getKey());
        }
        // remove wrecked ships
        for(var key : toBeDeleted)
            state.ships.remove(key);
        return lost;
    }

    @Override
    public String toString(){
        return ""+Math.abs(this.hashCode()) + "_" + this.state.toString() + "_C"+this.pathCost;
    }
}