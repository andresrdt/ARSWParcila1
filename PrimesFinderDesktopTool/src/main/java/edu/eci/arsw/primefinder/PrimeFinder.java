package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import edu.eci.arsw.mouseutils.MouseMovementMonitorExample;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrimeFinder extends Thread {

    private static boolean pause = false;
    private static PrimesResultSet prs;

    public static void findPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prss) {
        PrimeFinder.prs = prss;
        BigInteger a = _a;
        BigInteger b = _b;
        MathUtilities mt = new MathUtilities();
        FinderThread ft1 = new FinderThread();
        FinderThread ft2 = new FinderThread();
        FinderThread ft3 = new FinderThread();
        FinderThread ft4 = new FinderThread();
        ft1.LinkPrimes(a, new BigInteger("2500"), prs);
        ft2.LinkPrimes(new BigInteger("2501"), new BigInteger("5000"), prs);
        ft3.LinkPrimes(new BigInteger("5001"), new BigInteger("7500"), prs);
        ft4.LinkPrimes(new BigInteger("7501"), new BigInteger("10000"), prs);
        ft1.start();
        ft2.start();
        ft3.start();
        ft4.start();
        while (ft1.isAlive()) {
            synchronized (prs) {
                try {
                    //check every 10ms if the idle status (10 seconds without mouse
                    //activity) was reached. 
                    Thread.sleep(1000);
                    if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() > 10000) {
                        System.out.println("Idle CPU ");
                        ft1.changePaused();
                        ft2.changePaused();
                        ft3.changePaused();
                        ft4.changePaused();
                    } else {
                        System.out.println("User working again!");
                        ft1.notifyDes();
                        ft2.notifyDes();
                        ft3.notifyDes();
                        ft4.notifyDes();

                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        /*int itCount=0;
            
                BigInteger i=a;
                while (i.compareTo(b)<=0){
                    itCount++;
                    if (mt.isPrime(i)){
                        prs.addPrime(i);
                    }

                    i=i.add(BigInteger.ONE);
                }*/

    }

}
