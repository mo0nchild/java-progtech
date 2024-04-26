package application.domen.unittests;

import application.domen.models.MPolygon;
import application.domen.testsystem.TestClass;
import application.domen.testsystem.infrastructure.TestMethod;
import javafx.geometry.Point2D;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PolygonTest extends TestClass {
    @TestMethod(name = "Точка внутри квадрата")
    public void squareContainsTestInside() {
        var polygon = new MPolygon() {{
            setHeight(5);
            setWidth(5);
            setKpoint(4);
            setPoints(new Point2D[] {
                    new Point2D(0, 0),
                    new Point2D(0, 5),
                    new Point2D(5, 5),
                    new Point2D(5, 0),
            });
        }};
        this.logInfo(polygon.toString());
        this.assertThat(polygon.contains(2, 2), true);
    }
    @TestMethod(name = "Точка снаружи квадрата")
    public void squareContainsTestOutside() {
        var polygon = new MPolygon() {{
            setHeight(5);
            setWidth(5);
            setKpoint(4);
            setPoints(new Point2D[] {
                    new Point2D(0, 0),
                    new Point2D(0, 5),
                    new Point2D(5, 5),
                    new Point2D(5, 0)
            });
        }};
        this.logInfo(polygon.toString());
        this.assertIfFalse(!polygon.contains(6, 6));
    }
    @TestMethod(name = "Точка внутри треугольника")
    public void triangleContainsTestInside() {
        var polygon = new MPolygon() {{
            setHeight(10);
            setWidth(10);
            setKpoint(3);
            setPoints(new Point2D[] {
                    new Point2D(0, 0),
                    new Point2D(0, 10),
                    new Point2D(10, 10),
            });
        }};
        this.logInfo(polygon.toString());
        this.assertIfFalse(polygon.contains(3, 6));
    }
    @TestMethod(name = "Точка снаружи треугольника")
    public void triangleContainsTestOutside() {
        var polygon = new MPolygon() {{
            setHeight(10);
            setWidth(10);
            setKpoint(3);
            setPoints(new Point2D[] {
                    new Point2D(0, 10),
                    new Point2D(10, 10),
                    new Point2D(10, 0),
            });
        }};
        this.logInfo(polygon.toString());
        this.assertThat(polygon.contains(100, 6), false);
    }
}
