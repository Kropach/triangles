package KR.Math;

import KR.Models.Triangle;

import java.util.ArrayList;

public class Math_Utils {
    public static ArrayList<Vector3> getArrayPoints(Triangle tr1, Triangle tr2){
        ArrayList<Vector3> vectors3 = new ArrayList<>();
        Flatness f1 = new Flatness(tr1);//Flatness.getK(tr1);
        Flatness f2 = Flatness.getK(tr2);

        Vector3 v1Tr1 = new Vector3(tr1.getP1().getX(), tr1.getP1().getY(), tr1.getP1().getZ());
        Vector3 v2Tr1 = new Vector3(tr1.getP2().getX(), tr1.getP2().getY(), tr1.getP2().getZ());
        Vector3 v3Tr1 = new Vector3(tr1.getP3().getX(), tr1.getP3().getY(), tr1.getP3().getZ());
        Vector3 v1Tr2 = new Vector3(tr2.getP1().getX(), tr2.getP1().getY(), tr2.getP1().getZ());
        Vector3 v2Tr2 = new Vector3(tr2.getP2().getX(), tr2.getP2().getY(), tr2.getP2().getZ());
        Vector3 v3Tr2 = new Vector3(tr2.getP3().getX(), tr2.getP3().getY(), tr2.getP3().getZ());
        Vector3 p1 = f2.getVector(v2Tr1, v3Tr1);
        if (tr1.cheakSquare(tr1, p1) && tr2.cheakSquare(tr2, p1)){
            vectors3.add(p1);
//            System.out.println("check1");
        }
        p1 = f2.getVector(v1Tr1, v3Tr1);
        if (tr1.cheakSquare(tr1, p1) && tr2.cheakSquare(tr2, p1)){
            vectors3.add(p1);
//            System.out.println("check2");
        }
        p1 = f2.getVector(v1Tr1, v2Tr1);
        if (tr1.cheakSquare(tr1, p1) && tr1.cheakSquare(tr2, p1)){
            vectors3.add(p1);
//            System.out.println("check3");
        }
        p1 = f1.getVector(v2Tr2, v3Tr2);
        if (tr1.cheakSquare(/*2*/tr1, p1) && tr2.cheakSquare(tr2, p1)){
            vectors3.add(p1);
//            System.out.println("check4");
        }
        p1 = f1.getVector(v1Tr2, v3Tr2);
        if (tr1.cheakSquare(/*2*/tr1, p1) && tr2.cheakSquare(tr2, p1)){
            vectors3.add(p1);
//            System.out.println("check5");
        }
        p1 = f1.getVector(v1Tr2, v2Tr2);
        if (tr1.cheakSquare(/*2*/tr1, p1) && tr2.cheakSquare(tr2, p1)){
            vectors3.add(p1);
//            System.out.println("check6");
        }
        return vectors3;
    }
}
