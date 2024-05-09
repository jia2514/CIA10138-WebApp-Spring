package com.joyfulresort.room.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.joyfulresort.member.model.Member;
import com.joyfulresort.member.model.MemberService;
import com.joyfulresort.room.model.Room;
import com.joyfulresort.room.model.RoomService;
import com.joyfulresort.roomorder.model.RoomOrder;
import com.joyfulresort.roomorder.model.RoomOrderService;
import com.joyfulresort.roomtype.model.RoomType;
import com.joyfulresort.roomtype.model.RoomTypeService;
import com.joyfulresort.roomorderitem.model.RoomOrderItem;
import com.joyfulresort.roomorderitem.model.RoomOrderItemService;
import com.joyfulresort.roomschedule.model.RoomSchedule;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	RoomService roomSvc;

	
	

	@PostMapping("checkout")
	public ResponseEntity<Map<String, Object>> getOneToCheckout(@RequestParam("roomId") String roomId) {
		Integer rId = Integer.valueOf(roomId);
		System.out.println("roomId+"+roomId);
		Room room = roomSvc.checkoutRoom(rId);
		System.out.println("room+"+room);
		Map<String, Object> roomInfo = new HashMap<>();
		roomInfo.put("roomId", room.getRoomId());
		roomInfo.put("roomState", room.getRoomState());
		
		System.out.println("roomInfo+"+roomInfo);
		return ResponseEntity.ok(roomInfo);
	}

	@PostMapping("clean")
	public ResponseEntity<Map<String, Object>> getOneToClaen(@RequestParam("roomId") String roomId) {
		
		Integer rId = Integer.valueOf(roomId);

		Room room = roomSvc.cleanRoom(rId);

		Map<String, Object> roomInfo = new HashMap<>();
		roomInfo.put("roomId", room.getRoomId());
		roomInfo.put("roomState", room.getRoomState());
		
		
		return ResponseEntity.ok(roomInfo);
	}

	
}