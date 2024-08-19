package ru.gb.family_tree.family_tree;

import ru.gb.family_tree.human.Human;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List; //...

public class FamilyTree implements Serializable, Iterable<Human> {
    private long humansId;
    private List<ru.gb.family_tree.human.Human> humanList;

    public FamilyTree(){
        this(new ArrayList<>());
    }

    public boolean add (ru.gb.family_tree.human.Human human){
        if (human==null){
            return false;
        }
        if (!humanList.contains(human)){
            humanList.add(human);
            human.setId(humansId++);

            addToParents(human);
            addToChildren(human);

            return true;
        }
        return false;
    }

    private void addToParents(ru.gb.family_tree.human.Human human){
        for (ru.gb.family_tree.human.Human parent: human.getParents()){
            parent.addChild(human);
        }
    }

    private void addToChildren(ru.gb.family_tree.human.Human human){
        for (ru.gb.family_tree.human.Human child: human.getChildren()){
            child.addParent(human);
        }
    }

    public List<ru.gb.family_tree.human.Human> getSiblings(int id){
        ru.gb.family_tree.human.Human human = getById(id);
        if (human==null){
            return null;
        }
        List<ru.gb.family_tree.human.Human> res = new ArrayList<>();
        for (ru.gb.family_tree.human.Human parent: human.getParents()){
            for (ru.gb.family_tree.human.Human child: parent.getChildren()){
                if (!child.equals(human)){
                    res.add(child);
                }
            }
        return res;
        }
    }

    public List<ru.gb.family_tree.human.Human> getByName(String name){
        List<ru.gb.family_tree.human.Human> res = new ArrayList<>();
        for (ru.gb.family_tree.human.Human human: humanList){
            if (human.getName().equals(name)){
                res.add(human);
            }
        }
        return res;
    }

    public boolean setWedding(long humanId1, long humanId2){
        if (checkId(humanId1) && checkId(humanId2)){
            ru.gb.family_tree.human.Human human1 = getById(humanId1);
            ru.gb.family_tree.human.Human human2 = getById(humanId2);
            return setWedding(human1,human2);
        }
        return false;
    }

    public boolean setWedding (ru.gb.family_tree.human.Human human1, ru.gb.family_tree.human.Human human2){
        if (human1.getSpouse()==null && human2.getSpouse()==null){
            human1.setSpouse(human2);
            human2.setSpouse(human1);
            return true;
        }else {
            return false;
        }
    }

    public boolean setDivorce(long humanId1, long humanId2){
        if (checkId(humanId1) && checkId(humanId2)){
            ru.gb.family_tree.human.Human human1 = getById(humanId1);
            ru.gb.family_tree.human.Human human2 = getById(humanId2);
            return setDivorce(human1,human2);
        }
        return false;
    }

    public boolean setDivorce(ru.gb.family_tree.human.Human human1, ru.gb.family_tree.human.Human human2){
        if (human1.getSpouse() !=null && human2.getSpouse() !=null){
            human1.setSpouse(null);
            human2.setSpouse(null);
            return true;
        }else {
            return false;
        }
    }

    public boolean remove(long humansId){
        if (checkId(humansId)){
            ru.gb.family_tree.human.Human human= getById(humansId);
            return humanList.remove(human);
        }
        return false;
    }

    private boolean checkId(long id){
        return id<humansId && id>=0;
    }

    public ru.gb.family_tree.human.Human getById(long id){
        for (ru.gb.family_tree.human.Human human: humanList){
            if (human.getId()==id){
                return human;
            }
        }
        return null;
    }

    @Override
    public String toString() {return getInfo();}

    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("В дереве ");
        sb.append(humanList.size());
        sb.append(" объектов: \n");
        for (Human human: humanList){
            sb.append(human);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public Iterator<Human> iterator(){
        return new FamilyTreeIterator(humanList);
    }

    public void sortByName(){
        humanList.sort(new HumanComparatorByName());
    }

    public void sortByBirthDate(){
        humanList.sort(new HumanComparatorByBirthDate());
    }


}
