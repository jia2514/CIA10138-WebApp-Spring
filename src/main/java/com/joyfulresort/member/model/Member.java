package com.joyfulresort.member.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.joyfulresort.roomorder.model.RoomOrder;


@Component
@Entity
@Table(name = "member")
public class Member {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id", updatable = false)
	private Integer memberId;
	
	@Column(name = "member_name")
	private String memberName;
	
	@Column(name = "member_account")
	private String memberAccount;
	
	@Column(name = "member_password")
	private String memberPassword;
	
	@Column(name = "member_email")
	private String memberEmail;
	
	@Column(name = "member_phone")
	private String memberPhone;
	
	@Column(name = "member_address")
	private String memberAddress;
	
	@Column(name = "member_state")
	private Byte memberState;
	
	@Column(name = "member_gender")
	private Boolean memberGender;
	
	@Column(name = "member_birthday")
	private Date memberBirthday;
	
	@Column(name = "member_img", columnDefinition = "longblob")
	private byte[] memberImg;
	
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<RoomOrder> roomOrder;

	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberAccount() {
		return memberAccount;
	}


	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}


	public String getMemberPassword() {
		return memberPassword;
	}


	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}


	public String getMemberPhone() {
		return memberPhone;
	}


	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}


	public String getMemberAddress() {
		return memberAddress;
	}


	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}


	public Byte getMemberState() {
		return memberState;
	}


	public void setMemberState(Byte memberState) {
		this.memberState = memberState;
	}


	public Boolean getMemberGender() {
		return memberGender;
	}


	public void setMemberGender(Boolean memberGender) {
		this.memberGender = memberGender;
	}


	public Date getMemberBirthday() {
		return memberBirthday;
	}


	public void setMemberBirthday(Date memberBirthday) {
		this.memberBirthday = memberBirthday;
	}


	public byte[] getMemberImg() {
		return memberImg;
	}


	public void setMemberImg(byte[] memberImg) {
		this.memberImg = memberImg;
	}


	public Set<RoomOrder> getRoomOrder() {
		return roomOrder;
	}


	public void setRoomOrder(Set<RoomOrder> roomOrder) {
		this.roomOrder = roomOrder;
	}


	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", memberAccount=" + memberAccount
				+ ", memberPassword=" + memberPassword + ", memberEmail=" + memberEmail + ", memberPhone=" + memberPhone
				+ ", memberAddress=" + memberAddress + ", memberState=" + memberState + ", memberGender=" + memberGender
				+ ", memberBirthday=" + memberBirthday + ", memberImg=" + Arrays.toString(memberImg) + ", roomOrder="
				+ roomOrder + "]";
	}
	
	
	
	
}
