package SortAlgorithm.HomeWork;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] pointsIn) {
        if (pointsIn == null) {
             throw new IllegalArgumentException();
        }

        for (int i = 0; i < pointsIn.length; i++) {
            if (pointsIn[i] == null) {
                throw new IllegalArgumentException();
            }
        }

        int len        = pointsIn.length;
        Point[] points = new Point[len];
        System.arraycopy(pointsIn, 0, points, 0, len);
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++)
            if (points[i - 1].compareTo(points[i]) == 0)
                throw new IllegalArgumentException();

        lineSegments = new ArrayList<>();
        int pLen = points.length;
        if (pLen < 4) return;

        for (int i = 0; i < pLen - 3; i++) {
            for (int j = i + 1; j < pLen - 2; j++) {
                double kab = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < pLen - 1; k++) {
                    double kac = points[i].slopeTo(points[k]);
                    if (kab != kac) continue;
                    for (int s = k + 1; s < pLen; s++) {
                        double kad = points[i].slopeTo(points[s]);
                        if (kab != kad) continue;

                        LineSegment lineSegment = new LineSegment(points[i], points[s]);
                        lineSegments.add(lineSegment);
                    }

                }
            }
        }
    }


    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[lineSegments.size()];
        for (int i = 0; i < lineSegments.size(); i++) {
            result[i] = lineSegments.get(i);
        }

        return result;
    }
}
