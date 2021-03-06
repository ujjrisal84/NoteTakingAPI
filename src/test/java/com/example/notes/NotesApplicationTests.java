package com.example.notes;

import com.example.notes.model.Note;
import com.example.notes.repository.NoteRepository;
import com.example.notes.service.NoteService;
import com.example.notes.service.NoteServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotesApplicationTests {

	// @Mock
	private NoteRepository noteRepository;

	private NoteService noteService;

	@Before
	public void setup() {
		noteRepository = Mockito.mock(NoteRepository.class);
		noteService = new NoteServiceImpl(noteRepository);
	}

	@Test
	public void testThatNotesTitleMatchesSearchTerm() {
		String searchTerm = "friends";

		List<Note> notesFromDb = new ArrayList<>(Arrays.asList(
				note(1, "test title 1"), 
				note(2, "test friends title"), 
				note(3, "test title 3")));

		Mockito.when(noteRepository.findAll()).thenReturn(notesFromDb);

		List<Note> notes = noteService.searchByTitle(searchTerm);

		Assert.assertEquals(1, notes.size());
		Assert.assertEquals("test friends title", notes.get(0).getTitle());
	}
	
	
	
	@Test
	public void testThatNoMatchFound() {
		String searchTerm = "abcd";

		List<Note> notesFromDb = new ArrayList<>(Arrays.asList(
				note(1, "test title 1"), 
				note(2, "test friends title"), 
				note(3, "test title 3")));

		Mockito.when(noteRepository.findAll()).thenReturn(notesFromDb);

		List<Note> notes = noteService.searchByTitle(searchTerm);

		Assert.assertEquals(0, notes.size());
		
	}

	private Note note(long id, String title) {
		Note note = new Note();
		note.setId(id);
		note.setTitle(title);
		return note;
	}

}
