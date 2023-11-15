package com.rishab;

import java.util.ArrayList;
import java.util.List;

enum Geometry {POINT, LINE, POLYGON}

enum Color {BLACK, BLUE, GREEN, ORANGE, RED}

enum PointMarker {CIRCLE, PUSH_PIN, STAR, SQUARE, TRIANGLE}

enum LineMarker {DASHED, DOTTED, SOLID}

enum UsageType {ENTERTAINMENT, GOVERNMENT, RESIDENTIAL, SPORTS}

enum UtilityType {ELECTRICAL, FIBER_OPTIC, GAS, WATER}

interface Mappable {
    String JSON_PROPERTY = """
        "properties" : {%s}\s""";

    String getLabel();

    Geometry getShape();

    String getMarker();

    default String toJSON() {
        return """
            "type": "%s", "label": "%s", "marker": "%s"\s""".formatted(getShape(), getLabel(), getMarker());
    }

    static void mapIt(Mappable mappable) {
        System.out.println(JSON_PROPERTY.formatted(mappable.toJSON()));
    }
}

class Building implements Mappable {
    private String name;
    private UsageType usage;

    public Building(String name, UsageType usage) {
        this.name = name;
        this.usage = usage;
    }

    @Override
    public String getLabel() {
        return this.name + "(" + this.usage + ")";
    }

    @Override
    public Geometry getShape() {
        return Geometry.POINT;
    }

    @Override
    public String getMarker() {
        return switch (usage) {
            case ENTERTAINMENT -> Color.GREEN + "—" + PointMarker.TRIANGLE;
            case GOVERNMENT -> Color.RED + "—" + PointMarker.STAR;
            case RESIDENTIAL -> Color.BLUE + "—" + PointMarker.SQUARE;
            case SPORTS -> Color.ORANGE + "—" + PointMarker.PUSH_PIN;
            default -> Color.BLACK + "—" + PointMarker.CIRCLE;
        };
    }

    @Override
    public String toJSON() {
        return Mappable.super.toJSON() + """
            , "name": "%s", "usage": "%s"\s""".formatted(this.name, this.usage);
    }
}

class UtilityLine implements Mappable {
    private String name;
    private UtilityType type;

    public UtilityLine(String name, UtilityType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getLabel() {
        return this.name + "(" + this.type + ")";
    }

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }

    @Override
    public String getMarker() {
        return switch (type) {
            case ELECTRICAL -> Color.RED + "—" + LineMarker.DASHED;
            case FIBER_OPTIC -> Color.GREEN + "—" + LineMarker.DOTTED;
            case GAS -> Color.ORANGE + "—" + LineMarker.SOLID;
            case WATER -> Color.BLUE + "—" + LineMarker.SOLID;
            default -> Color.BLACK + "—" + LineMarker.SOLID;
        };
    }

    @Override
    public String toJSON() {
        return Mappable.super.toJSON() + """
            , "name": "%s", "utility": "%s"\s""".formatted(this.name, this.type);
    }
}

public class InterfaceChallenge {
    public static void main(String[] args) {

        List<Mappable> mappables = new ArrayList<>();

        mappables.add(new Building("Town Hall", UsageType.GOVERNMENT));
        mappables.add(new Building("Theater", UsageType.ENTERTAINMENT));
        mappables.add(new Building("Stadium", UsageType.SPORTS));

        mappables.add(new UtilityLine("Power Line", UtilityType.ELECTRICAL));
        mappables.add(new UtilityLine("Gas Line", UtilityType.GAS));
        mappables.add(new UtilityLine("Water Line", UtilityType.WATER));

        for (var mappable : mappables) {
            Mappable.mapIt(mappable);
        }

        System.out.println("\n\n");

        ISaveAble player = new Player("Tommy", 10, 15);
        ISaveAble monster = new Monster("Werewolf", 20, 40);

        System.out.println("Player write: " + player.write());
        System.out.println("Monster write: " + monster.write());

        List<String> playerData = List.of("Bob", "20", "30", "Axe");
        List<String> monsterData = List.of("Vampire", "50", "100");

        player.read(playerData);
        monster.read(monsterData);

        System.out.println("Player read: " + player);
        System.out.println("Monster read: " + monster);

    }
}

interface ISaveAble {
    List<String> write();

    void read(List<String> savedValues);
}

class Player implements ISaveAble {
    private String name;
    private String weapon;
    private int hitPoints;
    private int strength;

    public Player(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.weapon = "Sword";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return this.weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public List<String> write() {
        return List.of(this.name, String.valueOf(this.hitPoints), String.valueOf(this.strength), this.weapon);
    }

    @Override
    public void read(List<String> savedValues) {
        if (savedValues != null && savedValues.size() > 0) {
            this.name = savedValues.get(0);
            this.hitPoints = Integer.parseInt(savedValues.get(1));
            this.strength = Integer.parseInt(savedValues.get(2));
            this.weapon = savedValues.get(3);
        }
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', hitPoints=" + hitPoints + ", strength=" + strength + ", weapon='" + weapon + "'}";
    }
}

class Monster implements ISaveAble {
    private String name;
    private int hitPoints;
    private int strength;

    public Monster(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public List<String> write() {
        return List.of(this.name, String.valueOf(this.hitPoints), String.valueOf(this.strength));
    }

    @Override
    public void read(List<String> savedValues) {
        if (savedValues != null && savedValues.size() > 0) {
            this.name = savedValues.get(0);
            this.hitPoints = Integer.parseInt(savedValues.get(1));
            this.strength = Integer.parseInt(savedValues.get(2));
        }
    }

    @Override
    public String toString() {
        return "Monster{name='" + name + "', hitPoints=" + hitPoints + ", strength=" + strength + "}";
    }
}
