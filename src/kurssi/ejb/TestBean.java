/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurssi.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author Oppi
 */
@Stateless
public class TestBean {
	@Resource(mappedName = "jdbc/sample")
	private DataSource ds;

	public void testJDBCAccess() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try ( Connection con = ds.getConnection() ) {
			ps = con.prepareStatement("SELECT * FROM MESSAGES");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("" + rs.getString(1));
			}
		} catch (SQLException ex) {
			Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}
}
