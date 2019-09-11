package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import edu.eci.arsw.api.primesrepo.service.PrimeServiceStub;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
//@Service
public class PrimesController
{
    //@Autowired
    PrimeService primeService;


    @RequestMapping( value = "/primes", method = GET )
    public ResponseEntity<?> getPrimes()
    {
        try{
            synchronized(primeService){
                return new ResponseEntity<>(primeService.getFoundPrimes() ,HttpStatus.OK);
            }
        }catch(Exception ex){
            Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error no primes found", HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping( value = "/primes/{primenumber}", method = GET )
    public ResponseEntity<?> getPrimeBynumber(@PathVariable("primenumber") String prime)
    {
        try{
            synchronized(primeService){
                return new ResponseEntity<>(primeService.getPrime(prime),HttpStatus.OK);
            }
        }catch(Exception ex){
            Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error no prime found", HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(path = "primes",method = RequestMethod.POST)
    public ResponseEntity<?> postPrimes(@RequestBody FoundPrime fp)
    {
        try{
            synchronized(primeService){
                primeService.addFoundPrime(fp);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }catch(Exception ex){
            Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error primes", HttpStatus.NOT_FOUND);
        }
    }
    //TODO implement additional methods provided by PrimeService



}
