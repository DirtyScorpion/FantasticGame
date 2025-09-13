package djyava;

import java.util.*;

abstract class Fraction implements Comparable<Fraction> { 
    protected String name;  
    protected int technologicalLevel; 
    
    public Fraction(String name, int technologicalLevel) {
        this.name = name;
        this.technologicalLevel = Math.max(0, Math.min(100, technologicalLevel));
    }
    
    abstract int getLoyalty();
    
    @Override
    public int compareTo(Fraction other) {
        if (other == null) return 1;
        return Integer.compare(this.getLoyalty(), other.getLoyalty());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction fraction = (Fraction) obj;
        return technologicalLevel == fraction.technologicalLevel && 
               Objects.equals(name, fraction.name);
    }
    
    @Override
    public String toString() {
        return String.format("%s [Техн.уровень: %d, Лояльность: %d]", 
                           name, technologicalLevel, getLoyalty());
    }
}

class FlameChildren extends Fraction {
    private int insanity;
    private int ideologySimilarity;
    
    public FlameChildren(int technologicalLevel, int insanity, int ideologySimilarity) {
        super("Дети пламени", technologicalLevel);
        this.insanity = Math.max(0, Math.min(100, insanity));
        this.ideologySimilarity = Math.max(0, Math.min(100, ideologySimilarity));
    }

    @Override
    int getLoyalty() {
        int baseLoyalty = 50;
        baseLoyalty -= (technologicalLevel - 50) / 5;
        
        int insanityDifference = 100 - insanity;
        
        if (insanityDifference > 60)
            baseLoyalty -= insanityDifference / 2;
        else if (insanityDifference < 10)
            baseLoyalty += 25 + (10 - insanityDifference) * 2;
        
        baseLoyalty += ideologySimilarity / 4;
        
        return Math.max(0, Math.min(100, baseLoyalty));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        FlameChildren other = (FlameChildren) obj;
        return insanity == other.insanity && 
               ideologySimilarity == other.ideologySimilarity;
    }
    
    @Override
    public String toString() {
        return String.format("%s [Безумие: %d, Идеология: %d, Лояльность: %d]", 
                           name, insanity, ideologySimilarity, getLoyalty());
    }
}

class SunChildren extends Fraction {
    private int geneticStability;
    
    public SunChildren(int geneticStability, int technologicalLevel) {
        super("Солнечные дети", technologicalLevel);
        this.geneticStability = Math.max(0, Math.min(100, geneticStability));
    }
    
    @Override
    int getLoyalty() {
        int baseLoyalty = 50;
        
        baseLoyalty -= (technologicalLevel - 50) / 3;
        
        if (geneticStability > 51)
            baseLoyalty += geneticStability / 2;
        else if (geneticStability < 50)
            baseLoyalty -= (100 - geneticStability) / 2;
        
        return Math.max(0, Math.min(100, baseLoyalty));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        SunChildren other = (SunChildren) obj;
        return geneticStability == other.geneticStability;
    }
     
    @Override
    public String toString() {
        return String.format("%s [Ген.стабильность: %d, Лояльность: %d]", 
                           name, geneticStability, getLoyalty());
    }
}

class Engineers extends Fraction {
    private int tradeLevel;
    private boolean friendshipWithOthers;
    
    public Engineers(int technologicalLevel, int tradeLevel, boolean friendshipWithOthers) {
        super("Инженеры", technologicalLevel); 
        this.tradeLevel = Math.max(0, Math.min(100, tradeLevel));
        this.friendshipWithOthers = friendshipWithOthers;
    }
    
    @Override
    int getLoyalty() {
        int baseLoyalty = 50;
        
        baseLoyalty += (technologicalLevel - 50) / 2;
        baseLoyalty += (tradeLevel - 50) / 5;
        
        if (friendshipWithOthers)
            baseLoyalty -= (tradeLevel - 50) / 4;
        
        return Math.max(0, Math.min(100, baseLoyalty));        
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Engineers other = (Engineers) obj;
        return tradeLevel == other.tradeLevel && 
               friendshipWithOthers == other.friendshipWithOthers;
    }
    
    
    @Override
    public String toString() {
        return String.format("%s [Торговля: %d, Дружба: %s, Лояльность: %d]", 
                           name, tradeLevel, friendshipWithOthers ? "Да" : "Нет", getLoyalty());
    }
}

public class Comparablee {
    public static void main(String[] args) {
        
        System.out.println("ИСХОДНЫЙ СПИСОК ФРАКЦИЙ \n");
        List<Fraction> frac = new ArrayList<>();
        frac.add(new FlameChildren(30, 85, 70));
        frac.add(new Engineers(80, 90, true));
        frac.add(new SunChildren(75, 40));
        frac.add(new FlameChildren(60, 20, 90));
        frac.add(new Engineers(95, 60, false));
        frac.add(new SunChildren(30, 70));
        
        for (Fraction fraction : frac) {
            System.out.println(fraction);
        }
        
        
        System.out.println("СОРТИРОВКА ПО ЛОЯЛЬНОСТИ \n");
        Collections.sort(frac);
        for (Fraction fraction : frac) {
            System.out.println(fraction);
        }
        

        
        System.out.println("РАВЕНСТВО \n");
        Fraction fc1 = new FlameChildren(30, 85, 70);
        Fraction fc2 = new FlameChildren(30, 85, 70);
        Fraction fc3 = new FlameChildren(30, 85, 71);
        
        System.out.println("fc1.equals(fc2): " + fc1.equals(fc2));
        System.out.println("fc1.equals(fc3): " + fc1.equals(fc3));
        
        
     
    }
}