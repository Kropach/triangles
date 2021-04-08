package KR.ThirdDimention;

import KR.Math.Vector3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Scene {
    public List<iModel> models = new LinkedList<>();
    public BufferedImage drawScene(Camera cam, ScreenConverter sc){
        BufferedImage bi = new BufferedImage(sc.getWs(), sc.getHs(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)bi.getGraphics();
        /**/
        List<PolyLine3D> lines = new ArrayList<>();
        for (iModel m:
                models) {
            for (PolyLine3D pl :
                    m.getLines()) {
                List<Vector3> points = new LinkedList<>();
                for (Vector3 v :
                        pl.getPoints()) {
                    points.add(cam.w2c(v));
                }
                lines.add(new PolyLine3D(points, pl.isClosed()));
            }
        }

        lines.sort(new Comparator<PolyLine3D>() {
            @Override
            public int compare(PolyLine3D o1, PolyLine3D o2) {
                return (int)Math.signum(o1.avgZ() - o2.avgZ());
            }
        });
        g.setColor(Color.WHITE);
        g.fillRect(0,0,bi.getWidth(),bi.getHeight());
        g.setColor(Color.BLACK);
        for (PolyLine3D pl :
                lines) {
            ScreenPoint last = sc.r2s(pl.getPoints().get(0));
            for (int i = 1; i < pl.getPoints().size(); i++) {
                ScreenPoint np = sc.r2s(pl.getPoints().get(i));
                g.drawLine(last.getI(), last.getJ(), np.getI(), np.getJ());
                last = np;
            }
            if (pl.isClosed()){
                ScreenPoint np = sc.r2s(pl.getPoints().get(0));
                g.drawLine(last.getI(), last.getJ(), np.getI(), np.getJ());
            }
        }
//            int[] xx = new int[pl.getPoints().size()];
//            int[] yy = new int[pl.getPoints().size()];
//            int i = 0;
//            for (Vector3 v :
//                    pl.getPoints()) {
//                ScreenPoint sp = sc.r2s(v);
//                xx[i]= sp.getI();
//                yy[i]=sp.getJ();
//                i++;
//            }
//            if (pl.isClosed()) {
//                g.setColor(new Color(100,100,255));
//                g.fillPolygon(xx, yy, xx.length);
//            }
//            g.setColor(Color.BLACK);
//            for (int j = 1; j < xx.length; j++) {
//                g.drawLine(xx[j-1],yy[j-1],xx[j],yy[j]);
//            }
//            if (pl.isClosed())
//                g.drawLine(xx[0],yy[0],xx[xx.length-1], yy[yy.length-1]);
//        }
//

//        }
        /**/
        g.dispose();
        return bi;
    }
}
