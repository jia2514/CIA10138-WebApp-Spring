package com.joyfulresort.roomorderitem.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.sql.Date;
import java.sql.Timestamp;

import org.apache.naming.java.javaURLContextFactory;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_RoomOrder;
import com.hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_RoomSchedule;
import com.joyfulresort.roomorder.model.RoomOrder;

@Service("roomOrderItemService")
public class RoomOrderItemService {

	@Autowired
	RoomOrderItemRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	public List<RoomOrderItem> getAll() {
		return repository.findAll();
	}

	public List<RoomOrderItem> getByRoomOrder(RoomOrder roomOrder){
		return repository.findByRoomOrder(roomOrder);
	}
	
	
	

	public void addRoomOrderItem(RoomOrderItem roomOrderItem) {
		repository.save(roomOrderItem);
	}

	public RoomOrderItem updateRoomOrderItem(RoomOrderItem roomOrderItem) {
		repository.save(roomOrderItem);
		return getOneRoomOrderItem(roomOrderItem.getRoomOrderItemId());
	}

	public RoomOrderItem getOneRoomOrderItem(Integer roomOrderItemId) {
		Optional<RoomOrderItem> optional = repository.findById(roomOrderItemId);
//		return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

}