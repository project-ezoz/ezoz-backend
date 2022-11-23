package ezoz.backend_ezoz.api.journal.controller;

import ezoz.backend_ezoz.api.journal.dto.JournalDto;
import ezoz.backend_ezoz.api.journal.service.JournalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JournalApiController {

    private final JournalApiService journalApiService;

    @GetMapping("/journal")
    public ResponseEntity<?> registerJournal(JournalDto.Request journalRequestDto) {

        Long savedId = journalApiService.registerJournal(journalRequestDto);

        return ResponseEntity.ok(savedId);
    }
}
