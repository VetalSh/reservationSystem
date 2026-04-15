package com.example.reservation.reservations;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Modifying
    @Query("""
            update ReservationEntity r
             set r.status = :status
              where r.id = :id
             """)
    void setStatus(@Param("id") Long id, @Param("status") ReservationStatus reservationStatus);

    @Query("""
            select r.id from ReservationEntity r
             where r.roomId = :roomId
               and r.status = :status
               and r.startDate < :endDate
               and r.endDate > :startDate
             """)
    List<Long> findConflictReservationIds(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("status") ReservationStatus status
    );

    @Query("""
            select r from ReservationEntity r
             where (:roomId IS NULL OR r.roomId = :roomId)
               and (:userId IS NULL OR r.userId = :userId)
             """)
    List<ReservationEntity> searchAllByFilter(
            Long roomId,
            Long userId,
            Pageable pageable
    );
}
