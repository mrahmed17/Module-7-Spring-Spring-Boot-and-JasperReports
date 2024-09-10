package com.mrahmed.ReviewSprintBoot.repository;

import com.mrahmed.ReviewSprintBoot.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.hotel.name = :hotelName")
    public List<Room> findRoomByHotelName(@Param("hotelName") String hotelName);

    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId")
    public List<Room> findRoomByHotelId(@Param("hotelId") int hotelId);



}
