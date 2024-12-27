package com.example.booking_app_be.service;


import com.example.booking_app_be.dto.request.BookRequest;
import com.example.booking_app_be.dto.request.BookedRoomRequest;
import com.example.booking_app_be.dto.request.BookingRequest;
import com.example.booking_app_be.dto.response.BookedRoomResponse;
import com.example.booking_app_be.entity.BookedRoom;
import com.example.booking_app_be.entity.Hotel;
import com.example.booking_app_be.entity.Room;
import com.example.booking_app_be.entity.Service;
import com.example.booking_app_be.exception.AppException;
import com.example.booking_app_be.exception.ErrorCode;
import com.example.booking_app_be.mapper.BookedRoomMapper;
import com.example.booking_app_be.mapper.BookingMapper;
import com.example.booking_app_be.mapper.RoomMapper;
import com.example.booking_app_be.mapper.ServiceMapper;
import com.example.booking_app_be.repository.BookedRoomRepository;
import com.example.booking_app_be.repository.HotelRepository;
import com.example.booking_app_be.repository.RoomRepository;
import com.example.booking_app_be.repository.ServiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookedRoomService {
    BookingService bookingService;
    BookedRoomRepository bookedRoomRepository;
    HotelRepository hotelRepository;
    BookedRoomMapper bookedRoomMapper;
    BookingMapper bookingMapper;
    RoomRepository roomRepository;
    ServiceRepository serviceRepository;
    RoomMapper roomMapper;
    ServiceMapper serviceMapper;

    public BookedRoomResponse createBookedRoom(BookRequest bookRequest){
        BookedRoomRequest request = bookedRoomMapper.toBookedRoomRequest(bookRequest);

        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow();
        BookedRoom bookedRoom = bookedRoomMapper.toBookedRoom(request);

        if (!checkExistBookedRoom(request, hotel)) {
            List<Room> rooms = null;
            List<Service> services = null;

            if (!request.getRooms().isEmpty()) {
                rooms = roomRepository.findAllById(request.getRooms());

                if (rooms.isEmpty())
                    throw new AppException(ErrorCode.ROOM_NOT_EXISTED);

                for (Room room : rooms) {
                    bookedRoom.setPrice(bookedRoom.getPrice() + room.getPrice());
                }
            }
            if (!request.getServices().isEmpty()) {
                services = serviceRepository.findAllById(request.getServices());
                if (services.isEmpty())
                    throw new AppException(ErrorCode.SERVICE_NOT_EXISTED);
                for (Service service : services) {
                    bookedRoom.setPrice(bookedRoom.getPrice() + service.getPrice());
                }
            }



            bookedRoom.setRooms(rooms);
            bookedRoom.setServices(services);
            bookedRoom.setHotel(hotel);
            bookedRoomRepository.save(bookedRoom);

            BookingRequest bookingRequest = bookingMapper.toBookingRequest(bookRequest);
            bookingService.createBookingService(bookingRequest, bookedRoom);

            BookedRoomResponse bookedRoomResponse = bookedRoomMapper.toBookedRoomResponse(bookedRoom);
            bookedRoomResponse.setRooms(rooms.stream().map(room -> roomMapper.toRoomResponse(room)).toList());
            bookedRoomResponse.setServices(services.stream().map(service -> serviceMapper.toServiceResponse(service)).toList());

            return bookedRoomResponse;
        }

        throw new AppException(ErrorCode.BOOKED_ROOM_EXISTED);
    }

    private boolean checkExistBookedRoom(BookedRoomRequest request, Hotel hotel) {
        List<BookedRoom> bookedRooms = bookedRoomRepository.getAllBookedRoomIntersectArrivalDateAndDepartureDate(request.getArrivalDate(), request.getDepartureDate(), hotel.getId());

        for (BookedRoom bookedRoom: bookedRooms) {
            for (Room room: bookedRoom.getRooms()) {
                if (request.getRooms().contains(room.getId())){
                    return true;
                }
            }
        }

        return false;
    }
}
