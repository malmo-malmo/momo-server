package com.momo.common.dto;

import com.momo.common.domain.GroupCategory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCategoryRequest {

  private List<String> groupCategories;

  public List<GroupCategory> toGroupCategories() {
    return groupCategories.stream().map(GroupCategory::of).collect(Collectors.toList());
  }
}
