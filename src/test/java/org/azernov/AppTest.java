package org.azernov;

import org.azernov.constants.Constants;
import org.azernov.exceptions.NoSuchElementException;
import org.azernov.model.Element;
import org.azernov.service.ElementMatrix;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void getNearbyElementsCornerTest() throws NoSuchElementException {
        Element e = new Element(1,1);
        Element[] nearbyElements = new ElementMatrix().getNearbyElements(e);
        Assert.assertEquals(3, nearbyElements.length);
        List<Element> expectedElements = List.of(new Element(1,2), new Element(2,1), new Element(2,2));
        assertExpected(expectedElements, Arrays.asList(nearbyElements));
    }
    @Test
    public void getNearbyElementsCenterTest() throws NoSuchElementException {
        Element e = new Element(3,3);
        Element[] nearbyElements = new ElementMatrix().getNearbyElements(e);
        Assert.assertEquals(8, nearbyElements.length);
        List<Element> expectedElements = List.of(
                new Element(2,2),
                new Element(2,3),
                new Element(2,4),
                new Element(3,2),
                new Element(3,4),
                new Element(4,2),
                new Element(4,3),
                new Element(4,4)
        );
        assertExpected(expectedElements, Arrays.asList(nearbyElements));
    }
    @Test
    public void getNearbyElementsExceptionTest() {
        Exception exception = assertThrows(
                NoSuchElementException.class,
                () -> new ElementMatrix().getNearbyElements(new Element(20,10))
        );
        Assert.assertEquals(Constants.NO_ELEMENT_FOUND, exception.getMessage());
    }

    private void assertExpected(List<Element> expected, List<Element> actual) {
        for (Element e : expected) {
            assertTrue(actual.contains(e));
        }
    }

}
