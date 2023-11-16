package com.rishab.genericsChallenge;

import java.util.ArrayList;
import java.util.List;

public class Layer<T extends Mappable> {
    private List<T> layerElements;

    public Layer(T[] layerElements) {
        this.layerElements = new ArrayList<T>(List.of(layerElements));
    }

    public void addElements(T... elements) {
        this.layerElements.addAll(List.of(elements));
    }

    public void renderLayer() {
        for (T element : this.layerElements) {
            element.render();
        }
    }
}
