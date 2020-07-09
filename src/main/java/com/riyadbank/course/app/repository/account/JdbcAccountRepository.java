package com.riyadbank.course.app.repository.account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
			ps.close();
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

	@Override
	public void updateAccount(Account account) {
		String sql = "UPDATE ACCOUNT set balance = ?, creditCardNumber = ?, date = ? WHERE id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setBigDecimal(1, account.getBalance());
			ps.setString(2, account.getCreditCardNumber());
			ps.setTimestamp(3, Timestamp.valueOf(account.getDate()));
			ps.setLong(4, account.getId());
			ps.executeUpdate();
			ps.close();
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}finally {
			
		}
	}

	@Override
	public long createAccount(Account newAccount) {
		String sql = "INSERT INTO ACCOUNT(balance,creditCardNumber,date,personId) VALUES(?,?,?,?)";		
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rsKey = ps.getGeneratedKeys();
			ps.close();
			if(rsKey.next()) {
				return rsKey.getLong("id");
			}else {
				throw new RuntimeException("NO KEY FOUND");
			}
		}catch(SQLException e){
			throw new RuntimeException("SQL exception in createAccount", e);
		}
				
	}

	@Override
	public Account findById(Long id) {
		String sql = "SELECT id,balance,creditCardNumber,date,personId FROM ACCOUNT WHERE id = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		Account account = null;
		try{
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				account = mapAccount(rs);
			}
			ps.close();
		}catch(SQLException e){
			throw new RuntimeException("SQL exception occurred finding all accounts", e);
		}
		
		return account;
	}

}
