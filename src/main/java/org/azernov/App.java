package org.azernov;

import org.azernov.exceptions.NoSuchElementException;
import org.azernov.model.Element;
import org.azernov.service.ElementMatrix;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Введите значение x: ");
            int x = in.nextInt();
            System.out.print("Введите значение y: ");
            int y = in.nextInt();
            Element[] nearbyElements = new ElementMatrix().getNearbyElements(new Element(x,y));
            System.out.println(Arrays.toString(nearbyElements));
        } catch (InputMismatchException e) {
            System.out.println("Введено неверное значение, необходимо ввести целое число!");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
