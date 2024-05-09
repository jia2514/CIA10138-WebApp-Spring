package com.joyfulresort.room.model;

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

@Service("roomService")
public class RoomService {

	@Autowired
	RoomRepository repository;

	@Autowired
    private SessionFactory sessionFactory;
	
	
	public List<Room> getAll() {
		return repository.findAll();
	}

	public Room getOneRoom(Integer roomId) {
		Optional<Room> optional = repository.findById(roomId);
//		return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public Room checkoutRoom(Integer roomId) {
		Room room = getOneRoom(roomId);      
        	room.setRoomState((byte)2);
            return repository.saveAndFlush(room);
        
	}
	
	
	
	public Room cleanRoom(Integer roomId) {
		Room room = getOneRoom(roomId);      
    	room.setRoomState((byte)0);
        return repository.saveAndFlush(room);
	}

	
	
	
	
	
}