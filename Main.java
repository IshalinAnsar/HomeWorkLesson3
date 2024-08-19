package ru.gb.family_tree;

import ru.gb.family_tree.family_tree.FamilyTree;
import ru.gb.family_tree.human.Human;
import ru.gb.family_tree.human.Gender;
import ru.gb.family_tree.writer.FileHandler;

import java.time.LocalDate;

public class Main {
    final static String filePath = "src/family_tree/writer/tree.txt"
    public static void main(String[] args){

        FamilyTree tree = load();
        FamilyTree tree = testTree();
        save(tree);

        System.out.println(tree);
    }

    private static FamilyTree load(){
        FileHandler fileHandler = new FileHandler();
        fileHandler.setPath(filePath);
        return (FamilyTree) fileHandler.read();
    }


    private static void save(FamilyTree familyTree) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.setPath(filePath);
        fileHandler.save(familyTree);
    }

    private static FamilyTree testTree(){
        FamilyTree tree = new FamilyTree();

        Human vasya = new Human(name:"Василий", Gender.Male, LocalDate.of(year:1963,month:12,dayOfMonth:10));
        Human masha = new Human(name:"Мария", Gender.Female, LocalDate.of(year:1965,month:9,dayOfMonth:15));

        tree.add(vasya);
        tree.add(masha);
        tree.setWedding(vasya,masha);

        Human christina = new Human(name:"Кристина", Gender.Female, LocalDate.of(year:1988,month:7,dayOfMonth:5),vasya,masha);
        Human semyon = new Human(name:"Семен", Gender.Male, LocalDate.of(year:1991,month:1,dayOfMonth:25),vasya,masha);

        tree.Add (christina);
        tree.Add (semyon);


        Human grandMother = new Human (name:"Лариса", Gender.Female, LocalDate.of(year:1945,month:9,dayOfMonth:1));
        grandMother.addChild(vasya);

        tree.add(grandMother);
        return tree;
    }
}