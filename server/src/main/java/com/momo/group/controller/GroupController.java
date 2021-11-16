package com.momo.group.controller;

import com.momo.common.dto.EnumResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

  @GetMapping("/categories")
  public ResponseEntity<List<EnumResponse>> getMyInfo() {
    return ResponseEntity.ok(EnumResponse.listOfGroupCategory());
  }
}
