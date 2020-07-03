package com.riyadbank.course.app.repository.account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.riyadbank.course.app.model.Account;

@Repository
public class JdbcAccountRepository implements IAccountRepository{
	
	private DataSource dataSource;
	
	public JdbcAccountRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Account> findAll() {
		String sql = "SELECT id,balance,creditCardNumber,date,personId FROM ACCOUNT";
		List<Account> list;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		Account account = null;
		try{
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			list = new LinkedList<Account>();
			while(rs.next()) {
				account = mapAccount(rs);
				list.add(account);
			}
			
		}catch(SQLException e){
			throw new RuntimeException("SQL exception occurred finding all accounts", e);
		}
		
		return list;
	}
	
	private Account mapAccount(ResultSet rs) throws SQLException {
		BigDecimal balance = rs.getBigDecimal("balance");
		String creditCardNumber = rs.getString("creditCardNumber");
		LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
		long id = rs.getLong("id");
		long personId = rs.getLong("id");
		
		Account account = new Account(id,balance,creditCardNumber,date, personId);
		return account;
	}

}
