package com.nep.timetable.service;

import com.nep.timetable.dto.TimetableDto;
import com.nep.timetable.entity.Course;
import com.nep.timetable.entity.Timetable;
import com.nep.timetable.ga.Chromosome;
import com.nep.timetable.ga.GAResult;
import com.nep.timetable.ga.GeneticAlgorithmEngine;
import com.nep.timetable.recommendation.TimetableExplanationEngine;
import com.nep.timetable.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TimetableServiceTest {

    @Mock
    private TimetableRepository timetableRepository;
    @Mock
    private TimetableEntryRepository timetableEntryRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private TimeSlotRepository timeSlotRepository;
    @Mock
    private FacultyAvailabilityRepository facultyAvailabilityRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private GeneticAlgorithmEngine gaEngine;
    @Mock
    private TimetableExplanationEngine explanationEngine;

    @InjectMocks
    private TimetableService timetableService;

    @Test
    void testGenerateTimetable() {
        // Arrange
        Long timetableId = 1L;
        TimetableDto.GenerateRequest request = new TimetableDto.GenerateRequest();
        request.setSemester(3);

        Timetable timetable = new Timetable();
        timetable.setId(timetableId);
        timetable.setName("Test Timetable");
        timetable.setStatus(Timetable.TimetableStatus.DRAFT);
        timetable.setEntries(Collections.emptyList());

        when(timetableRepository.findById(timetableId)).thenReturn(Optional.of(timetable));
        
        Course course = new Course();
        course.setIsActive(true);
        course.setSemester(3);
        when(courseRepository.findAll()).thenReturn(Collections.singletonList(course));
        
        when(facultyRepository.findByIsActiveTrue()).thenReturn(Collections.emptyList());
        when(roomRepository.findByIsActiveTrue()).thenReturn(Collections.emptyList());
        when(timeSlotRepository.findByIsBreakFalseAndIsActiveTrueOrderByDayOfWeekAscSlotOrderAsc()).thenReturn(Collections.emptyList());
        when(facultyAvailabilityRepository.findAll()).thenReturn(Collections.emptyList());

        GAResult gaResult = new GAResult();
        gaResult.setBestChromosome(new Chromosome());
        gaResult.setBestFitness(0.95);
        gaResult.setExecutionTimeMs(100L);
        gaResult.setGenerationsUsed(50);
        when(gaEngine.run(any(), any(), any(), any(), any())).thenReturn(gaResult);

        when(explanationEngine.generateTimetableExplanation(any(), anyInt(), anyInt())).thenReturn("AI Explanation");

        // Act
        timetableService.generateTimetable(timetableId, request);

        // Assert
        assertEquals(Timetable.TimetableStatus.GENERATED, timetable.getStatus());
        assertEquals(0.95, timetable.getFitnessScore());
        assertEquals("AI Explanation", timetable.getAiExplanation());
        
        // Verify save was called for GENERATING status and then for GENERATED status
        verify(timetableRepository, times(2)).save(timetable);
        verify(timetableEntryRepository).saveAll(any());
    }
}
