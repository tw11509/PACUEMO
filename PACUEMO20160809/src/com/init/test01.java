package com.init;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public class test01 {
	
	
	
	public static void main(String[] args) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException{
		
		
		  long truncatedHash = 0;

		  byte[] hash = {0x50,(byte) 0xef,0x7f,0x19};
		  
          for (int i = 0; i < 4; ++i)
          {
              truncatedHash <<= 8;

              System.out.println(truncatedHash);
              System.out.println(Long.toBinaryString(truncatedHash));
              
              // Java bytes are signed but we need an unsigned integer:
              // cleaning off all but the LSB.
              truncatedHash |= (hash[i] & 0xff);
              //                                 1010000111011110111111100011001
              //1111111111111111111111111111111111111111111011110111111100011001
              
              
              
          }
          System.out.println(Long.toBinaryString(truncatedHash));
          
          truncatedHash &= 0x7FFFFFFF;
          System.out.println(truncatedHash);
          truncatedHash %= Math.pow(10, 6);
          System.out.println(truncatedHash);
          
          
        

	}
	 
	 
	
}