package org.euro2024.controller;

import org.euro2024.model.Room;
import org.euro2024.model.RoomRule;
import org.euro2024.service.RoomService;
import org.euro2024.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;



    @PostMapping("/create")
    public Room createRoom(@RequestBody Room room, @AuthenticationPrincipal User user) {
        return roomService.createRoom(room, user.getUsername());
    }

    @PostMapping("/join/{roomId}")
    public Room joinRoom(@PathVariable String roomId, @AuthenticationPrincipal User user) {
        org.euro2024.model.User customUser = userService.findByUsername(user.getUsername());
        return roomService.addUserToRoom(roomId, customUser);
    }

    @PostMapping("/set-rules")
    public Room setRoomRules(@RequestParam String roomUrl, @RequestBody RoomRule rules) {
        return roomService.setRoomRules(roomUrl, rules);
    }

}
