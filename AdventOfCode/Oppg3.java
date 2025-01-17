import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class Oppg3 {
    public static void main(String[] args) {
        Scanner reader = null;
        File adventFile = new File("Oppg3.input");

        // Initialization of parameters:



        // 
        
        try{
            reader = new Scanner(adventFile);
            
            Direction[] dir1 = getDirections(reader.nextLine());
            Direction[] dir2 = getDirections(reader.nextLine());

            Line[] lines1 = makeLines(dir1);
            Line[] lines2 = makeLines(dir2);

            int closest = -1;

            for(int i1 = 0; i1 < lines1.length; i1++){
                for(int i2 = 0; i2 < lines2.length; i2++){
                    int cross = lines1[i1].cross(lines2[i2]);
                    if(cross >= 1){
                        out.println("New cross!: " + cross);
                        out.println(" l1:" + lines1[i1].toString());
                        out.println(" l2:" + lines2[i2].toString());
                        if(closest == -1){
                            closest = cross;
                        }else{
                            if(cross < closest){
                                closest = cross;
                            }
                        }
                    }
                }
            }

            out.println("Closest part1: " + closest);


            int closest2 = -1;

            for(int i1 = 0; i1 < lines1.length; i1++){
                for(int i2 = 0; i2 < lines2.length; i2++){
                    int cross = lines1[i1].cross(lines2[i2]);
                    if(cross >= 1){
                        int distance = getSignalDelay(lines1, lines2, dir1, dir2, i1, i2);
                        if(closest2 == -1){
                            closest2 = distance;
                        }else{
                            if(distance < closest2){
                                closest2 = distance;
                            }
                        }
                    }
                }
            }

            out.println("Closest part2: " + closest2);

        }catch(Exception e){
            out.println("General failure: " + e.toString());
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){
                    out.println("Failed to close file: " + e.toString());
                }
            }
        }
    }

    public static int getSignalDelay(Line[] l1, Line[] l2, Direction[] d1, Direction[] d2, int i1, int i2){
        int sum = 0;

        for(int i = 0 ; i < i1; i++){
            sum += d1[i].length;
        }
        for(int i = 0 ; i < i2; i++){
            sum += d2[i].length;
        }


        int cx = 0;
        int cy = 0;

        if(l1[i1].x1 == l1[i1].x2){
            cx = l1[i1].x1;
            cy = l2[i2].y1;
        }else{
            cx = l2[i2].x1;
            cy = l1[i1].y1;
        }

        sum += Math.abs(l1[i1].x1 - cx);
        sum += Math.abs(l1[i1].y1 - cy);
        sum += Math.abs(l2[i2].x1 - cx);
        sum += Math.abs(l2[i2].y1 - cy);


        return sum;
    }

    public static Direction[] getDirections(String s){
        String[] linepart = s.split(",");

        Direction[] dir = new Direction[linepart.length];

        for(int i = 0; i < linepart.length; i++){
            dir[i] = new Direction(linepart[i]);
        }

        return dir;
    }

    public static Line[] makeLines(Direction[] directions){
        int x = 0; // 1 = +, 3 = -
        int y = 0; // 0 = +, 2 = -

        Line[] lines = new Line[directions.length];

        
        for(int i = 0; i < directions.length; i++){
            int x2 = x;
            int y2 = y;

            switch(directions[i].direction){
                case 0:
                    x2 += directions[i].length;
                    break;
                case 1:
                    y2 += directions[i].length;
                    break;
                case 2:
                    x2 -= directions[i].length;
                    break;
                case 3:
                    y2 -= directions[i].length;
            }

            lines[i] = new Line(x, y, x2, y2);
            x = x2;
            y = y2;
        }

        return lines;
    }
}