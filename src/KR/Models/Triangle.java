package KR.Models;

import KR.Math.Vector3;
import KR.ThirdDimention.PolyLine3D;
import KR.ThirdDimention.iModel;

import java.util.Arrays;
import java.util.List;

public class Triangle implements iModel {
    private Vector3 p1, p2, p3;

    public Triangle(Vector3 p1, Vector3 p2, Vector3 p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    Triangle tr;

    public Vector3 getLengths(){
        double ab = Math.sqrt((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p2.getY() - p1.getY()) * (p2.getY() - p1.getY()) + (p2.getZ() - p1.getZ()) * (p2.getZ() - p1.getZ()));
        double bc = Math.sqrt((p3.getX() - p2.getX()) * (p3.getX() - p2.getX()) + (p3.getY() - p2.getY()) * (p3.getY() - p2.getY()) + (p3.getZ() - p2.getZ()) * (p3.getZ() - p2.getZ()));
        double ac = Math.sqrt((p3.getX() - p1.getX()) * (p3.getX() - p1.getX()) + (p3.getY() - p1.getY()) * (p3.getY() - p1.getY()) + (p3.getZ() - p1.getZ()) * (p3.getZ() - p1.getZ()));
        return new Vector3(ab, bc, ac);
    }

    public double getPerimetr(){
        Vector3 length = getLengths();
        return (length.getX() + length.getY() + length.getZ())/2;
    }

    public boolean cheakSquare(Triangle tr, Vector3 v){
        Vector3 length = tr.getLengths();
        double p = tr.getPerimetr();
        double sqTr = Math.sqrt(p*(p-length.getX())*(p-length.getY())*(p-length.getZ()));

        Triangle tr1 = new Triangle(tr.getP1(), tr.getP2(), v);
        length = tr1.getLengths();
        p = tr1.getPerimetr();
        double sqTr1 = Math.sqrt(p*(p-length.getX())*(p-length.getY())*(p-length.getZ()));

        Triangle tr2 = new Triangle(tr.getP2(), tr.getP3(), v);
        length = tr2.getLengths();
        p = tr2.getPerimetr();
        double sqTr2 = Math.sqrt(p*(p-length.getX())*(p-length.getY())*(p-length.getZ()));

        Triangle tr3 = new Triangle(tr.getP1(), tr.getP3(), v);
        length = tr3.getLengths();
        p = tr3.getPerimetr();
        double sqTr3 = Math.sqrt(p*(p-length.getX())*(p-length.getY())*(p-length.getZ()));

        double sq = sqTr1 + sqTr2 + sqTr3;
        if (sq - sqTr < 1e-15){
            return true;
        }
        else return false;
    }


    @Override
    public List<PolyLine3D> getLines() {
        PolyLine3D pl = new PolyLine3D(Arrays.asList(new Vector3[]{p1,p2,p3}), true);
        return Arrays.asList(new PolyLine3D[]{pl});
    }

    public Vector3 getP1() {
        return p1;
    }

    public Vector3 getP2() {
        return p2;
    }

    public Vector3 getP3() {
        return p3;
    }
}
