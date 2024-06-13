package org.github.aastrandemma.todoapi.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.github.aastrandemma.todoapi.domain.entity.Person;
import org.github.aastrandemma.todoapi.domain.entity.Task;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@DataJpaTest
public class TaskRepositoryTest {
	@Autowired
	private TaskRepository taskRepository;
    @Autowired
    private PersonRepository personRepository;

    private Person existingPerson;
    private Task existingTask;
    private Task existingTaskTwo;

    @BeforeEach
    void setUp() {
        existingPerson = personRepository.save(new Person("Jane Doe"));
        existingTask = taskRepository.save(new Task("Test title", "Test description", LocalDate.now().plusDays(7), existingPerson));
        existingTaskTwo = taskRepository.save(new Task("Test title two", "Test description two", LocalDate.now().plusDays(14), existingPerson));
    }

	@Test
    @Transactional
	public void testFindByTitleContainsIgnoreCase() {
		String titleContaining = "test";
		List<Task> expected = new ArrayList<>(Arrays.asList(existingTask, existingTaskTwo));
		List<Task> actual = taskRepository.findByTitleContainsIgnoreCase(titleContaining);

        assertNotNull(expected);
        assertNotNull(actual);
		assertEquals(expected, actual);
	}

	@Test
    @Transactional
	public void testFindByPerson_Id() {
		Long personId = existingPerson.getId();
		List<Task> expected = new ArrayList<>(Arrays.asList(existingTask, existingTaskTwo));
		List<Task> actual = taskRepository.findByPerson_Id(personId);

        assertNotNull(expected);
        assertNotNull(actual);
		assertEquals(expected, actual);
	}

	@Test
    @Transactional
	public void testFindByDoneExpectingEmptyList() {
		boolean status = true;
		List<Task> expected = new ArrayList<>();
		List<Task> actual = taskRepository.findByDone(status);

        assertNotNull(expected);
        assertNotNull(actual);
		assertEquals(expected, actual);
	}

    @Test
    @Transactional
    public void testFindByDone() {
        boolean status = true;
        existingTask.setDone(status);

        List<Task> expected = new ArrayList<>(Collections.singletonList(existingTask));
        List<Task> actual = taskRepository.findByDone(status);

        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    public void testFindByDeadlineBetweenExpectingEmptyList() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(5);
        List<Task> expected = new ArrayList<>();
        List<Task> actual = taskRepository.findByDeadlineBetween(start, end);

        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

	@Test
    @Transactional
	public void testFindByDeadlineBetween() {
		LocalDate start = LocalDate.now().plusDays(5);
		LocalDate end = LocalDate.now().plusDays(10);
		List<Task> expected = new ArrayList<>(Collections.singletonList(existingTask));
		List<Task> actual = taskRepository.findByDeadlineBetween(start, end);

        assertNotNull(expected);
        assertNotNull(actual);
		assertEquals(expected, actual);
	}

	@Test
    @Transactional
	public void testFindByPersonIsNull() {
        existingTaskTwo.setPerson(null);
		List<Task> expected = new ArrayList<>(Collections.singletonList(existingTaskTwo));
		List<Task> actual = taskRepository.findByPersonIsNull();

        assertNotNull(expected);
        assertNotNull(actual);
		assertEquals(expected, actual);
	}

	@Test
    @Transactional
	public void testFindByDoneIsFalse() {
		List<Task> expected = new ArrayList<>(Arrays.asList(existingTask, existingTaskTwo));
		List<Task> actual = taskRepository.findByDoneIsFalse();

        assertNotNull(expected);
        assertNotNull(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFindByDoneIsFalseAndDeadlineIsBefore() {
        existingTaskTwo.setDeadline(LocalDate.now().minusDays(14));
		LocalDate currentDate = LocalDate.now();
        List<Task> expected = new ArrayList<>(Collections.singletonList(existingTaskTwo));
		List<Task> actual = taskRepository.findByDoneIsFalseAndDeadlineIsBefore(currentDate);

        assertNotNull(expected);
        assertNotNull(actual);
		assertEquals(expected, actual);
	}
}