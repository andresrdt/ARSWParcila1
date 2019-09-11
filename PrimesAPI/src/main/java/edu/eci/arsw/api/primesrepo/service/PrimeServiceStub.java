package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Santiago Carrillo 2/22/18.
 */
//@Service
public class PrimeServiceStub implements PrimeService {
    private List<FoundPrime> list;
    public PrimeServiceStub() {
        FoundPrime fp=new FoundPrime();
        fp.setPrime("32416190071");
        fp.setUser("john");
        list.add(fp);
    }
   
    @Override
    public void addFoundPrime(FoundPrime foundPrime) {
        list.add(foundPrime);
    }

    @Override
    public List<FoundPrime> getFoundPrimes() {
       return list;
    }

    @Override
    public FoundPrime getPrime(String prime) {
        //TODO
        for (FoundPrime fp:list){
            if(fp.getPrime().equals(prime)){
                return fp;
            }
        }
        return null;
    }
}
