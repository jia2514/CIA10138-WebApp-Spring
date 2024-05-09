package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.dept.model.DeptVO;
import com.joyfulresort.room.model.Room;
import com.joyfulresort.room.model.RoomService;
import com.joyfulresort.roomorder.model.RoomOrder;
import com.joyfulresort.roomorder.model.RoomOrderService;
import com.joyfulresort.roomorderitem.model.RoomOrderItem;
import com.joyfulresort.roomorderitem.model.RoomOrderItemService;
import com.joyfulresort.roomtype.model.RoomType;
import com.joyfulresort.roomtype.model.RoomTypeService;

import java.util.*;



//@PropertySource("classpath:application.properties") // 於https://start.spring.io建立Spring Boot專案時, application.properties文件預設已經放在我們的src/main/resources 目錄中，它會被自動檢測到
@Controller
public class IndexController_inSpringBoot {
	
	// @Autowired (●自動裝配)(Spring ORM 課程)
	// 目前自動裝配了EmpService --> 供第66使用
	@Autowired
	RoomOrderService roomOrderSvc;
	
	@Autowired
	RoomTypeService roomTypeSvc;
	
	@Autowired
	RoomOrderItemService roomOrderItemSvc;
	
	@Autowired
	RoomService roomSvc;

	
    // inject(注入資料) via application.properties
    @Value("${welcome.message}")
    private String message;
	
//    private List<String> myList = Arrays.asList("Spring Boot Quickstart 官網 : https://start.spring.io", "IDE 開發工具", "直接使用(匯入)官方的 Maven Spring-Boot-demo Project + pom.xml", "直接使用官方現成的 @SpringBootApplication + SpringBootServletInitializer 組態檔", "依賴注入(DI) HikariDataSource (官方建議的連線池)", "Thymeleaf", "Java WebApp (<font color=red>快速完成 Spring Boot Web MVC</font>)");
    @GetMapping("/")
    public String index(Model model) {
    	model.addAttribute("message", message);
//        model.addAttribute("myList", myList);
        return "index"; //view
    }
    
  //====================================================================  
    @GetMapping("/main_page")
	public String main_page(Model model) {
		return "back-end/main_page";
	}	
    
  //====================================================================
    @GetMapping("/roomorder/roomorderselect")
	public String roomorderselect(Model model) {
		return "back-end/roomorder/roomorderselect";
	}
    
    @GetMapping("/roomorder/listAllRoomOrder")
	public String listAllRoomOrder(Model model) {
		return "back-end/roomorder/listAllRoomOrder";
	}
    
    @ModelAttribute("roomOrderListData")  
	protected List<RoomOrder> referenceListData_RoomOrder(Model model) {
    	List<RoomOrder> list = roomOrderSvc.getAll();
		return list;
	}
    

	@GetMapping("/roomorder/addRoomOrder")
	public String addRoomOrder(@RequestParam Map<String, String> rq
			                   , Model model) {
		System.out.println(rq);
		model.addAttribute("roomOrder", rq);
		return "back-end/roomorder/addRoomOrder";
	}
    
  //====================================================================
    @GetMapping("/roomschedule/roomscheduleselect")
	public String roomscheduleselect(Model model) {
		return "back-end/roomschedule/roomscheduleselect";
	}
    
    @ModelAttribute("roomTypeListData") 
	protected List<RoomType> referenceListData_RoomType(Model model) {
		model.addAttribute("roomType", new RoomType()); 
		System.out.println("123");
		List<RoomType> list = roomTypeSvc.getAll();
		return list;
	}
    

    @GetMapping("/roomorderitem/checkin")
	public String listCheckInItem(Model model) {
		
    	List<RoomOrder> list = roomOrderSvc.getTodayCheckIn();
    	System.out.println(list);
		model.addAttribute("checkInList", list);
    	return "back-end/roomorderitem/checkin";
	}
    
    @GetMapping("/roomorderitem/checkout")
	public String listCheckOutItem(Model model) {
		
    	List<RoomOrder> list = roomOrderSvc.getTodayCheckOut();
    	System.out.println(list);
		model.addAttribute("checkOutList", list);
    	return "back-end/roomorderitem/checkout";
	}
    
    
    
    @ModelAttribute("roomListData") 
	protected List<Room> referenceListData_Room(Model model) {
		model.addAttribute("room", new Room()); 
		List<Room> list = roomSvc.getAll();
		return list;
	}
    
    
    
    


}