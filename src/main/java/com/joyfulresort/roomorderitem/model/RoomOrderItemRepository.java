// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.joyfulresort.roomorderitem.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.joyfulresort.roomorder.model.RoomOrder;


public interface RoomOrderItemRepository extends JpaRepository<RoomOrderItem, Integer> {

	 @Transactional
	    @Query(value = "select roi from RoomOrderItem roi where roi.roomOrder = ?1")
	    List<RoomOrderItem> findByRoomOrder(RoomOrder roomOrder);
	
	
	

}