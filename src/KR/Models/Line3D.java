package KR.Models;

import KR.Math.Vector3;
import KR.ThirdDimention.PolyLine3D;
import KR.ThirdDimention.iModel;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class Line3D implements iModel {
    private Vector3 p1, p2;

    public Line3D(Vector3 p1, Vector3 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public List<PolyLine3D> getLines() {
        PolyLine3D pl = new PolyLine3D(Arrays.asList(new Vector3[]{p1,p2}), false);
        return Arrays.asList(new PolyLine3D[]{pl});
    }
}
