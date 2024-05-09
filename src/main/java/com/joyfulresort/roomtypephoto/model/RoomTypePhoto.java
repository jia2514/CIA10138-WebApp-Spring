package com.joyfulresort.roomtypephoto.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joyfulresort.roomtype.model.RoomType;



@Entity
@Table(name = "room_type_photo")
public class RoomTypePhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_type_photo_id", updatable = false)
	private Integer roomTypePhotoId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id")
	private RoomType roomType;
	
	@Column(name = "room_type_photo", columnDefinition = "longblob")
	private byte[] roomTypePhoto;
	
	@Column(name = "room_type_photo_state")
	private Boolean roomTypePhotoState;

	

	public RoomTypePhoto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Integer getRoomTypePhotoId() {
		return roomTypePhotoId;
	}

	public void setRoomTypePhotoId(Integer roomTypePhotoId) {
		this.roomTypePhotoId = roomTypePhotoId;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public byte[] getRoomTypePhoto() {
		return roomTypePhoto;
	}

	public void setRoomPhoto(byte[] roomTypePhoto) {
		this.roomTypePhoto = roomTypePhoto;
	}

	public Boolean getRoomTypePhotoState() {
		return roomTypePhotoState;
	}

	public void setRoomTypePhotoState(Boolean roomTypePhotoState) {
		this.roomTypePhotoState = roomTypePhotoState;
	}

	@Override
	public String toString() {
		return "RoomTypePhoto [roomTypePhotoId=" + roomTypePhotoId + ", roomPhoto=" + Arrays.toString(roomTypePhoto)
				+ ", roomTypePhotoState=" + roomTypePhotoState + "]";
	}
	
	
	
}
