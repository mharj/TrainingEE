/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kurssi.ejb;

import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author Oppi
 */
@Stateless
public class TestTimerSessionBean {
	@Schedule(minute = "*", second = "0", dayOfMonth = "*", month = "*", year = "*", hour = "*", dayOfWeek = "*", persistent = false)
	public void myTimer() {
		System.out.println("Greetings! Timer event: " + new Date());
	}
}
