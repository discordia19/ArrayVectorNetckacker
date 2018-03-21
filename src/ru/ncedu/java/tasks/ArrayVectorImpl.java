package ru.ncedu.java.tasks;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector {
    private double[] elements;

    public ArrayVectorImpl() {
        this.elements = null;
    }

    @Override
    public void set(double... elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    @Override
    public double[] get() {
        return this.elements;
    }

    @Override
    public ArrayVector clone() {
        ArrayVector cloneVector = new ArrayVectorImpl();
        cloneVector.set(this.elements);
        return cloneVector;
    }

    @Override
    public int getSize() {
        return this.elements.length;
    }

    @Override
    public void set(int index, double value) {
        if (index < 0) {
            return;
        } else if (index >= this.getSize()) {
            this.elements = Arrays.copyOf(this.elements, index + 1);
            this.elements[index] = value;
        } else {
            this.elements[index] = value;
        }
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        if (((index >= 0) && (index <= this.elements.length))) {
            return this.elements[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public double getMax() {
        double maxElement = this.get(0);
        for (double element :
                this.get()) {
            if (element > maxElement) {
                maxElement = element;
            }
        }
        return maxElement;
    }

    @Override
    public double getMin() {
        double minElement = this.get(0);
        for (double element :
                this.get()) {
            if (element < minElement) {
                minElement = element;
            }
        }
        return minElement;
    }

    @Override
    public void sortAscending() {
        Arrays.sort(this.elements, 0, this.elements.length);
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < this.elements.length; i++) {
            this.elements[i] *= factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        if (this.getSize() >= anotherVector.getSize()) {
            for (int i = 0; i < anotherVector.getSize(); i++) {
                this.set(i, anotherVector.get(i) + this.get(i));
                // this.elements[i] += anotherVector.get(i);
            }
        } else {
            double[] backupArray = Arrays.copyOf(this.get(), this.getSize());
            this.set(anotherVector.get());

            for (int i = 0; i < backupArray.length; i++) {
                this.set(i, this.get(i) + backupArray[i]);
            }
        }
        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double scalarMultiplication = 0;
        int shortestVectorSize = (this.getSize() <= anotherVector.getSize()) ?
                this.getSize() : anotherVector.getSize();

        for (int i = 0; i < shortestVectorSize; i++) {
            scalarMultiplication += this.get(i) * anotherVector.get(i);
        }

        return scalarMultiplication;
    }

    @Override
    public double getNorm() {
        return Math.sqrt(this.scalarMult(this));
    }

    public static void main(String[] args) {
        ArrayVector vector = new ArrayVectorImpl();
        double[] testArray = {-1, 2, 3};
        vector.set(testArray);

//        vector.set(6, 10);
//        System.out.println(Arrays.toString(vector.get()));
////
//        vector.set(7,20);
//        System.out.println(Arrays.toString(vector.get()));
//
//        vector.set(-1,20);
//        System.out.println(Arrays.toString(vector.get()));
        System.out.println("scalar mult: " + vector.scalarMult(vector));

        System.out.println("scalar mult: " + vector.getNorm());
    }
}


