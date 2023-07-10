package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.GradeDTO;
import com.adminuniversity.adminuniversityrest.entity.GradeEntity;

public interface GradeService {
    GradeEntity mapEntity(GradeDTO gradeDTO, long studentId, long courseId);

    GradeDTO mapDTO(GradeEntity gradeEntity);
}
