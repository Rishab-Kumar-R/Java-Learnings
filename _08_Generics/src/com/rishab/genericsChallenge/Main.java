package com.rishab.genericsChallenge;

public class Main {
    public static void main(String[] args) {

        var park = new Park[]{
            new Park("Yellowstone", "44.4280,-110.5885"),
            new Park("Yosemite", "37.8651,-119.5383"),
            new Park("Grand Canyon", "36.1070,-112.1130")
        };

        Layer<Park> parkLayer = new Layer<>(park);
        parkLayer.renderLayer();

        var river = new River[]{
            new River("Colorado", "37.8651,-119.5383", "36.1070,-112.1130"),
            new River("Rio Grande", "44.4280,-110.5885", "36.1070,-112.1130"),
            new River("Mississippi", "44.4280,-110.5885", "37.8651,-119.5383")
        };

        Layer<River> riverLayer = new Layer<>(river);
        riverLayer.addElements(
            new River("Missouri", "44.4280,-110.5885", "37.8651,-119.5383"),
            new River("Arkansas", "44.4280,-110.5885", "37.8651,-119.5383")
        );
        riverLayer.renderLayer();

    }
}
