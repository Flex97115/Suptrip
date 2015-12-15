package com.supinfo.suptrip.entity;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public User( int idBooster, String password,String firstName,String lastName, String email, Campus campus ){
		this.idBooster = idBooster;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.campus = campus;
		
	}
	
	public User() {
	}

	@Id
	private int idBooster;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	
	@OneToOne
	@JoinColumn(name="campus_fk")
	private Campus campus;
	
	public Collection<Bag> getBags() {
		return bags;
	}

	public void setBags(Collection<Bag> bags) {
		this.bags = bags;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy="user",  cascade = CascadeType.ALL)
	private Collection<Bag> bags;
	
	public int getIdBooster() {
		return idBooster;
	}

	public void setIdBooster(int idBooster) {
		this.idBooster = idBooster;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

		
}
