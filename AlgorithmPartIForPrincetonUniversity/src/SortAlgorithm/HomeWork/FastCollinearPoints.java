package SortAlgorithm.HomeWork;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;
    private ArrayList<Line> lines;
    private int countLine;

    public FastCollinearPoints(Point[] pointsIn) {
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

        for (int i = 0; i < len - 1; i++) {
            Line[] arrayLines = new Line[len - 1 - i];
            int sz            = 0;
            for (int j = i + 1; j < len; j++) {
                Line line        = new Line(points[i], points[j]);
                arrayLines[sz++] = line;
            }

            if (arrayLines.length < 3) break;
            Merge.sort(arrayLines);

            lines = new ArrayList<>();
            int pa = 0, pb = 1;
            while (pa < sz && pb < sz) {
                if (arrayLines[pa].slope == arrayLines[pb].slope) {
                    pb++;
                    if (pb == sz && (pb - pa) >= 3) {
                        Line tLine = new Line(arrayLines[pa].startPoint, arrayLines[pb-1].endPoint);
                        if (!isExist(arrayLines[pb-1].endPoint, tLine.slope)) {
                            lines.add(tLine);
                        }
                    }

                    continue;
                }

                if (pb - pa >= 3) {
                    Line tLine = new Line(arrayLines[pa].startPoint, arrayLines[pb-1].endPoint);
                    if (!isExist(arrayLines[pb-1].endPoint, tLine.slope)) {
                        lines.add(tLine);
                    }

                    pa = pb;
                    pb++;
                } else {
                    pa = pb;
                    pb++;
                }
            }

            countLine   = lines.size();
            lineSegments = new ArrayList<>();
            for (Line line: lines) {
                LineSegment segment = new LineSegment(line.startPoint, line.endPoint);
                lineSegments.add(segment);
            }
        }
    }

    private boolean isExist(Point end, double slope) {
        for (Line line: lines) {
            if (end.compareTo(line.endPoint) == 0 && Double.compare(slope, line.slope) == 0) return true;
        }

        return false;
    }

    public int numberOfSegments() {
        return countLine;
    }

    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[countLine];
        int i                = 0;
        for (LineSegment lineSegment: lineSegments) {
            result[i++] = lineSegment;
        }

        return result;
    }

    private static class Line implements Comparable<Line> {

        public final Point startPoint;
        public final Point endPoint;
        public double slope;

        public Line(Point s, Point e) {
            startPoint = s;
            endPoint   = e;
            slope      = s.slopeTo(e);
        }

        @Override
        public int compareTo(Line other) {
            return Double.compare(this.slope, other.slope);
        }

        @Override
        public String toString() {
            return startPoint + "->" + endPoint;
        }
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
