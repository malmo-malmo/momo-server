package com.momo.common.dto;

import com.momo.common.domain.GroupCategory;
import com.momo.user.domain.model.Location;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnumResponse {

  private String code;
  private String name;

  public EnumResponse(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public static EnumResponse of(String code, String name) {
    return new EnumResponse(code, name);
  }

  public static List<EnumResponse> listOfGroupCategory() {
    return Arrays.stream(GroupCategory.values())
        .map(status -> EnumResponse.of(status.getCode(), status.getName()))
        .collect(Collectors.toList());
  }

  public static List<EnumResponse> listOfLocation() {
    return Arrays.stream(Location.values())
        .map(status -> EnumResponse.of(status.getCode(), status.getName()))
        .collect(Collectors.toList());
  }
}
