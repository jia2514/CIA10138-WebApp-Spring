package com.joyfulresort.roomtype.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import java.sql.Timestamp;

import org.apache.naming.java.javaURLContextFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_RoomOrder;
import com.joyfulresort.roomorder.model.RoomOrder;

@Service("roomTypeService")
public class RoomTypeService {

	@Autowired
	RoomTypeRepository repository;

	@Autowired
    private SessionFactory sessionFactory;
	
	
	public List<RoomType> getAll() {
		return repository.findAll();
	}

	public RoomType getOneRoomType(Integer roomTypeId) {
		Optional<RoomType> optional = repository.findById(roomTypeId);
//		return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	

	
	
	
	
	
}