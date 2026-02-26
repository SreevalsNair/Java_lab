class VectorException extends RuntimeException {
    public VectorException(String message) {
        super(message);
    }
}

public class Vector {
    private double[] array;

    public Vector(double[] array) {
        if (array.length != 2 && array.length != 3) {
            throw new VectorException("Vector must be 2D or 3D");
        }
        this.array = array;
    }

    private void checkDimensions(Vector other) {
        if (this.array.length != other.array.length) {
            throw new VectorException("Vectors must have the same dimensions");
        }
    }

    public Vector add(Vector other) {
        checkDimensions(other);

        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = this.array[i] + other.array[i];
        }

        return new Vector(result);
    }

    public Vector subtract(Vector other) {
    checkDimensions(other); 

    double[] result = new double[array.length];
    for (int i = 0; i < array.length; i++) {
        result[i] = this.array[i] - other.array[i];
    }

    return new Vector(result);
}
    public double dot(Vector other) {
        checkDimensions(other);

        double product = 0;
        for (int i = 0; i < array.length; i++) {
            product += this.array[i] * other.array[i];
        }

        return product;
    }

    public void display() {
        System.out.print("(");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println(")");
    }



}