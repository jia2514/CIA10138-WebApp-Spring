package com.joyfulresort.member.model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("memberService")
public class MemberService {

	@Autowired
	MemberRepository repository;

	@Autowired
    private SessionFactory sessionFactory;
	
	public Member findByMemberPhone(String memberPhone) {
		
		return repository.findByMemberPhone(memberPhone);
	}
	
	
	
}
