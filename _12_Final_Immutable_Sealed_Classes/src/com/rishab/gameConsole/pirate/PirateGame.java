package com.rishab.gameConsole.pirate;

import com.rishab.gameConsole.game.Game;
import com.rishab.gameConsole.game.GameAction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PirateGame extends Game<Pirate> {
    private static final List<List<Town>> levelMap;

    static {
        levelMap = new ArrayList<>();
        System.out.println("Loading level map...");
        loadLevelMap();

        if (levelMap.size() == 0) {
            throw new RuntimeException("No levels found!");
        }

        System.out.println("Level map loaded!");
    }

    public PirateGame(String gameName) {
        super(gameName);
    }

    @Override
    public Pirate createNewPlayer(String name) {
        return new Pirate(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {
        Pirate pirate = getPlayer(playerIndex);
        System.out.println(pirate);
        List<Weapon> weapons = Weapon.getWeaponsByLevel(pirate.value("level"));

        Map<Character, GameAction> map = new LinkedHashMap<>();
        if (pirate.hasOpponents()) {
            for (Weapon w : weapons) {
                char key = w.name().charAt(0);
                map.put(key, new GameAction(key, "Use " + w, this::useWeapon));
            }
        }
        map.put('F', new GameAction('F', "Find loot", this::findLoot));

        if (pirate.hasExperiences()) {
            map.put('X', new GameAction('X', "Experience feature", this::experienceFeature));
        }

        map.putAll(getStandardActions());
        return map;
    }

    private static void loadLevelMap() {
        // Level 1 towns
        levelMap.add(new ArrayList<Town>(List.of(
            new Town("Bridgetown", "Barbados", 0),
            new Town("Fitts Village", "Barbados", 0),
            new Town("Holetown", "Barbados", 0)

        )));

        // Level 2 towns
        levelMap.add(new ArrayList<Town>(List.of(
            new Town("Forte-de-France", "Martinique", 1),
            new Town("Sainte-Anne", "Martinique", 1),
            new Town("Le Vauclin", "Martinique", 1)
        )));
    }

    public static List<Town> getTowns(int level) {
        if (level <= (levelMap.size() - 1)) {
            return levelMap.get(level);
        }
        return null;
    }

    private boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon();
    }

    @Override
    public boolean executeGameAction(int player, GameAction action) {
        getPlayer(player).setCurrentWeapon(Weapon.getWeaponByChar(action.key()));
        return super.executeGameAction(player, action);
    }

    @Override
    public boolean printPlayer(int playerIndex) {
        System.out.println(getPlayer(playerIndex).information());
        return false;
    }

    private boolean findLoot(int playerIndex) {
        return getPlayer(playerIndex).findLoot();
    }

    private boolean experienceFeature(int playerIndex) {
        return getPlayer(playerIndex).experienceFeature();
    }
}
