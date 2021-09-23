package logicSimulator;

import datastructures.Signal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SaveInfoBitflip extends Signal{

     private List <Signal> MTF_bitflip_List = Collections.synchronizedList(new ArrayList<Signal>());
    private List <Integer> OriginalValues = Collections.synchronizedList(new ArrayList<Integer>());
    private List <Integer> NewValues = Collections.synchronizedList(new ArrayList<Integer>());

    public SaveInfoBitflip() {
        super();

    }

}

