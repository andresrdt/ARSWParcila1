/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;

/**
 *
 * @author 2112076
 */
public class FinderThread extends Thread {

    private BigInteger a;
    private BigInteger b;
    private MathUtilities mt;
    private PrimesResultSet prs;
    private boolean pause = false;

    @Override
    public void run() {
        int itCount = 0;

        BigInteger i = a;
        while (i.compareTo(b) <= 0) {
            if (pause) {

            } else {
                itCount++;
                if (mt.isPrime(i)) {
                    prs.addPrime(i);
                    System.out.println("prime is " + i);
                }
                i = i.add(BigInteger.ONE);

            }
        }
    }

    public void changePaused() {
        pause = true;
    }

    public boolean isPaused() {
        return pause;
    }

    public void LinkPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prs) {

        a = _a;
        b = _b;

        mt = new MathUtilities();

        this.prs = prs;
    }

    public void notifyDes() {
        pause = false;
    }
}
