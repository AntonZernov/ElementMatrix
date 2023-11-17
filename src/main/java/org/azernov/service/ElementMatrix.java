package org.azernov.service;

import org.azernov.constants.Constants;
import org.azernov.exceptions.NoSuchElementException;
import org.azernov.model.Element;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElementMatrix {
    private Element[][] matrix;
    public ElementMatrix() {
        init();
    }

    private void init() {
        matrix = new Element[10][10];
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                matrix[j-1][10-i] = new Element(j, i);
            }
        }
    }

    public Element[] getNearbyElements(Element element) throws NoSuchElementException {
        List<Element> elements = Stream.of(matrix).flatMap(Stream::of).collect(Collectors.toList());
        if (!elements.contains(element)) {
            throw new NoSuchElementException(Constants.NO_ELEMENT_FOUND);
        }
        return elements
                .stream()
                .filter(
                        item -> (item.getX() != element.getX() || item.getY() != element.getY()) &&
                                !(item.getX() > element.getX() + 1 || item.getX() < element.getX() - 1) &&
                                !(item.getY() > element.getY() + 1 || item.getY() < element.getY() - 1)
                )
                .toArray(Element[]::new);
    }
}
