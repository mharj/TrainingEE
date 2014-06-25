/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kurssi.ejb;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author Oppi
 */
@Stateless
@Interceptors ({InterceptBean.class})
public class TestUsedBean {
	public void doRun() {
	}
}
