package com.smart.booking.data.store.model;

import java.util.List;

public record ParseRegionResultDto(
    List<ParseRegionResultDtoDocument> documents,
    ParseRegionResultDtoMeta meta // 메타데이터 추가
) {

    public record ParseRegionResultDtoDocument(
        String address_name,
        String address_type,
        String x,
        String y,
        ParseRegionResultDtoDocumentAddress address,
        ParseRegionResultDtoRoadAddress road_address // 객체로 수정

    ) {

        public record ParseRegionResultDtoDocumentAddress(
            String address_name,
            String region_1depth_name,
            String region_2depth_name,
            String region_3depth_name,
            String region_3depth_h_name,
            String h_code,
            String b_code,
            String mountain_yn,
            String main_address_no,
            String sub_address_no,
            String x,
            String y
        ) {

        }

        // road_address가 있을 때 객체로 받을 수 있도록 정의
        public record ParseRegionResultDtoRoadAddress(
            String address_name,
            String building_name,
            String main_building_no,
            String region_1depth_name,
            String region_2depth_name,
            String region_3depth_name,
            String road_name,
            String sub_building_no,
            String underground_yn,
            String x,
            String y,
            String zone_no
        ) {

        }

    }

    // 메타데이터 처리
    public record ParseRegionResultDtoMeta(
        boolean is_end,
        int pageable_count,
        int total_count
    ) {

    }

}