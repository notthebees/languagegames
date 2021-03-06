package agent.concept;

import conceptualspace.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleBayesianFactory implements RandomConceptFactory {

    private final Random random = new Random();

    public Concept randomConcept(final int numDimensions) {
        final List<Point> points = new ArrayList<Point>();
        points.add(randomPoint(numDimensions));
        points.add(randomPoint(numDimensions));
        return new BayesianConcept(new IndependentGaussianModel(points), points.size());
    }

    private Point randomPoint(final int numDimensions) {
        final List<Double> coordinates = new ArrayList<Double>();
        for (int i = 0; i < numDimensions; i++) {
            coordinates.add(random.nextDouble());
        }
        return new Point(coordinates);
    }

}
