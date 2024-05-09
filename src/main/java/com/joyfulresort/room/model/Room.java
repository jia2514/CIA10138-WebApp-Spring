package com.joyfulresort.room.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joyfulresort.roomorderitem.model.RoomOrderItem;
import com.joyfulresort.roomtype.model.RoomType;

@Component
@Entity
@Table(name = "room")
public class Room {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id", updatable = false)
	private Integer roomId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id")
	private RoomType roomType;
	
	@Column(name = "room_sale_state")
	private Boolean roomSaleState;
	
	@Column(name = "room_state")
	private Byte roomState;
	
	@JsonIgnore
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	@OrderBy("room_order_item_id asc")
	private Set<RoomOrderItem> roomOrderItem;

	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public Boolean getRoomSaleState() {
		return roomSaleState;
	}

	public void setRoomSaleState(Boolean roomSaleState) {
		this.roomSaleState = roomSaleState;
	}

	public Byte getRoomState() {
		return roomState;
	}

	public void setRoomState(Byte roomState) {
		this.roomState = roomState;
	}

	public Set<RoomOrderItem> getRoomOrderItem() {
		return roomOrderItem;
	}

	public void setRoomOrderItem(Set<RoomOrderItem> roomOrderItem) {
		this.roomOrderItem = roomOrderItem;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomSaleState=" + roomSaleState + ", roomState=" + roomState
				+ ", roomOrderItem=" + roomOrderItem + "]";
	}
	
}	