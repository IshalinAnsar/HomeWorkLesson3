package ru.gb.family_tree.family_tree;

import ru.gb.family_tree.human.Human;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FamilyTreeIterator implements Iterator<Human> {
    private List<Human> list;
    private int index;

    public FamilyTreeIterator(List<Human> list){
        this.list = list;
        index = 0;
    }

    @Override
    public boolean hasNext(){return index<list.size();}

    @Override
    public Human next(){return list.get(index++);}
}

