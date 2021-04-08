package KR.Math;

import KR.Models.Triangle;

public class Flatness {
    private double a, b, c, d;

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public Flatness(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public Flatness(Triangle tr) {
        Vector3 m1 = new Vector3(tr.getP1().getX(),tr.getP1().getY(),tr.getP1().getZ());
        Vector3 m2 = new Vector3(tr.getP2().getX(),tr.getP2().getY(),tr.getP2().getZ());
        Vector3 m3 = new Vector3(tr.getP3().getX(),tr.getP3().getY(),tr.getP3().getZ());
        a = ((m2.getY() - m1.getY())*(m3.getZ()-m1.getZ()) - (m3.getY()-m1.getY())*(m2.getZ()-m1.getZ()));
        b = -((m2.getX() - m1.getX())*(m3.getZ()-m1.getZ()) - (m2.getZ()-m1.getZ())*(m3.getX()-m1.getX()));
        c = ((m2.getX() - m1.getX())*(m3.getY()-m1.getY()) - (m2.getY()-m1.getY())*(m3.getX()-m1.getX()));
        d = -(a*m1.getX() + b*m1.getY() + c*m1.getZ());
//        System.out.println(a + " " + b + " " + c + " " + d + " ");

    }

    public static Flatness getK(Triangle tr){
        Vector3 m1 = new Vector3(tr.getP1().getX(),tr.getP1().getY(),tr.getP1().getZ());
        Vector3 m2 = new Vector3(tr.getP2().getX(),tr.getP2().getY(),tr.getP2().getZ());
        Vector3 m3 = new Vector3(tr.getP3().getX(),tr.getP3().getY(),tr.getP3().getZ());
        double a = ((m2.getY() - m1.getY())*(m3.getZ()-m1.getZ()) - (m3.getY()-m1.getY())*(m2.getZ()-m1.getZ()));
        double b = -((m2.getX() - m1.getX())*(m3.getZ()-m1.getZ()) - (m2.getZ()-m1.getZ())*(m3.getX()-m1.getX()));
        double c = ((m2.getX() - m1.getX())*(m3.getY()-m1.getY()) - (m2.getY()-m1.getY())*(m3.getX()-m1.getX()));
        double d = -(a*m1.getX() + b*m1.getY() + c*m1.getZ());
//        System.out.println(a + " " + b + " " + c + " " + d + " ");
        return new Flatness(a,b,c,d);
    }
    public Vector3 getVector(Vector3 v1, Vector3 v2){
        Flatness f = this;
        Vector3 C = new Vector3((v2.getX()-v1.getX()), (v2.getY()-v1.getY()), (v2.getZ()-v1.getZ()));
        double t = -(f.getA()*v1.getX() + f.getB()*v1.getY() + f.getC()*v1.getZ() + f.getD())/(f.getA()*C.getX()+f.getB()*C.getY()+f.getC()*C.getZ());
//        System.out.println((v1.getX()+C.getX()*t)+","+(v1.getY()+C.getY()*t)+","+(v1.getZ()+C.getZ()*t));
        return new Vector3((v1.getX()+C.getX()*t),(v1.getY()+C.getY()*t),(v1.getZ()+C.getZ()*t));
    }
}
