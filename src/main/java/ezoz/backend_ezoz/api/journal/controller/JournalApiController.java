package ezoz.backend_ezoz.api.journal.controller;

import ezoz.backend_ezoz.api.journal.dto.JournalDetailDto;
import ezoz.backend_ezoz.api.journal.dto.JournalDto;
import ezoz.backend_ezoz.api.journal.service.JournalApiService;
import ezoz.backend_ezoz.domain.journal.entity.JournalType;
import ezoz.backend_ezoz.global.error.exception.BusinessException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import ezoz.backend_ezoz.global.validator.ImageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JournalApiController {

    private final JournalApiService journalApiService;
    private final ImageValidator imageValidator;

    @PostMapping("/journal")
    public ResponseEntity<?> registerJournal(JournalDto.Request journalRequestDto) {


        List<MultipartFile> journalImageFiles = journalRequestDto.getJournalImageFiles();
        if (journalImageFiles == null || imageValidator.notExistFileName(journalImageFiles)) {
            throw new BusinessException(ErrorCode.IMAGE_NOT_EXISTS);
        }
        Long savedId = journalApiService.registerJournal(journalRequestDto);

        return ResponseEntity.ok(savedId);
    }

    @GetMapping("/journal")
    public ResponseEntity<List<JournalDto.Response>> searchByKeywordWithPaging(
            @RequestParam String keyword, @RequestParam String type, @RequestParam int page
    ) {
        if (!JournalType.isJournalType(type)) {
            throw new BusinessException(ErrorCode.INVALID_JOURNAL_TYPE);
        }

        List<JournalDto.Response> responses = journalApiService.searchByKeywordWithPaging(keyword, type, page);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/journal/{journalId}")
    public ResponseEntity<JournalDetailDto> getJournalDetail(@PathVariable Long journalId) {

        JournalDetailDto journalDetail = journalApiService.getJournalDetail(journalId);

        return ResponseEntity.ok(journalDetail);
    }
}
