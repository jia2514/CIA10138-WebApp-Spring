package com.joyfulresort.roomtype.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joyfulresort.room.model.Room;
import com.joyfulresort.roomorderitem.model.RoomOrderItem;
import com.joyfulresort.roomschedule.model.RoomSchedule;
import com.joyfulresort.roomtypephoto.model.RoomTypePhoto;

@Component
@Entity
@Table(name = "room_type")
public class RoomType {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_type_id", updatable = false)
	private Integer roomTypeId;
	
	@Column(name = "room_type_name")
	private String roomTypeName;
	
	@Column(name = "room_type_content")
	private String roomTypeContent;
	
	@Column(name = "room_type_sale_state")
	private Boolean roomTypeSaleState;
	
	@Column(name = "room_type_price")
	private Integer roomTypePrice;
	
	
	@OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
	@OrderBy("room_id asc")
	private Set<Room> room;
	
	@JsonIgnore
	@OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
	@OrderBy("room_order_item_id asc")
	private Set<RoomOrderItem> roomOrderItems;
	
	
	@OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
	@OrderBy("room_type_photo_id asc")
	private Set<RoomTypePhoto> roomTypePhotos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
	@OrderBy("room_schedule_id asc")
	private Set<RoomSchedule> roomSchedules;

	
	public RoomType() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getRoomTypeContent() {
		return roomTypeContent;
	}

	public void setRoomTypeContent(String roomTypeContent) {
		this.roomTypeContent = roomTypeContent;
	}

	public Boolean getRoomTypeSaleState() {
		return roomTypeSaleState;
	}

	public void setRoomTypeSaleState(Boolean roomTypeSaleState) {
		this.roomTypeSaleState = roomTypeSaleState;
	}

	public Integer getRoomTypePrice() {
		return roomTypePrice;
	}

	public void setRoomTypePrice(Integer roomTypePrice) {
		this.roomTypePrice = roomTypePrice;
	}

	public Set<Room> getRoom() {
		return room;
	}

	public void setRoom(Set<Room> room) {
		this.room = room;
	}

	public Set<RoomOrderItem> getroomOrderItems() {
		return roomOrderItems;
	}

	public void setRoomOrderItem(Set<RoomOrderItem> roomOrderItems) {
		this.roomOrderItems = roomOrderItems;
	}

	public Set<RoomTypePhoto> getRoomTypePhotos() {
		return roomTypePhotos;
	}

	public void setRoomTypePhoto(Set<RoomTypePhoto> roomTypePhotos) {
		this.roomTypePhotos = roomTypePhotos;
	}

	public Set<RoomSchedule> getRoomSchedules() {
		return roomSchedules;
	}

	public void setRoomSchedules(Set<RoomSchedule> roomSchedules) {
		this.roomSchedules = roomSchedules;
	}

	@Override
	public String toString() {
		return "RoomType [roomTypeId=" + roomTypeId + ", roomTypeName=" + roomTypeName + ", roomTypeContent="
				+ roomTypeContent + ", roomTypeSaleState=" + roomTypeSaleState + ", roomTypePrice=" + roomTypePrice
				+ ", room=" + room + ", roomOrderItems=" + roomOrderItems 
//				+ ", roomTypePhotos=" + roomTypePhotos
				+ ", roomSchedules=" + roomSchedules + "]";
	}
	
	
	
	
}
