package ezoz.backend_ezoz.api.journal.controller;

import ezoz.backend_ezoz.api.journal.dto.JournalDetailDto;
import ezoz.backend_ezoz.api.journal.dto.JournalDto;
import ezoz.backend_ezoz.api.journal.service.JournalApiService;
import ezoz.backend_ezoz.domain.journal.entity.JournalType;
import ezoz.backend_ezoz.global.error.exception.BusinessException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import ezoz.backend_ezoz.global.validator.ImageValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Api(tags = "Journal")
@RequiredArgsConstructor
public class JournalApiController {

    private final JournalApiService journalApiService;
    private final ImageValidator imageValidator;

    @PostMapping(value = "/journal", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "저널 생성 API", notes = "저널을 새로 작성한다.")
    public ResponseEntity<?> registerJournal(JournalDto.Request journalRequestDto) {


        List<MultipartFile> journalImageFiles = journalRequestDto.getJournalImageFiles();
        if (journalImageFiles == null || imageValidator.notExistFileName(journalImageFiles)) {
            throw new BusinessException(ErrorCode.IMAGE_NOT_EXISTS);
        }
        Long savedId = journalApiService.registerJournal(journalRequestDto);

        return ResponseEntity.ok(savedId);
    }

    @GetMapping("/journal")
    @ApiOperation(value = "저널 검색 API", notes = "키워드와 타입에 따라 추출된 저널을 페이징해 제공한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "keyword",
                    value = "저널 id",
                    dataType = "string",
                    paramType = "query",
                    required = true,
                    example = "충청남도"
            ),
            @ApiImplicitParam(
                    name = "type",
                    value = "저널 타입 (EZ or OZ)",
                    dataType = "string",
                    paramType = "query",
                    required = true,
                    example = "EZ"
            ),
            @ApiImplicitParam(
                    name = "page",
                    value = "페이지 번호 (0부터 시작)",
                    dataType = "int",
                    paramType = "query",
                    required = true,
                    example = "0"
            )
    })
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
    @ApiOperation(value = "저널 조회 API", notes = "저널 id를 통해 해당하는 저널을 제공한다.")
    public ResponseEntity<JournalDetailDto> getJournalDetail(@PathVariable Long journalId) {

        JournalDetailDto journalDetail = journalApiService.getJournalDetail(journalId);

        return ResponseEntity.ok(journalDetail);
    }
}
