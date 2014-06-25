/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kurssi.ejb;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;



/**
 *
 * @author Oppi
 */
public class InterceptBean {
	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		System.out.println("Invokinhg method: "+ic.getMethod());
		long start = System.currentTimeMillis();
		Object out = ic.proceed();
		System.out.println("Time: " + (System.currentTimeMillis()-start));
		return out;
	}
}
