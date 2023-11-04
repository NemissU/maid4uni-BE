package com.swp391.maid4uni.request;

import com.swp391.maid4uni.enums.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;


/**
 * The type Update package request.
 */
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePackageRequest {
    int id;
    String name;
    String description;
    Category category;
    List<UpdateServiceRequest> serviceList;
}
