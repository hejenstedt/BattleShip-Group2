/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maventry.EjbPro;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.Basic;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;

import model.EmployInfo;

/**
 *
 * @author martin
 */
@Singleton
@LocalBean
@Startup
public class NewSessionBean {
	@PersistenceContext(name="EjbPro")
	   EntityManager em;
	@PostConstruct
	public void loadMethod() {
	   
	 
	   
	   EmployInfo emp = new EmployInfo();
	   emp.setName("kala choor");
	   emp.setDesignation("sardar");
	   emp.setPassword("KALA");
	   emp.setUserName("CHOOR ");
	   emp.setPic(imageToByte("/Users/amirashfaq/Desktop/nawazshreef.jpg"));
	   emp.setStart_Date(new Date());
	   
	   em.persist(emp);//@Basic(fetch=FetchType.LAZY)
	   
	   
	   System.out.println("DONE DONE DONEndn");
	   
	   
	   
	   
	   
	   
	         
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
    }
   
   public byte[] imageToByte(String Path)
   {
       FileInputStream inputStream;
       ByteArrayOutputStream buffer = null;
	try {
			inputStream = new FileInputStream(Path);
		    buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}
			buffer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return buffer.toByteArray();
   } 

   

}
