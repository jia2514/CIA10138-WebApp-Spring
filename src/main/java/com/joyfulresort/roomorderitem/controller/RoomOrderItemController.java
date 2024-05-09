package com.joyfulresort.roomorderitem.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
@RequestMapping("/roomorderitem")
public class RoomOrderItemController {

	@Autowired
	RoomOrderService roomOrderSvc;

	@Autowired
	RoomOrder roomOrder;

	@Autowired
	MemberService memberSvc;

	@Autowired
	RoomTypeService roomTypeSvc;

	@Autowired
	RoomOrderItemService roomOrderItemSvc;
	
	@Autowired
	RoomService roomSvc;

	@Autowired
	RoomOrderItem roomOrderItem;

	@Autowired
	RoomSchedule roomSchedule;

	@PostMapping("getAll")
	public String getAll(ModelMap model) {
		List<RoomOrderItem> list = roomOrderItemSvc.getAll();
		model.addAttribute("roomOrderItemList", list);
		
		return "back-end/roomorder/listAllRoomOrderItem";
	}
	
	@PostMapping("checkinUpdate")
	public ResponseEntity<Map<String, Object>> checkinUpdate(HttpServletRequest req) {
//		{specialREQ=嬰兒床及澡盆, roomGuestName=劉小雪, roomOrderItemId=12, roomGuestPhone=0967890123, roomId=4}
		String specialREQ = req.getParameter("specialREQ").trim();
		String roomGuestName = req.getParameter("roomGuestName").trim();
		String roomGuestPhone = req.getParameter("roomGuestPhone").trim();
		Integer roomOrderItemId = Integer.valueOf(req.getParameter("roomOrderItemId"));
		Integer roomId = Integer.valueOf(req.getParameter("roomId"));
		RoomOrderItem roomOrderItem = roomOrderItemSvc.getOneRoomOrderItem(roomOrderItemId);
		roomOrderItem.setSpecialREQ(specialREQ);
		roomOrderItem.setRoomGuestName(roomGuestName);
		roomOrderItem.setRoomGuestPhone(roomGuestPhone);
		roomOrderItem.setRoom(roomSvc.getOneRoom(roomId));
		roomOrderItem.setRoomOrderItemState(true);
		roomSvc.getOneRoom(roomId).setRoomState((byte)1);
		RoomOrderItem orderItem = roomOrderItemSvc.updateRoomOrderItem(roomOrderItem);

		Map<String, Object> roomOrderInfo = new HashMap<>();
		roomOrderInfo.put("specialREQ", orderItem.getSpecialREQ());
		roomOrderInfo.put("roomGuestName", orderItem.getRoomGuestName());
		roomOrderInfo.put("roomGuestPhone", orderItem.getRoomGuestPhone());
		roomOrderInfo.put("roomOrderItemId", orderItem.getRoomOrderItemId());
		roomOrderInfo.put("roomId", orderItem.getRoom().getRoomId());

		return ResponseEntity.ok(roomOrderInfo);
	}
	
	
	@PostMapping("checkinUpdateSelect")
	public ResponseEntity<List<Integer>> checkinUpdateSelect(HttpServletRequest req) {

		List<RoomType> list = roomTypeSvc.getAll(); 

		List<Integer> roomIdInfo = new ArrayList<>();
		for(RoomType roomType :list) {
			Set<Room> rooms = roomType.getRoom();
			for(Room room:rooms) {
				if(room.getRoomSaleState()==true && room.getRoomState()==0) {
					roomIdInfo.add(room.getRoomId());
				}
			}
		}
		
		return ResponseEntity.ok(roomIdInfo);
	}
	
	
	
	
	
	
	
	
	
	
	

	@PostMapping("list_ByCompositeQuery")
	public String listAllRoomOrder(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<RoomOrder> list = roomOrderSvc.getAll(map);
		System.out.println("65" + list);
		model.addAttribute("roomOrderList", list);
		return "back-end/roomorder/listAllRoomOrder";
	}

	@PostMapping("getOneToCancel")
	public ResponseEntity<Map<String, Object>> getOneToCancel(@RequestParam("roomOrderId") String roomOrderId) {
		Integer orderId = Integer.valueOf(roomOrderId);

		RoomOrder roomOrder = roomOrderSvc.cancelRoomOrder(orderId);

		Map<String, Object> roomOrderInfo = new HashMap<>();
		roomOrderInfo.put("roomOrderId", roomOrder.getRoomOrderId());
		roomOrderInfo.put("orderDate", roomOrder.getOrderDate());
		roomOrderInfo.put("roomOrderState", roomOrder.getRoomOrderState());
		roomOrderInfo.put("refundState", roomOrder.getRefundState());
		roomOrderInfo.put("completeDateTime", roomOrder.getCompleteDateTime());

		return ResponseEntity.ok(roomOrderInfo);
	}

	@PostMapping("getOneToRefund")
	public ResponseEntity<Map<String, Object>> getOneToRefund(@RequestParam("roomOrderId") String roomOrderId) {
		System.out.println(roomOrderId);
		Integer orderId = Integer.valueOf(roomOrderId);

		RoomOrder roomOrder = roomOrderSvc.refundRoomOrder(orderId);

		Map<String, Object> roomOrderInfo = new HashMap<>();
		roomOrderInfo.put("roomOrderId", roomOrder.getRoomOrderId());
		roomOrderInfo.put("orderDate", roomOrder.getOrderDate());
		roomOrderInfo.put("roomOrderState", roomOrder.getRoomOrderState());
		roomOrderInfo.put("refundState", roomOrder.getRefundState());
		roomOrderInfo.put("completeDateTime", roomOrder.getCompleteDateTime());

		return ResponseEntity.ok(roomOrderInfo);
	}

	@GetMapping("addOne")
	public String addOne(ModelMap model) {
		RoomOrder roomOrder = new RoomOrder();
		model.addAttribute("roomOrder", roomOrder);
		return "back-end/roomorder/addRoomOrder";
	}

	@PostMapping("insert")
	public String insert(HttpServletRequest req, Model model) throws IOException {

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);

		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		String memberPhone = req.getParameter("memberPhone");
//		String memberPhoneReg = "^09[0-9]{8}$";
//		if (memberPhone == null || memberPhone.trim().length() == 0) {
//			errorMsgs.put("memberPhone", "會員電話: 請勿空白");
//		} else if (!memberPhone.trim().matches(memberPhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
//			errorMsgs.put("memberPhone", "會員電話: 格式為09xxxxxxxx共10碼");
//		}
//
//		if (!errorMsgs.isEmpty()) {
//			return "/back-end/roomorder/addRoomOrder";
//
//		}

		Member member = memberSvc.findByMemberPhone(memberPhone);
		Date checkInDate = java.sql.Date.valueOf(req.getParameter("checkInDate"));
		Date checkOutDate = java.sql.Date.valueOf(req.getParameter("checkOutDate"));

		roomOrder.setCheckInDate(checkInDate);
		roomOrder.setCheckOutDate(checkOutDate);
		roomOrder.setMember(member);

		long diffInMillies = checkOutDate.getTime() - checkInDate.getTime();
		int differenceInDays = (int) (diffInMillies / (1000 * 3600 * 24));
		List<RoomType> list = roomTypeSvc.getAll();
		Set<RoomOrderItem> roomOrderItems = new LinkedHashSet<>();
		Integer roomTypeId = null;
		Integer roomAmount = 0;
		System.out.println("157 list.size()+"+list.size());
		for (int i = 1; i <= list.size(); i++) {
		    roomTypeId = Integer.valueOf(req.getParameter("roomType" + i));
		    roomAmount = Integer.valueOf(req.getParameter("roomAmount" + i));
		    if (roomAmount > 0 && roomAmount != null) {
		        for (int amount = 1; amount <= roomAmount; amount++) {
		            RoomOrderItem roomOrderItem = new RoomOrderItem(); // 在每次迭代中创建新的对象
		            roomOrderItem.setRoomOrder(roomOrder);
		            roomOrderItem.setRoomType(roomTypeSvc.getOneRoomType(roomTypeId));
		            roomOrderItem.setRoomPrice(roomTypeSvc.getOneRoomType(roomTypeId).getRoomTypePrice());
		            roomOrderItem.setSpecialREQ(req.getParameter("specialreq").trim());
		            Set<RoomSchedule> roomSchedules = new LinkedHashSet<>();
		            int count2 = 0;
		            while (count2 < differenceInDays) {
		                RoomSchedule roomSchedule = new RoomSchedule();
		                roomSchedule.setRoomType(roomTypeSvc.getOneRoomType(roomTypeId));
		                roomSchedule.setRoomOderItem(roomOrderItem);
		                long millies = checkInDate.getTime();
		                Date date = new Date(count2 * 1000 * 3600 * 24 + millies);
		                roomSchedule.setRoomScheduleDate(date);
		                roomSchedules.add(roomSchedule);
		                count2++;
		            }
		            roomOrderItem.setRoomSchedules(roomSchedules);
		            roomOrderItems.add(roomOrderItem);
		        }
		    }
		}
		System.out.println("187 roomOrderItems"+roomOrderItems);
		roomOrder.setRoomOrderItems(roomOrderItems);

		roomOrderSvc.addRoomOrder(roomOrder);

		List<RoomOrder> listAll = roomOrderSvc.getAll();
		model.addAttribute("roomOrderList", listAll);
		return "back-end/roomorder/listAllRoomOrder";
	}

//	/*
//	 * This method will be called on listAllEmp.html form submission, handling POST request
//	 */
//	@PostMapping("getOne_For_Update")
//	public String getOne_For_Update(@RequestParam("roomorderid") String roomorderid, ModelMap model) {
//		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
//		/*************************** 2.開始查詢資料 *****************************************/
//		// EmpService empSvc = new EmpService();
//		RoomOrder roomOrder = roomOrderSvc.getOneOrder(Integer.valueOf(roomorderid));
//
//		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
//		model.addAttribute("roomOrder", roomOrder);
//		return "back-end/emp/updateRoomOrder"; // 查詢完成後轉交update_emp_input.html
//	}

	/*
	 * This method will be called on update_emp_input.html form submission, handling
	 * POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid RoomOrder roomOrder, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
//		result = removeFieldError(empVO, result, "upFiles");
//
//		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
//			// EmpService empSvc = new EmpService();
//			byte[] upFiles = empSvc.getOneEmp(empVO.getEmpno()).getUpFiles();
//			empVO.setUpFiles(upFiles);
//		} else {
//			for (MultipartFile multipartFile : parts) {
//				byte[] upFiles = multipartFile.getBytes();
//				empVO.setUpFiles(upFiles);
//			}
//		}
//		if (result.hasErrors()) {
//			return "back-end/emp/update_emp_input";
//		}
		/*************************** 2.開始修改資料 *****************************************/
		// EmpService empSvc = new EmpService();
		roomOrderSvc.updateRoomOrder(roomOrder);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		roomOrder = roomOrderSvc.getOneRoomOrder(Integer.valueOf(roomOrder.getRoomOrderId()));
		model.addAttribute("roomOrder", roomOrder);
		return "back-end/roomorder/listOneRoomOrder"; // 修改成功後轉交listOneEmp.html
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST
	 * request
	 */
//	@PostMapping("delete")
//	public String delete(@RequestParam("empno") String empno, ModelMap model) {
//		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
//		/*************************** 2.開始刪除資料 *****************************************/
//		// EmpService empSvc = new EmpService();
//		empSvc.deleteEmp(Integer.valueOf(empno));
//		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
//		List<EmpVO> list = empSvc.getAll();
//		model.addAttribute("empListData", list);
//		model.addAttribute("success", "- (刪除成功)");
//		return "back-end/emp/listAllEmp"; // 刪除完成後轉交listAllEmp.html
//	}

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : <form:select
	 * path="deptno" id="deptno" items="${deptListData}" itemValue="deptno"
	 * itemLabel="dname" />
	 */
//	@ModelAttribute("deptListData")
//	protected List<DeptVO> referenceListData() {
//		// DeptService deptSvc = new DeptService();
//		List<DeptVO> list = deptSvc.getAll();
//		return list;
//	}

	/*
	 * 【 第二種作法 】 Method used to populate the Map Data in view. 如 : <form:select
	 * path="deptno" id="deptno" items="${depMapData}" />
	 */
//	@ModelAttribute("deptMapData") //
//	protected Map<Integer, String> referenceMapData() {
//		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
//		map.put(10, "財務部");
//		map.put(20, "研發部");
//		map.put(30, "業務部");
//		map.put(40, "生管部");
//		return map;
//	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(RoomOrder roomOrder, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)).collect(Collectors.toList());
		result = new BeanPropertyBindingResult(roomOrder, "roomOrder");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

}