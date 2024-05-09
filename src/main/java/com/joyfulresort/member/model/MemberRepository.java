package com.joyfulresort.member.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.joyfulresort.roomorder.model.RoomOrder;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	@Transactional
	@Query(value = "select * from member where member_phone =?1", nativeQuery = true)
	Member findByMemberPhone(String memberPhone);
	
}
