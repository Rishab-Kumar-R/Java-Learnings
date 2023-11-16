package com.rishab.generics;

interface Player {
    String name();
}

record BaseballPlayer(String name, String position) implements Player {
}

record FootballPlayer(String name, String position) implements Player {
}

record VolleyballPlayer(String name, String position) implements Player {
}

public class Main {
    public static void main(String[] args) {

        var red = new Affiliation("city", "Red", "US");
        BaseballTeam redSox1 = new BaseballTeam("Red Sox");
        BaseballTeam uscTrojans1 = new BaseballTeam("USC Trojans");
        scoreResult(redSox1, 5, uscTrojans1, 3);

        SportsTeam redSox2 = new SportsTeam("Red Sox");
        SportsTeam uscTrojans2 = new SportsTeam("USC Trojans");
        scoreResult(redSox2, 5, uscTrojans2, 3);

        Team<BaseballPlayer, Affiliation> redSox = new Team<>("Red Sox", red);
        Team<BaseballPlayer, Affiliation> uscTrojans = new Team<>("USC Trojans");
        scoreResult(redSox, 5, uscTrojans, 3);

        var harper = new BaseballPlayer("B Harper", "Right Fielder");
        var marsh = new BaseballPlayer("B Marsh", "Pitcher");
        var fowler = new BaseballPlayer("D Fowler", "Center Fielder");
        redSox.addTeamMember(harper);
        redSox.addTeamMember(marsh);
        redSox.addTeamMember(fowler);
        redSox.listTeamMembers();

        SportsTeam afc1 = new SportsTeam("Adelaide Crows");
        Team<FootballPlayer, String> afc = new Team<>("Adelaide Crows", "City of Adelaide, Australia");
        var tex = new FootballPlayer("Tex", "Full Forward");
        var rory = new FootballPlayer("Rory Laird", "Midfielder");
        afc.addTeamMember(tex);
        afc.addTeamMember(rory);
        afc.listTeamMembers();

        Team<VolleyballPlayer, Affiliation> adelaide = new Team<>("Adelaide Strikers");
        adelaide.addTeamMember(new VolleyballPlayer("R Tugger", "Setter"));
        adelaide.listTeamMembers();

        var canberra = new Team<VolleyballPlayer, Affiliation>("Canberra Hawks");
        canberra.addTeamMember(new VolleyballPlayer("J Smith", "Libero"));
        canberra.listTeamMembers();
        scoreResult(canberra, 0, adelaide, 1);
    }

    public static void scoreResult(BaseballTeam team1, int t1_score, BaseballTeam team2, int t2_score) {
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);

        System.out.printf("%s %s %s%n", team1, message, team2);
    }

    public static void scoreResult(SportsTeam team1, int t1_score, SportsTeam team2, int t2_score) {
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);

        System.out.printf("%s %s %s%n", team1, message, team2);
    }

    public static void scoreResult(Team team1, int t1_score, Team team2, int t2_score) {
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);

        System.out.printf("%s %s %s%n", team1, message, team2);
    }
}
