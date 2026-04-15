package com.example.reservation;

import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public Reservation toDomain(ReservationEntity entity) {
        return new Reservation(
                entity.getId(),
                entity.getUserId(),
                entity.getRoomId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getStatus()
        );
    }

    public ReservationEntity toEntity(Reservation reservation) {
        return new ReservationEntity(
                reservation.id(),
                reservation.userId(),
                reservation.roomId(),
                reservation.startDate(),
                reservation.endDate(),
                reservation.status()
        );
    }
}
