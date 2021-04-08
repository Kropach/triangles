package KR;

import KR.Math.Flatness;
import KR.Math.Math_Utils;
import KR.Math.Matrix4;
import KR.Math.Vector3;
import KR.Models.Cube;
import KR.Models.Line3D;
import KR.Models.Triangle;
import KR.ThirdDimention.Camera;
import KR.ThirdDimention.Scene;
import KR.ThirdDimention.ScreenConverter;
import KR.ThirdDimention.ScreenPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener {

    private ScreenConverter sc;
    private Camera cam;
    private Scene scene;
    private Triangle tr1, tr2;
    private ArrayList<Vector3> vectors3;
    public DrawPanel(){
        super();
        sc = new ScreenConverter(-2,2,4,4,500,500);
        cam = new Camera();
        scene = new Scene();
//        tr1 = new Triangle(new Vector3(0, 0, 0), new Vector3(0, 1, 1), new Vector3(0.5, 0.5, 0.5));
//        tr2 = new Triangle(new Vector3(0.5, 0.7, 0.3), new Vector3(-0.2, 0.2, 0.7), new Vector3(0.5, 1, 0));

        Vector3 v1Tr1 = new Vector3(0, 0, 0);
        Vector3 v2Tr1 = new Vector3(0, 1, 1);
        Vector3 v3Tr1 = new Vector3(0.5, 0.5, 0.5);

        Vector3 v1Tr2 = new Vector3(0.5, 0.7, 0.3);
        Vector3 v2Tr2 = new Vector3(-0.2, 0.2, 0.7);
        Vector3 v3Tr2 = new Vector3(0.5, 1, 0);

        tr1 = new Triangle(v1Tr1, v2Tr1, v3Tr1);
        tr2 = new Triangle(v1Tr2, v2Tr2, v3Tr2);
        vectors3 = Math_Utils.getArrayPoints(tr1, tr2);
        scene.models.add(tr1);
        scene.models.add(tr2);
        if (!vectors3.isEmpty()) {
            if (vectors3.size() < 2) {
                Line3D l3 = new Line3D(vectors3.get(0), vectors3.get(0));
                scene.models.add(l3);
            } else{
                for (int i = 1; i < vectors3.size(); i++) {
                    Line3D l3 = new Line3D(vectors3.get(i), vectors3.get(i-1));
                    scene.models.add(l3);
                }
            }
        }
//        если треуголиники лежат в одной пл-сти, то линии пересечения это их ребра
//        Vector3 v1Tr1 = new Vector3(0, 0.3, 0.5);
//        Vector3 v2Tr1 = new Vector3(0, 1.3, 1.5);
//        Vector3 v3Tr1 = new Vector3(0, 1.7, 0.3);
//
//        Vector3 v1Tr2 = new Vector3(0, 0, 1);
//        Vector3 v2Tr2 = new Vector3(0, 2, 0.8);
//        Vector3 v3Tr2 = new Vector3(0, 0, 0);
//
//        tr1 = new Triangle(v1Tr1, v2Tr1, v3Tr1);
//        tr2 = new Triangle(v1Tr2, v2Tr2, v3Tr2);
//        vectors3 = getArrayPoints(tr1, tr2);
//        scene.models.add(tr1);
//        scene.models.add(tr2);
//        if (!vectors3.isEmpty()) {
//            if (vectors3.size() < 2) {
//                Line3D l3 = new Line3D(vectors3.get(0), vectors3.get(0));
//                scene.models.add(l3);
//                System.out.println("checksss");
//            } else{
//                for (int i = 1; i < vectors3.size(); i++) {
//                    Line3D l3 = new Line3D(vectors3.get(i), vectors3.get(i-1));
//                    scene.models.add(l3);
//                    System.out.println("check2222");
//                }
//            }
//        }

//        Flatness f1 = getK(tr1);
//        Flatness f2 = getK(tr2);
//        Vector3 p1 = getVector(f2, v2Tr1, v3Tr1);
//        Vector3 p2 = getVector(f1, v2Tr2, v3Tr2);
//        Line3D l3 = new Line3D(p1,p2);


//        scene.models.add(l3);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(scene.drawScene(cam,sc), 0,0, null);

    }

//    private ArrayList<Vector3> getArrayPoints(Triangle tr1, Triangle tr2){
//        vectors3 = new ArrayList<>();
//        Flatness f1 = Flatness.getK(tr1);
//        Flatness f2 = Flatness.getK(tr2);
//
//        Vector3 v1Tr1 = new Vector3(tr1.getP1().getX(), tr1.getP1().getY(), tr1.getP1().getZ());
//        Vector3 v2Tr1 = new Vector3(tr1.getP2().getX(), tr1.getP2().getY(), tr1.getP2().getZ());
//        Vector3 v3Tr1 = new Vector3(tr1.getP3().getX(), tr1.getP3().getY(), tr1.getP3().getZ());
//        Vector3 v1Tr2 = new Vector3(tr2.getP1().getX(), tr2.getP1().getY(), tr2.getP1().getZ());
//        Vector3 v2Tr2 = new Vector3(tr2.getP2().getX(), tr2.getP2().getY(), tr2.getP2().getZ());
//        Vector3 v3Tr2 = new Vector3(tr2.getP3().getX(), tr2.getP3().getY(), tr2.getP3().getZ());
//        Vector3 p1 = Vector3.getVector(f2, v2Tr1, v3Tr1);
//        if (tr1.cheakSquare(tr1, p1) && tr2.cheakSquare(tr2, p1)){
//            vectors3.add(p1);
////            System.out.println("check1");
//        }
//        p1 = Vector3.getVector(f2, v1Tr1, v3Tr1);
//        if (tr1.cheakSquare(tr1, p1) && tr2.cheakSquare(tr2, p1)){
//            vectors3.add(p1);
////            System.out.println("check2");
//        }
//        p1 = Vector3.getVector(f2, v1Tr1, v2Tr1);
//        if (tr1.cheakSquare(tr1, p1) && tr1.cheakSquare(tr2, p1)){
//            vectors3.add(p1);
////            System.out.println("check3");
//        }
//        p1 = Vector3.getVector(f1, v2Tr2, v3Tr2);
//        if (tr1.cheakSquare(/*2*/tr1, p1) && tr2.cheakSquare(tr2, p1)){
//            vectors3.add(p1);
////            System.out.println("check4");
//        }
//        p1 = Vector3.getVector(f1, v1Tr2, v3Tr2);
//        if (tr1.cheakSquare(/*2*/tr1, p1) && tr2.cheakSquare(tr2, p1)){
//            vectors3.add(p1);
////            System.out.println("check5");
//        }
//        p1 = Vector3.getVector(f1, v1Tr2, v2Tr2);
//        if (tr1.cheakSquare(/*2*/tr1, p1) && tr2.cheakSquare(tr2, p1)){
//            vectors3.add(p1);
////            System.out.println("check6");
//        }
//        return vectors3;
//    }


    private ScreenPoint last = null;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        last = new ScreenPoint(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        last = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint np = new ScreenPoint(e.getX(), e.getY());
        if (last != null){
            int dx = np.getI() - last.getI();
            int dy = np.getJ() - last.getJ();
            double a = dx * Math.PI/180;
            double b = dy * Math.PI/180;
            cam.rotate = Matrix4.rotate(-a,1).mul(Matrix4.rotate(-b,0).mul(cam.rotate));
        }
        last = np;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
